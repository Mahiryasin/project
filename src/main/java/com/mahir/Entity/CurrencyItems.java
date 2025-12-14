package com.mahir.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// Bilinmeyen başka alanlar gelirse (örn: UNIXTIME) hata vermemesi için bunu ekle:
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyItems {

    @JsonProperty("Tarih")
    private String date;

    @JsonProperty("TP_DK_USD_A_YTL")
    private String usd;

    @JsonProperty("TP_DK_EUR_A_YTL")
    private String eur;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsd() {
        return usd;
    }

    public void setUsd(String usd) {
        this.usd = usd;
    }

    public String getEur() {
        return eur;
    }

    public void setEur(String eur) {
        this.eur = eur;
    }
}