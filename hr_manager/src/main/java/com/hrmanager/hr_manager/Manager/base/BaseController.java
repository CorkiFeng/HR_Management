package com.hrmanager.hr_manager.Manager.base;

import java.lang.reflect.ParameterizedType;

public abstract class BaseController<T> {

    public BaseController(){
        //获得泛型的真实类型
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();

        //获得全类名
        Class<?> modelClass = (Class<?>) type.getActualTypeArguments()[0];

        //活动类名
        String className = modelClass.getSimpleName();


    }
}
