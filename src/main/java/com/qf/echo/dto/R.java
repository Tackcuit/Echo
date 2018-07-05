package com.qf.echo.dto;

import java.util.HashMap;

/**
 * Created by helen on 2018/6/12
 */
public class R extends HashMap<String, Object> {

    public R(){
        put("code", 0);
    }

    public static R ojbk(){
        R r = new R();
        r.put("code", 0);
        r.put("res",true);
        return r;
    }

    public static R ojbk(String msg){
        R r = new R();
        r.put("code", 0);
        r.put("msg", msg);
        r.put("res",true);
        return r;
    }

    public static R ojbk(int code, String msg){
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        r.put("res",true);
        return r;
    }

    public static R error(){
        R r = new R();
        r.put("code", 500);
        r.put("res",false);
        return r;
    }

    public static R error(String msg){
        R r = new R();
        r.put("code", 500);
        r.put("msg", msg);
        r.put("res",false);
        return r;
    }

    public static R error(int code, String msg){
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        r.put("res",false);
        return r;
    }

    public R put(String key, Object value){
        super.put(key, value);
        return this;
    }
}
