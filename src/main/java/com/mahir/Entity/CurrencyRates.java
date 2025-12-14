package com.mahir.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyRates {

    @JsonProperty("totalCount")
    private Integer totalCount;

    @JsonProperty("items")
    private List<CurrencyItems> items;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<CurrencyItems> getItems() {
        return items;
    }

    public void setItems(List<CurrencyItems> items) {
        this.items = items;
    }
}