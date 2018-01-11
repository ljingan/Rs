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

    private Map<Integer, Method> cmds = new ConcurrentHashMap<Integer, Method>();
    private Map<Integer, Class<?>> protos = new ConcurrentHashMap<Integer, Class<?>>();

    public AbstractHandler() {
        initialize();
    }

    private void initialize() {

        for (Method m : this.getClass().getDeclaredMethods()) {
            Cmd cmd = m.getAnnotation(Cmd.class);
            if (cmd != null) {
                cmds.put(cmd.id(), m);
                protos.put(cmd.id(), cmd.protoClass());
                Dispatcher.put(cmd.id(), this);
            }
        }
    }

    @Override
    public void exceute(Request request, Response response) {
        Method m = cmds.get(request.getCmd());
        Class<?> cl = protos.get(request.getCmd());
        if (m != null && cl != null) {
            try {
                Method parseMethod = cl.getMethod("parseFrom",
                        byte[].class);
                Object data = parseMethod.invoke(null, request.getBytes());
                request.setData(data);
                m.invoke(this, request);
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
