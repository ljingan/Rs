package com.common.net.codec;

import com.common.net.DataPackage;
import com.google.protobuf.CodedInputStream;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;;import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if (in.readableBytes() < DataPackage.PACKAGE_HEAD_LENGTH) {
            return;
        }

        in.markReaderIndex();                  //我们标记一下当前的readIndex的位置
        int dataLength = in.readInt();       // 读取传送过来的消息的长度。ByteBuf 的readInt()方法会让他的readIndex增加4
        if (dataLength < 0) { // 我们读到的消息体长度为0，这是不应该出现的情况，这里出现这情况，关闭连接。
            ctx.close();
        }
        if (in.readableBytes() < dataLength) { //读到的消息体长度如果小于我们传送过来的消息长度，则resetReaderIndex. 这个配合markReaderIndex使用的。把readIndex重置到mark的地方
            in.resetReaderIndex();
            return;
        }

        byte[] body = new byte[dataLength];  //  嗯，这时候，我们读到的长度，满足我们的要求了，把传送过来的数据，取出来吧~~
        in.readBytes(body);

        while (true) {
            int position = in.readerIndex();
            int size = in.readShort();
            if (in.readableBytes() + 2 >= size) {
                DataPackage pack = new DataPackage();
                pack.setSize(size);
                pack.setIsZip(in.readByte());
                pack.setCmd(in.readShort());
                int len = size - DataPackage.PACKAGE_HEAD_LENGTH;
                byte[] data = new byte[len];

                in.readBytes(data, 0, len);
                pack.setBytes(data);
                out.add(pack);//正确读取返回
            } else {
                in.re
                buff.setPosition(position);
                break;
            }
        }

//        Object o = convertToObject(body);  //将byte数据转化为我们需要的对象。伪代码，用什么序列化，自行选择
//        out.add(o);

        in.markReaderIndex();//标记读取的位置
        final byte[] buf = new byte[5];//varint32 最大5个字节
        for (int i = 0; i < buf.length; i++) {
            if (!in.isReadable()) {
                in.resetReaderIndex();
                return;
            }

            buf[i] = in.readByte();
            if (buf[i] >= 0) {
                int length = CodedInputStream.newInstance(buf, 0, i + 1).readRawVarint32();//varint表示的格式 转 实际长度int
                if (length < 0) {
                    throw new CorruptedFrameException("negative length: " + length);
                }

                if (in.readableBytes() < length) {//长度不够，回滚标记
                    in.resetReaderIndex();
                    return;
                } else {
                    out.add(in.readBytes(length));//正确读取返回
                    return;
                }
            }
        }

        // Couldn't find the byte whose MSB is off.
        throw new CorruptedFrameException("length wider than 32-bit");
    }
}
