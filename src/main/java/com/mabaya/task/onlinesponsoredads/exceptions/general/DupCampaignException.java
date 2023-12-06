package com.mabaya.task.onlinesponsoredads.exceptions.general;

import com.mabaya.task.onlinesponsoredads.campaign.Campaign;
import com.mabaya.task.onlinesponsoredads.exceptions.OnlineSponsoredAdsException;

public class DupCampaignException extends OnlineSponsoredAdsException {
    public DupCampaignException(Campaign campaign) {
        super(String.format("There is already a campaign with name= %s",
                campaign.getName()));
    }
}
