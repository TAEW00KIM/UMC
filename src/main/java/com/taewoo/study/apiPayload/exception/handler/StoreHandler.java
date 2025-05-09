package com.taewoo.study.apiPayload.exception.handler;

import com.taewoo.study.apiPayload.code.BaseErrorCode;
import com.taewoo.study.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode baseErrorCode) {super(baseErrorCode);}
}
