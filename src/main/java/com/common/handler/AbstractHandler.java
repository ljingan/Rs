package com.common.handler;

import com.common.net.BasePackage;
import com.common.net.Cmd;
import com.common.net.Request;
import com.common.net.Response;
import com.common.session.Session;
import io.netty.channel.Channel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractHandler implements Handler {

    class CmdPack {
        int cmd = 0;
        Class<?> requestProtoClass;
        Class<?> responseProtoClass;
        Method method;

        CmdPack(int cmd, Method method, Class<?> requestProtoClass, Class<?> responseProtoClass) {
            this.cmd = cmd;
            this.requestProtoClass = requestProtoClass;
            this.responseProtoClass = responseProtoClass;
            this.method = method;
        }
    }

    private Map<Integer, CmdPack> cmds = new ConcurrentHashMap<Integer, CmdPack>();

    public AbstractHandler() {
        initialize();
    }

    private void initialize() {

        for (Method m : this.getClass().getDeclaredMethods()) {
            Cmd cmd = m.getAnnotation(Cmd.class);
            if (cmd != null) {
                CmdPack cmdPack = new CmdPack(cmd.id(), m, cmd.requestProtoClass(), cmd.responseProtoClass());
                cmds.put(cmd.id(), cmdPack);
                Dispatcher.put(cmd.id(), this);
            }
        }
    }

    @Override
    public void exceute(Request request, Response response) {
        CmdPack cmdPack = cmds.get(request.getCmd());
        if (cmdPack.method != null && cmdPack.requestProtoClass != null) {
            try {
                Method parseMethod = cmdPack.requestProtoClass.getMethod("parseFrom",
                        byte[].class);
                Object data = parseMethod.invoke(null, request.getBytes());
                request.setData(data);
                response.setProtoClass(cmdPack.responseProtoClass);
                cmdPack.method.invoke(this, request, response);
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写数据到客户端
     *
     * @param response
     */
    protected void write(Response response) {
        Session<Channel> session = response.getSession();
        Channel channel = session.getConnection();
        channel.write(response);
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

}
