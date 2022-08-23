package com.yahaha.reggie.common;

/**
 * @author Yahaha
 * @date 2022-08-22 20:43:44
 * @description 基于ThreadLocal封装的工具类，用于保存和获取当前登陆的id(统一线程)
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     * @param id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
