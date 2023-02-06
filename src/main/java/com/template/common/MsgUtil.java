package com.template.common;

import com.iohao.game.action.skeleton.core.CmdInfo;
import com.iohao.game.bolt.broker.core.client.BrokerClientHelper;
import reactor.util.annotation.Nullable;

import java.util.List;

/**
 * 广播封装
 * @author lc
 * @since 2023/1/12
 */
public class MsgUtil {
    /** 发送多人动作  */
    public static void sendData(int cmd, int subCmd, List<Long> list){
        list.forEach((e)->{
            sendData(cmd,subCmd,null,e);
        });
    }
    /** 发送多人动作和消息,允许data为null  */
    public static void sendData(int cmd, int subCmd, @Nullable Object data, List<Long> list){
        list.forEach((e)->{
            sendData(cmd,subCmd,data,e);
        });
    }
    public static void sendData(int cmd,int subCmd,Object data,long userId){ //发送单人消息
        var broadcastContext = BrokerClientHelper.me().getBroadcastContext();
        broadcastContext.broadcast(CmdInfo.getCmdInfo(cmd,subCmd),data,userId);
    }

}
