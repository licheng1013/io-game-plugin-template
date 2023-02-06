package com.template.config;

import com.iohao.game.bolt.broker.client.external.session.UserSession;
import com.iohao.game.bolt.broker.client.external.session.UserSessions;
import com.iohao.game.bolt.broker.client.external.session.hook.UserHook;
import lombok.extern.slf4j.Slf4j;


/**
 * 跟默认实现一致
 * @author lc
 * @since 2023/1/12
 */
@Slf4j
public class MyUserHookDefault implements UserHook {
    @Override
    public void into(UserSession userSession) {
        long userId = userSession.getUserId();
        log.info("玩家上线 userId: {} -- {}", userId, userSession.getUserChannelId());
        log.info("当前在线玩家数量： {}", UserSessions.me().countOnline());
    }
    @Override
    public void quit(UserSession userSession) {
        long userId = userSession.getUserId();
        log.info("玩家退出 userId: {} -- {}", userId, userSession.getUserChannelId());
        log.info("当前在线玩家数量： {}", UserSessions.me().countOnline());
    }
}