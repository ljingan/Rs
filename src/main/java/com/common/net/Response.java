package com.common.net;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务端回复对象
 */
public class Response extends BasePackage {

    /**
     * 包头长度
     */
    public static int PACKAGE_HEAD_LENGTH = 2 + 2 + 2 + 1 + 4;

    /**
     * 响应标识
     */
    private int status = 0;

    /**
     * proto class
     */
    private Class<?> protoClass;

    public Response(Request request) {
        this.setCmd(request.getCmd());
        this.setIsZip(request.getIsZip());
        this.setSession(request.getSession());
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public int getSize() {
        return PACKAGE_HEAD_LENGTH + (getBytes() == null ? 0 : getBytes().length);
    }

    public void setProtoClass(Class<?> protoClass) {
        this.protoClass = protoClass;
    }

    public Class<?> getProtoClass() {
        return protoClass;
    }

}
