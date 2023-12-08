package com.mabaya.task.onlinesponsoredads.converts;

import com.mabaya.task.onlinesponsoredads.DTO.incoming.NewProductDTO;
import com.mabaya.task.onlinesponsoredads.category.Category;
import com.mabaya.task.onlinesponsoredads.category.CategoryService;
import com.mabaya.task.onlinesponsoredads.product.Product;
import lombok.AllArgsConstructor;
import java.util.function.Function;

@AllArgsConstructor
public class NewProductDTOConverter implements Function<NewProductDTO, Product> {
    private final CategoryService categoryService;

    @Override
    public Product apply(NewProductDTO newProductDTO) {

        Category category = categoryService
                .findByName(newProductDTO.category())
                .orElse(categoryService.save(newProductDTO.category()));

        return new Product(
                newProductDTO.serialNumber(),
                newProductDTO.title(),
                category,
                newProductDTO.price());
    }
}
