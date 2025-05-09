package com.taewoo.study.apiPayload.exception.handler;

import com.taewoo.study.apiPayload.code.BaseErrorCode;
import com.taewoo.study.apiPayload.exception.GeneralException;

public class FoodCateoryHandler extends GeneralException {
    public FoodCateoryHandler(BaseErrorCode baseErrorCode) {super(baseErrorCode);}
}
