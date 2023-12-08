package com.mabaya.task.onlinesponsoredads.product;

import com.mabaya.task.onlinesponsoredads.campaign.CampaignService;
import com.mabaya.task.onlinesponsoredads.exceptions.OnlineSponsoredAdsException;
import com.mabaya.task.onlinesponsoredads.exceptions.general.DupProductException;
import com.mabaya.task.onlinesponsoredads.exceptions.notExist.MyNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    public Set<Product> convertProductIds(List<Long> productIds) throws MyNotExistException {
        Set<Product> products = new HashSet<>();

        for (Long productId : productIds) {
            Product product =
                    findById(productId)
                            .orElseThrow(() -> new MyNotExistException("product", productId));
            products.add(product);
        }

        return products;
    }

    public Product trySave(Product product) throws OnlineSponsoredAdsException {
        if(existBySerialNumber(product))
            throw new DupProductException(product);

        return productRepository.save(product);
    }

    private boolean existBySerialNumber(Product product) {
        return productRepository.findBySerialNumber(product.getSerialNumber()).isPresent();
    }

    public void deleteById(Long productId) {
        productRepository.removeProductFrom_campaign_products(productId);
        productRepository.deleteById(productId);
    }

    public void updateCategory(Long productId, String newCategory) {
        productRepository.updateCategory(productId, newCategory);
    }

    public void updatePrice(Long productId, double newPrice) {
        productRepository.updatePrice(productId, newPrice);
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProductWithMaxBidByCategoryName(String categoryName){
        return productRepository.getProductWithMaxBidByCategoryName(categoryName);
    }

    public void dropProducts() {
        productRepository.drop_campaign_products();
        productRepository.drop_products();
        productRepository.drop_product_sequence();
    }
}
