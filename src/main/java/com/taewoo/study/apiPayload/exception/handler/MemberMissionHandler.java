package com.taewoo.study.apiPayload.exception.handler;

import com.taewoo.study.apiPayload.code.BaseErrorCode;
import com.taewoo.study.apiPayload.exception.GeneralException;

public class MemberMissionHandler extends GeneralException {
    public MemberMissionHandler(BaseErrorCode baseErrorCode) {super(baseErrorCode);}
}
