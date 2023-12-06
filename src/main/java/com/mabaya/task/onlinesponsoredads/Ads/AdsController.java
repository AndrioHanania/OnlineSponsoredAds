package com.mabaya.task.onlinesponsoredads.Ads;

import com.mabaya.task.onlinesponsoredads.DTO.outgoing.ProductDTO;
import com.mabaya.task.onlinesponsoredads.exceptions.ResponseException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/ads")
@Slf4j
public class AdsController {
    @GetMapping(path = "serveAd")
    public ProductDTO serveAd() {
        try{
            log.info("[AdsController][api/ads/serveAd] received new request to serve an ad");
            log.info("[AdsController][api/ads/serveAd] serve an ad completed successfully");
            return null;
        }
        catch (Exception e){
            log.error(String.format("[AdsController][api/ads/serveAd] serve an ad failed: %s", e));
            throw new ResponseException(e, "serveAd");
        }
    }
}
