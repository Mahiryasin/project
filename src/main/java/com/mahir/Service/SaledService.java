package com.mahir.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahir.DTO.SaledDTO;
import com.mahir.DTO.SaledDTO_IU;

@Service
public class SaledService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CurrencyRatesService currencyRatesService;

    @Transactional
    public SaledDTO PostSaled_Car(SaledDTO_IU saledDTO_IU) {

        String customerSql = """
                    SELECT c.id, c.first_name, c.last_name,
                           a.id as account_id, a.amount, a.currency_code
                    FROM Customer c
                    JOIN Account a ON c.account_id = a.id
                    WHERE c.user_id = ?
                """;

        CustomerAccountInfo customer = null;
        try {
            customer = jdbcTemplate.queryForObject(customerSql, new CustomerAccountInfoMapper(),
                    saledDTO_IU.getUser_id());
        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.out.println("Kullanıcı bulunamadı!");
            return null;
        }

        String carSql = "SELECT * FROM Car WHERE id = ?";
        CarInfo car = null;
        try {
            car = jdbcTemplate.queryForObject(carSql, new CarInfoMapper(), saledDTO_IU.getCar_id());
        } catch (Exception e) {
            System.out.println("Araba bulunamadı!");
            return null;
        }

        if (!"SALABLE".equals(car.statusCode)) {
            System.out.println("Araba satılamaz durumda!");
            return null;
        }

        String findGalleristSql = "SELECT gallerist_id FROM Gallerist_Car WHERE car_id = ?";
        Integer galleristId = null;
        try {
            galleristId = jdbcTemplate.queryForObject(findGalleristSql, Integer.class, car.id);
        } catch (Exception e) {
            System.out.println("Bu arabanın sahibi olan galerici bulunamadı!");
            return null;
        }

        String usd = null;
        String eur = null;
        try {
            usd = currencyRatesService.getCurrencyRates(GetFormat(new Date()), GetFormat(new Date())).getBody()
                    .getItems().get(0).getUsd();

            eur = currencyRatesService.getCurrencyRates(GetFormat(new Date()), GetFormat(new Date())).getBody()
                    .getItems().get(0).getEur();

        } catch (Exception e) {
            System.out.println("mana servisine bağlanırken hata oluştu: " + e.getMessage());
            return null;
        }

        if (usd == null || eur == null) {
            System.out.println("Döviz kurları alınamadı");
            return null;
        }

        BigDecimal usdToTry = new BigDecimal(usd);
        BigDecimal eurToTry = new BigDecimal(eur);

        BigDecimal carPriceInTry = convertToTry(car.price, car.currencyCode, usdToTry, eurToTry);
        BigDecimal customerBalanceInTry = convertToTry(customer.amount, customer.currencyCode, usdToTry, eurToTry);

        if (customerBalanceInTry.compareTo(carPriceInTry) < 0) {
            System.out.println("Yetersiz Bakiye!");
            return null;
        }

        BigDecimal remainingBalanceInTry = customerBalanceInTry.subtract(carPriceInTry);
        BigDecimal newCustomerAmount = convertFromTry(remainingBalanceInTry, customer.currencyCode, usdToTry, eurToTry);

        String updateAccountSql = "UPDATE Account SET amount = ? WHERE id = ?";
        jdbcTemplate.update(updateAccountSql, newCustomerAmount, customer.accountId);

        String updateCarSql = "UPDATE Car SET status_code = 'SALED' WHERE id = ?";
        jdbcTemplate.update(updateCarSql, car.id);

        String insertSaleSql = """
                    INSERT INTO Saled_Car (gallerist_id, car_id, customer_id, create_date)
                    VALUES (?, ?, ?, GETDATE())
                """;

        jdbcTemplate.update(insertSaleSql, galleristId, car.id, customer.id);

        SaledDTO response = new SaledDTO();
        response.setMessage("Satış Başarılı.");
        return response;
    }

    private BigDecimal convertToTry(BigDecimal amount, String currencyCode, BigDecimal usdRate, BigDecimal eurRate) {
        if (amount == null)
            return BigDecimal.ZERO;
        if ("TRY".equalsIgnoreCase(currencyCode))
            return amount;
        if ("USD".equalsIgnoreCase(currencyCode))
            return amount.multiply(usdRate);
        if ("EUR".equalsIgnoreCase(currencyCode))
            return amount.multiply(eurRate);
        return amount;
    }

    private BigDecimal convertFromTry(BigDecimal amountInTry, String targetCurrency, BigDecimal usdRate,
            BigDecimal eurRate) {
        if (amountInTry == null)
            return BigDecimal.ZERO;
        if ("TRY".equalsIgnoreCase(targetCurrency))
            return amountInTry;
        if ("USD".equalsIgnoreCase(targetCurrency))
            return amountInTry.divide(usdRate, 2, RoundingMode.HALF_UP);
        if ("EUR".equalsIgnoreCase(targetCurrency))
            return amountInTry.divide(eurRate, 2, RoundingMode.HALF_UP);
        return amountInTry;
    }

    private static String GetFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DAY_OF_MONTH, -2);

        return simpleDateFormat.format(calendar.getTime());
    }

    private static class CarInfo {
        int id;
        BigDecimal price;
        String currencyCode;
        String statusCode;
    }

    private static class CarInfoMapper implements RowMapper<CarInfo> {
        @Override
        public CarInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            CarInfo info = new CarInfo();
            info.id = rs.getInt("id");
            info.price = rs.getBigDecimal("price");
            info.currencyCode = rs.getString("currency_code");
            info.statusCode = rs.getString("status_code");
            return info;
        }
    }

    private static class CustomerAccountInfo {
        int id;
        int accountId;
        BigDecimal amount;
        String currencyCode;
    }

    private static class CustomerAccountInfoMapper implements RowMapper<CustomerAccountInfo> {
        @Override
        public CustomerAccountInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            CustomerAccountInfo info = new CustomerAccountInfo();
            info.id = rs.getInt("id");
            info.accountId = rs.getInt("account_id");
            info.amount = rs.getBigDecimal("amount");
            info.currencyCode = rs.getString("currency_code");
            return info;
        }
    }
}