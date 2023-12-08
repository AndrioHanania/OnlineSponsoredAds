package com.mabaya.task.onlinesponsoredads.DTO.incoming;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Getter
@Setter
public class NewCampaignDTO {
    private String name;
    private LocalDate date;
    private double bid;
    private List<Long> productIds;

    public NewCampaignDTO(String name, String date, double bid, List<Long> productIds) {
        this.name = name;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd\\MM\\yyyy");
        this.date = LocalDate.parse(date, formatter);
        this.bid = bid;
        this.productIds = productIds;
    }
}