package com.mabaya.task.onlinesponsoredads.ads;

import com.mabaya.task.onlinesponsoredads.campaign.CampaignService;
import com.mabaya.task.onlinesponsoredads.category.Category;
import com.mabaya.task.onlinesponsoredads.category.CategoryService;
import com.mabaya.task.onlinesponsoredads.exceptions.notExist.NoneEntityException;
import com.mabaya.task.onlinesponsoredads.product.Product;
import com.mabaya.task.onlinesponsoredads.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdsService {
    private final ProductService productService;
    private final CampaignService campaignService;
    private final CategoryService categoryService;

    public Product serveAd(String category) throws NoneEntityException {
        return productService
                .getProductWithMaxBidByCategoryName(category)
                .orElseThrow(() ->new NoneEntityException("product"));
    }

    public void dropDB() {
        productService.dropProducts();
        campaignService.dropCampaigns();
        categoryService.dropCategories();
    }
}
