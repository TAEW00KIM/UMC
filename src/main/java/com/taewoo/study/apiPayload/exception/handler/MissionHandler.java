package com.taewoo.study.apiPayload.exception.handler;

import com.taewoo.study.apiPayload.code.BaseErrorCode;
import com.taewoo.study.apiPayload.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode baseErrorCode) {super(baseErrorCode);}
}
