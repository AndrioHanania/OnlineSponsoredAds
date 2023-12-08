package com.mabaya.task.onlinesponsoredads.DTO.incoming;

import java.util.List;

public record UpdateProductsDTO(
        Long campaignId,
       List<Long> productIds
)
{}
