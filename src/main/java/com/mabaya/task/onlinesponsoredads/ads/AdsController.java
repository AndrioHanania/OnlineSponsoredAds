package com.mabaya.task.onlinesponsoredads.ads;

import com.mabaya.task.onlinesponsoredads.DTO.outgoing.ProductDTO;
import com.mabaya.task.onlinesponsoredads.converts.ProductConverter;
import com.mabaya.task.onlinesponsoredads.exceptions.ResponseException;
import com.mabaya.task.onlinesponsoredads.product.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/ads")
@Slf4j
public class AdsController {
    private final AdsService adsService;
    private final ProductConverter productConverter;

    @GetMapping(path = "serveAd")
    public ProductDTO serveAd(@RequestParam("category") String category) {
        try{
            log.info("[AdsController][api/ads/serveAd] received new request to serve an ad");
            Product product = adsService.serveAd(category);
            log.info("[AdsController][api/ads/serveAd] serve an ad completed successfully");
            return productConverter.apply(product);
        }
        catch (Exception e){
            log.error(String.format("[AdsController][api/ads/serveAd] serve an ad failed: %s", e));
            throw new ResponseException(e, "serveAd");
        }
    }

    @DeleteMapping(path = "dropDB")
    public void dropDB() {
        try{
            log.info("[AdsController][api/ads/dropDB] received new request to drop the db");
            adsService.dropDB();
            log.info("[AdsController][api/ads/dropDB] drop the db completed successfully");
        }
        catch (Exception e){
            log.error(String.format("[AdsController][api/ads/dropDB] drop the db failed: %s", e));
            throw new ResponseException(e, "dropDB");
        }
    }
}
