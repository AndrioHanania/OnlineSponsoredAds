package com.mabaya.task.onlinesponsoredads.exceptions.notExist;

import com.mabaya.task.onlinesponsoredads.exceptions.OnlineSponsoredAdsException;

public class MyNotExistException extends OnlineSponsoredAdsException {
    public MyNotExistException(String objName, Long objId) {
        super(String.format("%s (id=%s) doesn't exist", objName, objId.toString()));
    }
}
