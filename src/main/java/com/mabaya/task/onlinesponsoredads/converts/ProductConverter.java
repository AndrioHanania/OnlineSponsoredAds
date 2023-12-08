package com.mabaya.task.onlinesponsoredads.converts;

import com.mabaya.task.onlinesponsoredads.DTO.outgoing.ProductDTO;
import com.mabaya.task.onlinesponsoredads.product.Product;
import java.util.function.Function;

public class ProductConverter implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getSerialNumber(),
                product.getTitle(),
                product.getCategory().getName(),
                product.getPrice());
    }
}
