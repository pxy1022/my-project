package com.zhiyong.dormitory.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {
    private String code;
    private String msg;
    private T data;

    public R(T data) {
        this.data = data;
    }

    public static R success() {
        R r = new R<>();
        r.setCode("0");
        r.setMsg("成功");
        return r;
    }

    public static <T> R<T> success(T data) {
        R<T> r = new R<>(data);
        r.setCode("0");
        r.setMsg("成功");
        r.setData(data);
        return r;
    }

    public static R error(String code, String msg) {
        R r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

}
