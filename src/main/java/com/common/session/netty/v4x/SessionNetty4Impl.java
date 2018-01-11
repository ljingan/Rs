package com.common.session.netty.v4x;

import com.common.session.impl.SessionImpl;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SessionNetty4Impl extends SessionImpl<Channel> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Channel channel;

    public SessionNetty4Impl(long sessionId, Channel channel) {
        super(sessionId, channel);
        this.channel = channel;
//        this.attributes = new HashMap<String, Object>();
    }

    @Override
    public Channel getConnection() {
        return channel;
    }

    @Override
    public boolean isConnected() {
        return channel == null ? false : channel.isActive();
    }

    @Override
    public boolean disconnect() throws IOException {
        if(!isConnected())
            return false;
        logger.info("disconnect sessionId:{}", getSessionId());
        channel.close();
        return true;
    }

    @Override
    public void clear() {
        super.clear();
        channel = null;
    }
}
