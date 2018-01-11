package com.common.net.codec;

import com.common.net.BasePackage;
import com.common.net.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
;import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        logger.info("decode  remoteAddress:{}", ctx.channel().remoteAddress());
        while (true) {
            if (!in.isReadable() || in.readableBytes() < Request.PACKAGE_HEAD_LENGTH) {
                return;
            }
            in.markReaderIndex();                  //标记一下当前的readIndex的位置
            short packID = in.readShort();
            if (packID != BasePackage.PACKAGE_HEAD_IDENTIFYING) {
                return;
            }
            int position = in.readerIndex();
            short size = in.readShort();
            if (in.readableBytes() >= size - 4 && size > 4) {
                int len = size - Request.PACKAGE_HEAD_LENGTH;
                Request request = new Request();
                request.setSize(len);
                request.setIsZip(in.readByte());
                request.setCmd(in.readInt());
                byte[] data = new byte[len];
                in.readBytes(data, 0, len);
                request.setBytes(data);
                out.add(request);//正确读取返回
                logger.info("decode  remoteAddress:{}, readableBytes:{},  cmd:{}, size:{},", ctx.channel().remoteAddress(), in.readableBytes(), request.getCmd(), request.getSize());
            } else {
                in.resetReaderIndex();
                return;
            }
        }
    }
}
