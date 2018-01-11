package com.common.handler;

import com.common.net.Request;
import com.common.net.Response;

public interface Handler {
    void exceute(Request request, Response response);

    void clear();
}
