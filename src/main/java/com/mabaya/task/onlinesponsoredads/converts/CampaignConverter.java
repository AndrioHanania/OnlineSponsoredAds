package com.mabaya.task.onlinesponsoredads.converts;

import com.mabaya.task.onlinesponsoredads.DTO.outgoing.CampaignDTO;
import com.mabaya.task.onlinesponsoredads.campaign.Campaign;
import com.mabaya.task.onlinesponsoredads.product.Product;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CampaignConverter implements Function<Campaign, CampaignDTO> {
    @Override
    public CampaignDTO apply(Campaign campaign) {
        return new CampaignDTO(
                campaign.getId(),
                campaign.getName(),
                campaign.getStartDate().toString(),
                campaign.getBid(),
                campaign.getProducts().stream().map(Product::getId).collect(Collectors.toList()));
    }
}
