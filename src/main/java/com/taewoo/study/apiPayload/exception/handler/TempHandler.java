package com.taewoo.study.apiPayload.exception.handler;

import com.taewoo.study.apiPayload.code.BaseErrorCode;
import com.taewoo.study.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
