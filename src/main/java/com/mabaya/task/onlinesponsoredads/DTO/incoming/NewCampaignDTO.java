package com.mabaya.task.onlinesponsoredads.DTO.incoming;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mabaya.task.onlinesponsoredads.converts.CustomLocalDateDeserializer;
import java.util.List;

@JsonDeserialize(using = CustomLocalDateDeserializer.class)
public record NewCampaignDTO(
        String name,
        String date,
        double bid,
        List<Long> productIds
)
{}
