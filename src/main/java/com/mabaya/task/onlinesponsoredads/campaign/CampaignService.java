package com.mabaya.task.onlinesponsoredads.campaign;

import com.mabaya.task.onlinesponsoredads.exceptions.OnlineSponsoredAdsException;
import com.mabaya.task.onlinesponsoredads.exceptions.general.DupCampaignException;
import com.mabaya.task.onlinesponsoredads.exceptions.notExist.MyNotExistException;
import com.mabaya.task.onlinesponsoredads.product.Product;
import com.mabaya.task.onlinesponsoredads.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final ProductService productService;

    public Campaign trySave(Campaign campaign) throws OnlineSponsoredAdsException {
        if(existByName(campaign))
            throw new DupCampaignException(campaign);

        return campaignRepository.save(campaign);
    }

    public boolean existByName(Campaign campaign){
        return campaignRepository.findByName(campaign.getName()).isPresent();
    }

    public void deleteById(Long campaignId) {
        campaignRepository.removeCampaignFrom_campaign_products(campaignId);
        campaignRepository.deleteById(campaignId);
    }

    public void updateBid(Long campaignId, double newBid) {
        campaignRepository.updateBid(campaignId, newBid);
    }

    public void updateProduct(Long campaignId, List<Long> productIds) throws MyNotExistException {
        campaignRepository.updateProduct(campaignId, productService.convertProductIds(productIds));
    }

    public List<Campaign> getAll() {
        return campaignRepository.getAll();
    }

    public void deleteAllDeactivate() {
        campaignRepository.getAll().forEach(campaign -> {
            if(!campaign.isActive())
                campaignRepository.deleteById(campaign.getId());
        });
    }

    @Scheduled(cron = "0 0 0 * * ?") // Run daily at midnight
    public void updateIsActiveForCampaigns() {
        List<Campaign> campaigns = campaignRepository.findAll();
        LocalDate tenDaysAgo = LocalDate.now().minus(10, ChronoUnit.DAYS);

        campaigns.forEach(campaign -> campaign.checkActive(tenDaysAgo));
        campaignRepository.saveAll(campaigns);
    }

    public void dropCampaigns() {
        campaignRepository.dropCampaigns();
        campaignRepository.drop_campaign_sequence();
    }
}
