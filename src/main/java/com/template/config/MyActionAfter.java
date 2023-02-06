package com.template.config;

import com.iohao.game.action.skeleton.core.ActionCommand;
import com.iohao.game.action.skeleton.core.commumication.ChannelContext;
import com.iohao.game.action.skeleton.core.flow.ActionAfter;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import com.iohao.game.action.skeleton.core.flow.attr.FlowAttr;
import com.iohao.game.action.skeleton.core.flow.interal.DefaultActionAfter;
import com.iohao.game.action.skeleton.protocol.HeadMetadata;
import com.iohao.game.action.skeleton.protocol.ResponseMessage;
import lombok.extern.slf4j.Slf4j;

import static com.alipay.remoting.rpc.RpcCommandType.REQUEST_ONEWAY;

/**
 * 移除信息返回
 * @author lc
 * @since 2023/1/12
 */
@Slf4j
public class MyActionAfter implements ActionAfter {

    /** 跟默认的实现只多了两行 {@link DefaultActionAfter} */
    @Override
    public void execute(final FlowContext flowContext) {
        final ResponseMessage response = flowContext.getResponse();

        ChannelContext channelContext = getChannelContext(flowContext);

        // 有错误就响应给调用方
        if (response.hasError()) {
            // 装载错误信息返回给客户端 -
            String msg = flowContext.option(FlowAttr.msgException);
            response.setValidatorMsg(msg);
            channelContext.sendResponse(response);
            return;
        }

        // action 方法返回值是 void 的，不做处理
        ActionCommand actionCommand = flowContext.getActionCommand();
        if (actionCommand.getActionMethodReturnInfo().isVoid()) {
            return;
        }

        // 将数据回传给调用方
        channelContext.sendResponse(response);
    }

    private ChannelContext getChannelContext(FlowContext flowContext) {
        ResponseMessage response = flowContext.getResponse();
        HeadMetadata headMetadata = response.getHeadMetadata();

        byte rpcCommandType = headMetadata.getRpcCommandType();

        if (rpcCommandType == REQUEST_ONEWAY) {
            return flowContext.option(FlowAttr.brokerClientContext);
        } else {
            return flowContext.option(FlowAttr.channelContext);
        }
    }
}
