package com.mabaya.task.onlinesponsoredads.DTO.outgoing;

public record ProductDTO(
        Long id,
        int serialNumber,
        String title,
        String category,
        double price
)
{}
