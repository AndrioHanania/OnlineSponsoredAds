package com.mabaya.task.onlinesponsoredads.DTO.incoming;

public record NewProductDTO(
        int serialNumber,
        String title,
        String category,
        double price
)
{}
