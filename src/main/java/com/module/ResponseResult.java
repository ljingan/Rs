package com.module;

import com.module.constants.StateCode;
import org.json.JSONObject;

public class ResponseResult {

    public static String Http_Success = createHttpResult(StateCode.SUCCESS);
    public static String Http_Error = createHttpResult(StateCode.ERROR);

    public static String createHttpResult(StateCode value) {
        JSONObject json = new JSONObject();
        json.put("state", value.getState());
        json.put("msg", value.getMsg());
        return json.toString();
    }

    public static String createHttpResult(JSONObject json) {
        return json.toString();
    }

    public static String createHttpResult(String jsonString) {
        return jsonString;
    }

}
