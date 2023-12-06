package com.mabaya.task.onlinesponsoredads.converts;

import com.mabaya.task.onlinesponsoredads.DTO.incoming.NewCampaignDTO;
import com.mabaya.task.onlinesponsoredads.campaign.Campaign;
import com.mabaya.task.onlinesponsoredads.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import java.util.function.Function;

@AllArgsConstructor
public class NewCampaignDTOConverter implements Function<NewCampaignDTO, Campaign> {
    private final ProductService productService;

    @SneakyThrows
    @Override
    public Campaign apply(NewCampaignDTO newCampaignDTO) {
        return new Campaign(
                newCampaignDTO.name(),
                newCampaignDTO.bid(),
                CustomLocalDateDeserializer.convert(newCampaignDTO.date()),
                productService.convertProductIds(newCampaignDTO.productIds()));
    }
}