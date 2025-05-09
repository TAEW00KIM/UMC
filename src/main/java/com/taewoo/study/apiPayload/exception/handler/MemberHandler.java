package com.taewoo.study.apiPayload.exception.handler;

import com.taewoo.study.apiPayload.code.BaseErrorCode;
import com.taewoo.study.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode baseErrorCode) {super(baseErrorCode);}
}
