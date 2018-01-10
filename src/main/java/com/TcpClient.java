package com;

import com.C2S.C2SPtl;
import com.common.net.ByteArray;
import com.common.net.DataPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class TcpClient {
    public static void main(String[] args) throws Exception {
        System.out.println("client start...");
        try {
            Socket socket = new Socket("localhost", 8085);
            // 向服务器端发送数据
            OutputStream os = socket.getOutputStream();
            DataOutputStream bos = new DataOutputStream(os);

            C2SPtl.C2SLogin.Builder builder = C2SPtl.C2SLogin.newBuilder();
            builder.setId(12345);
            C2SPtl.C2SLogin login = builder.build();
            byte[] body = login.toByteArray();

            int size = 2 + 2 + 1 + 4 + body.length;

            ByteBuffer buffer = ByteBuffer.allocate(size);
            byte isZip = 0;
            int cmd = 1109;
            buffer.putShort(DataPackage.PACKAGE_HEAD_IDENTIFYING);
            buffer.putShort((short) size);
            buffer.put(isZip);
            buffer.putInt(cmd);
            buffer.put(body);
            byte[] data = buffer.array();
            bos.write(data);
            bos.flush();
            // 接收服务器端数据
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
//            System.out.println(dis.readUTF());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//public class TcpClient {
//    public static void main(String[] args) throws Exception {
//        System.out.println("client start...");
//        try {
//            Socket socket = new Socket("localhost", 8099);
//            // 向服务器端发送数据
//            OutputStream os = socket.getOutputStream();
//            DataOutputStream bos = new DataOutputStream(os);
//
//            C2SPtl.C2SLogin.Builder builder = C2SPtl.C2SLogin.newBuilder();
//            builder.setId(12345);
//            C2SPtl.C2SLogin login = builder.build();
//            byte[] body = login.toByteArray();
//            bos.writeShort(DataPackage.PACKAGE_HEAD_IDENTIFYING);
//            int size = 2 + 2 + 1 + 4 + body.length;
//            byte isZip = 0;
//            int cmd = 1109;
//            bos.writeShort(size);
//            bos.writeByte(isZip);
//            bos.writeInt(cmd);
//            bos.write(body);
//            bos.flush();
//            // 接收服务器端数据
//            InputStream is = socket.getInputStream();
//            DataInputStream dis = new DataInputStream(is);
////            System.out.println(dis.readUTF());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}