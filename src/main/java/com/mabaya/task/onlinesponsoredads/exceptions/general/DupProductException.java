package com.mabaya.task.onlinesponsoredads.exceptions.general;

import com.mabaya.task.onlinesponsoredads.exceptions.OnlineSponsoredAdsException;
import com.mabaya.task.onlinesponsoredads.product.Product;

public class DupProductException extends OnlineSponsoredAdsException {
    public DupProductException(Product product) {
        super(String.format("There is already a product with serial number= %d",
                product.getSerialNumber()));
    }
}
