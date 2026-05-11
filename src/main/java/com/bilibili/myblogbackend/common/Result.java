/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.common.Result
 *  lombok.Generated
 */
package com.bilibili.myblogbackend.common;

import lombok.Generated;

public class Result {
    private static final Integer code = 200;
    private String msg;
    private Object data;

    public static Result success(Object obj) {
        return new Result("成功", obj);
    }

    public static Result error(String str) {
        return new Result(str, (Object)null);
    }

    @Generated
    public String getMsg() {
        return this.msg;
    }

    @Generated
    public Object getData() {
        return this.data;
    }

    @Generated
    public void setMsg(final String msg) {
        this.msg = msg;
    }

    @Generated
    public void setData(final Object data) {
        this.data = data;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Result)) {
            return false;
        } else {
            Result other = (Result)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof Result;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getMsg();
        return "Result(msg=" + var10000 + ", data=" + String.valueOf(this.getData()) + ")";
    }

    @Generated
    public Result(final String msg, final Object data) {
        this.msg = msg;
        this.data = data;
    }
}

