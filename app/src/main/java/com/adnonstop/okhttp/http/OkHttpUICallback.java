package com.adnonstop.okhttp.http;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

public class OkHttpUICallback {

    /**
     * 异步回调接口
     * T 是一个类而非clazz。
     */
    public static abstract class ResultCallback<T> {//ResultCallback就是参数化类型（parameterizedType）的类

        public Type mType;

        public ResultCallback() {
            mType = getSuperclassTypeParameter(getClass());//获取<>中参数的类型。
        }

        /**
         * 为了解决fastJson解析json串时需要的Type类型的参数。
         * 我是怎么做的呢？我通过在请求数据的方法里传递Class类型参数。
         * 当<>里的类型 参数 没有时，superclass instanceof Class 为 true;直接抛出异常。
         *
         * @param subclass
         * @return
         */
        private static Type getSuperclassTypeParameter(Class<?> subclass) {
            //genericSuperclass 翻译为中文就是“泛型类”
            Type superclass = subclass.getGenericSuperclass();//获取当前类的类型；举个例子当前类的类型若是ResultCallBack<>不带泛型或泛型参数为空，则superclasss是Class类型的；ResuleCallBack<TestData> 则为参数化类型：parameterizedType。
            if (superclass instanceof Class) {//为啥呢？啥意思？通过完整的类而非clazz获取到类的信息；所以当为clazz时报错。
                Log.i("type>>>", "getSuperclassTypeParameter: superclass instanceof Class");
                throw new RuntimeException("Miss type parameter");//参数类型缺失.直接抛出异常！
            }
            ParameterizedType parameterizedType = (ParameterizedType) superclass;//通过完整的类而非clazz获取到类的信息。
            Log.i("type>>>", "getSuperclassTypeParameter: " + parameterizedType.getActualTypeArguments()[0]);
            return parameterizedType.getActualTypeArguments()[0];//因为<>就一个类型 参数，故index = 0.
        }

        public abstract void onSuccess(T result);

        public abstract void onError(Call call, IOException e);
    }

    /**
     * 带有进度的上传、下载回调接口
     */
    public interface ProgressCallback {
        void onSuccess(Call call, Response response, String path);

        void onProgress(long byteReadOrWrite, long contentLength, boolean done);

        void onError(Call call, IOException e);
    }
}
