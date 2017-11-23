package com.module.constants;

public enum StateCode {
    SUCCESS(1, "success"),
    ERROR(0, "error");

    private String msg;
    private int state;

    private StateCode(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }
}
