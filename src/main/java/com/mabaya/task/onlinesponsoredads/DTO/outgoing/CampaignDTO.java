package com.mabaya.task.onlinesponsoredads.DTO.outgoing;

import java.util.List;

public record CampaignDTO(
        Long id,
        String name,
        String date,
        double bid,
        List<Long> productIds)
{ }
