package com.mahir.Service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mahir.Entity.CurrencyRates;

@Service
public class CurrencyRatesService {

    private final String ROOT_URL = "https://evds2.tcmb.gov.tr/service/evds";
    private final String SERIES_URL = "series=TP.DK.USD.A.YTL-TP.DK.EUR.A.YTL";
    private final String TYPE_URL = "type=json";

    public ResponseEntity<CurrencyRates> getCurrencyRates(String startDate, String endDate) {
        try {
            String baseUrl = ROOT_URL + "/" + SERIES_URL + "&startDate=" + startDate + "&endDate=" + endDate + "&"
                    + TYPE_URL;

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("key", "h5fo3aVEiW");
            httpHeaders.set("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

            HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<CurrencyRates> response = restTemplate.exchange(
                    baseUrl,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<CurrencyRates>() {
                    });

            return response;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}