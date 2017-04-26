package com.example.luckyzhang.baseutils.recyclerview.mvp.base.utils;



import java.lang.reflect.ParameterizedType;


public class InstanceUtil {
    /**
     * 通过实例化工厂去实例化相应类
     *
     * @param o   带泛型的对象
     * @param i   需要实例化的对象在泛型中的位置
     * @param <T> 返回实例的泛型类型
     * @return
     */
    public static <T> T getInstance(Object o, int i) {
        Class mClass = (Class<T>) ((ParameterizedType) (o.getClass()
                .getGenericSuperclass())).getActualTypeArguments()[i];
        try {
            return (T) InstanceFactory.create(mClass);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
