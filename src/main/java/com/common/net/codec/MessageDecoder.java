package com.common.net.codec;

import com.common.net.DataPackage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;;import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.print("ffffff");
//        int dataLength = in.readInt();       // 读取传送过来的消息的长度。ByteBuf 的readInt()方法会让他的readIndex增加4
//        if (dataLength < 0) { // 我们读到的消息体长度为0，这是不应该出现的情况，这里出现这情况，关闭连接。
//            ctx.close();
//        }
//        if (in.readableBytes() < dataLength) { //读到的消息体长度如果小于我们传送过来的消息长度，则resetReaderIndex. 这个配合markReaderIndex使用的。把readIndex重置到mark的地方
//            in.resetReaderIndex();
//            return;
//        }

//        byte[] body = new byte[dataLength];  //  嗯，这时候，我们读到的长度，满足我们的要求了，把传送过来的数据，取出来吧~~
//        in.readBytes(body);
        while (true) {
            if (!in.isReadable() || in.readableBytes() < DataPackage.PACKAGE_HEAD_LENGTH) {
                return;
            }
            in.markReaderIndex();                  //一下当前的readIndex的位置
            short packID = in.readShort();
            if (packID != DataPackage.PACKAGE_HEAD_IDENTIFYING) {
                return;
            }
            int position = in.readerIndex();
            short size = in.readShort();
            if (in.readableBytes() >= size - 4 && size > 4) {
                int len = size - DataPackage.PACKAGE_HEAD_LENGTH;
                DataPackage pack = new DataPackage();
                pack.setSize(len);
                pack.setIsZip(in.readByte());
                pack.setCmd(in.readInt());
                byte[] data = new byte[len];
                in.readBytes(data, 0, len);
                pack.setBytes(data);
                out.add(pack);//正确读取返回
            } else {
                in.resetReaderIndex();
                return;
            }
        }
    }
}
