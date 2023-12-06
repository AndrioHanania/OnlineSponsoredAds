package com.mabaya.task.onlinesponsoredads.campaign;

import com.mabaya.task.onlinesponsoredads.DTO.incoming.NewCampaignDTO;
import com.mabaya.task.onlinesponsoredads.DTO.outgoing.CampaignDTO;
import com.mabaya.task.onlinesponsoredads.converts.CampaignConverter;
import com.mabaya.task.onlinesponsoredads.converts.NewCampaignDTOConverter;
import com.mabaya.task.onlinesponsoredads.exceptions.ResponseException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/campaigns")
@Slf4j
public class CampaignController {
    private final CampaignService campaignService;
    private final NewCampaignDTOConverter newCampaignDTOConverter;
    private final CampaignConverter campaignConverter;

    @GetMapping(path = "getAll")
    public List<CampaignDTO> getAll() { //The best practice is returning a Page<CampaignDTO> via page and size !!!
        try{
            log.info("[CampaignController][api/campaigns/getAll] received new request to get all campaigns");
            List<Campaign> campaigns = campaignService.getAll();
            log.info("[CampaignController][api/campaigns/getAll] get all campaigns completed successfully");
            return campaigns.stream().map(campaignConverter).collect(Collectors.toList());
        }
        catch (Exception e){
            log.error(String.format("[CampaignController][api/campaigns/getAll] get all campaigns failed: %s", e));
            throw new ResponseException(e, "getAll");
        }
    }

    @PostMapping(path = "createCampaign")
    public CampaignDTO createCampaign(@RequestBody NewCampaignDTO newCampaignDTO) {
        try{
            log.info("[CampaignController][api/campaigns/createCampaign] received new request to create a campaign");
            Campaign newCampaign = campaignService.trySave(newCampaignDTOConverter.apply(newCampaignDTO));
            log.info("[CampaignController][api/campaigns/createCampaign] create a campaign completed successfully");
            return campaignConverter.apply(newCampaign);
        }
        catch (Exception e){
            log.error(String.format("[CampaignController][api/campaigns/createCampaign] create a campaign failed: %s", e));
            throw new ResponseException(e, "createCampaign");
        }
    }

    @PutMapping(path = "updateBid")
    public void updateBid(
            @RequestParam("campaignId") Long campaignId,
            @RequestParam("newBid") double newBid) {
        try{
            log.info("[CampaignController][api/campaigns/updateBid] received new request to update the bid");
            campaignService.updateBid(campaignId, newBid);
            log.info("[CampaignController][api/campaigns/updateBid] update the bid completed successfully");
        }
        catch (Exception e){
            log.error(String.format("[CampaignController][api/campaigns/updateBid] update the bid failed: %s", e));
            throw new ResponseException(e, "updateBid");
        }
    }

    @PutMapping(path = "updateProduct")
    public void updateProduct(
            @RequestParam("campaignId") Long campaignId,
            @RequestParam("productIds") List<Long> productIds) {
        try{
            log.info("[CampaignController][api/campaigns/updateProduct] received new request to update products to campaign");
            campaignService.updateProduct(campaignId, productIds);
            log.info("[CampaignController][api/campaigns/updateProduct] update products to campaign completed successfully");
        }
        catch (Exception e){
            log.error(String.format("[CampaignController][api/campaigns/updateProduct] update products to campaign failed: %s", e));
            throw new ResponseException(e, "updateProduct");
        }
    }

    @DeleteMapping(path = "deleteCampaign")
    public void deleteCampaign(@RequestParam("campaignId") Long campaignId) {
        try{
            log.info("[CampaignController][api/campaigns/deleteCampaign] received new request to delete a campaign");
            campaignService.deleteById(campaignId);
            log.info("[CampaignController][api/campaigns/deleteCampaign] delete a campaign completed successfully");
        }
        catch (Exception e){
            log.error(String.format("[CampaignController][api/campaigns/deleteCampaign] delete a campaign failed: %s", e));
            throw new ResponseException(e, "deleteCampaign");
        }
    }

    @DeleteMapping(path = "deleteAllDeactivate")
    public void deleteAllDeactivate() {
        try{
            log.info("[CampaignController][api/campaigns/deleteAllDeactivate] received new request to delete all deactivate campaigns");
            campaignService.deleteAllDeactivate();
            log.info("[CampaignController][api/campaigns/deleteAllDeactivate] delete all deactivate campaigns completed successfully");
        }
        catch (Exception e){
            log.error(String.format("[CampaignController][api/campaigns/deleteAllDeactivate] delete all deactivate campaigns failed: %s", e));
            throw new ResponseException(e, "deleteAllDeactivate");
        }
    }
}
