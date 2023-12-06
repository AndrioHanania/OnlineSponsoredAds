package com.mabaya.task.onlinesponsoredads;

import com.mabaya.task.onlinesponsoredads.converts.CampaignConverter;
import com.mabaya.task.onlinesponsoredads.converts.NewCampaignDTOConverter;
import com.mabaya.task.onlinesponsoredads.converts.NewProductDTOConverter;
import com.mabaya.task.onlinesponsoredads.converts.ProductConverter;
import com.mabaya.task.onlinesponsoredads.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@AllArgsConstructor
@ComponentScan({"com"})//scan the packages for Spring-managed components.
@EntityScan("com")//scanning JPA @Entity classes.
@EnableJpaRepositories("com")//scanning JPA @Repository classes.
public class OnlineSponsoredAdsApplication {
    private final ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(OnlineSponsoredAdsApplication.class, args);
    }

    @Bean public NewCampaignDTOConverter createNewCampaignDTOConverter(){return new NewCampaignDTOConverter(productService);}
    @Bean public CampaignConverter createCampaignConverter(){
        return new CampaignConverter();
    }
    @Bean public NewProductDTOConverter createNewProductDTOConverter(){return new NewProductDTOConverter();}
    @Bean public ProductConverter createProductConverter(){return new ProductConverter();}
}
