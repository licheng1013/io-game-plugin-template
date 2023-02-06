package com.template.action;

import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.template.dto.MessageDto;

@ActionController(1)
public class TestAction {
    @ActionMethod(0)
    public MessageDto hello(MessageDto helloReq) {
        return helloReq;
    }

}