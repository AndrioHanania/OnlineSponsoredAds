package com.mabaya.task.onlinesponsoredads.exceptions.notExist;

import com.mabaya.task.onlinesponsoredads.exceptions.OnlineSponsoredAdsException;

public class NoneEntityException extends OnlineSponsoredAdsException {
    public NoneEntityException(String objName) {
        super(String.format("The are zero %ss in the DB", objName));
    }
}
