package com.mabaya.task.onlinesponsoredads.product;

import com.mabaya.task.onlinesponsoredads.DTO.incoming.NewProductDTO;
import com.mabaya.task.onlinesponsoredads.DTO.outgoing.ProductDTO;
import com.mabaya.task.onlinesponsoredads.converts.NewProductDTOConverter;
import com.mabaya.task.onlinesponsoredads.converts.ProductConverter;
import com.mabaya.task.onlinesponsoredads.exceptions.ResponseException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/products")
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final NewProductDTOConverter newProductDTOConverter;
    private final ProductConverter productConverter;

    @GetMapping(path = "getAll")
    public List<ProductDTO> getAll() { //The best practice is returning a Page<CampaignDTO> via page and size !!!
        try{
            log.info("[ProductController][api/products/getAll] received new request to get all the products");
            List<Product> products = productService.getAll();
            log.info("[ProductController][api/products/getAll] get all the products completed successfully");
            return products.stream().map(productConverter).collect(Collectors.toList());
        }
        catch (Exception e){
            log.error(String.format("[ProductController][api/products/getAll] get all the products failed: %s", e));
            throw new ResponseException(e, "createProduct");
        }
    }

    @PostMapping(path = "createProduct")
    public ProductDTO createProduct(@RequestBody NewProductDTO newProductDTO) {
        try{
            log.info("[ProductController][api/products/createProduct] received new request to create a product");
            Product newProduct = productService.trySave(newProductDTOConverter.apply(newProductDTO));
            log.info("[ProductController][api/products/createProduct] create a product completed successfully");
            return productConverter.apply(newProduct);
        }
        catch (Exception e){
            log.error(String.format("[ProductController][api/products/createProduct] create a product failed: %s", e));
            throw new ResponseException(e, "createProduct");
        }
    }

    @PutMapping(path = "updateCategory")
    public void updateCategory(
            @RequestParam("productId") Long productId,
            @RequestParam("newCategory") String newCategory
    ) {
        try{
            log.info("[ProductController][api/products/updateCategory] received new request to update the category");
            productService.updateCategory(productId, newCategory);
            log.info("[ProductController][api/products/updateCategory] update the category completed successfully");
        }
        catch (Exception e){
            log.error(String.format("[ProductController][api/products/updateCategory] update the category failed: %s", e));
            throw new ResponseException(e, "updateCategory");
        }
    }

    @PutMapping(path = "updatePrice")
    public void updatePrice(
            @RequestParam("productId") Long productId,
            @RequestParam("newPrice") double newPrice
    ) {
        try{
            log.info("[ProductController][api/products/updatePrice] received new request to update the price");
            productService.updatePrice(productId, newPrice);
            log.info("[ProductController][api/products/updatePrice] update the price completed successfully");
        }
        catch (Exception e){
            log.error(String.format("[ProductController][api/products/updatePrice] update the price failed: %s", e));
            throw new ResponseException(e, "updatePrice");
        }
    }

    @DeleteMapping(path = "deleteProduct")
    public void deleteProduct(@RequestParam("productId") Long productId) {
        try{
            log.info("[ProductController][api/products/deleteProduct] received new request to delete a product");
            productService.deleteById(productId);
            log.info("[ProductController][api/products/deleteProduct] delete a product completed successfully");
        }
        catch (Exception e){
            log.error(String.format("[ProductController][api/products/deleteProduct] delete a product failed: %s", e));
            throw new ResponseException(e, "deleteProduct");
        }
    }
}
