package com.doctor;


import android.util.Log;

/**
 * 创建人   ：yuelinwang
 * 创建时间 ：2020/5/26
 * 描述     ：记录方法的开始和结束；方法的开始和结束必须成对出现，不然统计不准确。
 */
public class MethodRecordUtil {
    public static final String TAG = MethodRecordUtil.class.getName();

    /**
     * 方法开始
     * @param className
     * @param methodName
     * @param args
     */
    public static void onStart(String className, String methodName, Object[] args) {
        Log.d(TAG, "MethodRecordUtil--onStart: " + className + "  " + methodName + (args == null ? "" : " 参数：" + args.length));

    }

    public static void onStart(String className, String methodName) {
        onStart(className, methodName, null);

    }

    /**
     * 方法的开始会调用
     * @param signature
     * @param args
     */
    public static void onStart(String signature, Object[] args) {
    }

    /**
     * 没有参数的情况
     * @param signature
     */
    public static void onStart(String signature) {
    }

    /**
     * 方法的结束会调用
     */
    public static void onEnd() {
        Log.d(TAG, "MethodRecordUtil--onEnd: ");
    }

}
