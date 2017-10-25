package com.stanley.utils;

import java.lang.reflect.Field;

/**
 * 反射的工具类
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/10/19
 **/
public class ReflectUtil {

    public static boolean setAttributeValue(Object obj, String fieldName, Object fieldValue){
        Class<?> clazz = obj.getClass();
        if(checkAttrsExist(clazz, fieldName)){
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(obj, fieldValue);
                return true;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean checkAttrsExist(Class<?> clazz, String attr){
        Field[] fields = clazz.getDeclaredFields();
        //属性为private时，需要将setAccessible设置为true才能获取
        Field.setAccessible(fields,true);
        for(Field field : fields)   {
            if(field.getName().equals(attr))
                return true;
        }
        return false;
    }

    public static boolean isArrayContain(String[] arr, String targetValue){
        for(String s : arr){
            if(s.equals(targetValue))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
       /* SysRole sysRole = new SysRole();
        setObjectValue(sysRole,"createId", 12345);
        setObjectValue(sysRole,"createDt", new Timestamp(System.currentTimeMillis()));
        System.out.printf("sysRole",sysRole);*/
    }

}
