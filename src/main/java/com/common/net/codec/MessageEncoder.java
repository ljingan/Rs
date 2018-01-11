package com.common.net.codec;

import com.common.net.BasePackage;
import com.common.net.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageEncoder<T> extends MessageToByteEncoder {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public MessageEncoder() {
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg,
                          ByteBuf out) throws Exception {
        if (!(msg instanceof Response)) {
            logger.info("encode msg:{}", msg);
        }
        Response response = (Response) msg;
        try {
            out.writeShort(BasePackage.PACKAGE_HEAD_IDENTIFYING);
            out.writeShort(response.getSize());
            out.writeByte(response.getIsZip());
            out.writeInt(response.getCmd());
            out.writeShort(response.getStatus());
            out.writeBytes(response.getBytes());
            logger.info("encode cmd:{}, size:{}, status:{}", response.getCmd(), response.getSize(), response.getStatus());
        } catch (Exception e) {
            logger.info("encode error " + e.toString());
            e.printStackTrace();
        }

    }
}
