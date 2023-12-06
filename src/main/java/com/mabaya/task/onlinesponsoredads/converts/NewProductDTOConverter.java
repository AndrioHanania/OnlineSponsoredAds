package com.mabaya.task.onlinesponsoredads.converts;

import com.mabaya.task.onlinesponsoredads.DTO.incoming.NewProductDTO;
import com.mabaya.task.onlinesponsoredads.product.Product;
import java.util.function.Function;

public class NewProductDTOConverter implements Function<NewProductDTO, Product> {

    @Override
    public Product apply(NewProductDTO newProductDTO) {
        return new Product(
                newProductDTO.serialNumber(),
                newProductDTO.title(),
                newProductDTO.category(),
                newProductDTO.price());
    }
}
