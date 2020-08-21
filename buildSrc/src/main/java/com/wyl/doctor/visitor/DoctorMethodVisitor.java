package com.wyl.doctor.visitor;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * 创建人   : yuelinwang
 * 创建时间 : 2020/6/8
 * 描述    : 处理方法
 */
public class DoctorMethodVisitor extends AdviceAdapter {
    public static final String TAG = DoctorMethodVisitor.class.getName();
    private static final int MAX_SECTION_NAME_LEN = 127;

    private Type[] paramTypes;//方法参数的类型
    private String classFullName;
    private String methodName;//方法名
    private String methodSignature;

    protected DoctorMethodVisitor(int api, MethodVisitor mv, int access, String name, String desc, String classFullName) {
        super(api, mv, access, name, desc);
        paramTypes = Type.getArgumentTypes(desc);
        this.classFullName = classFullName;
        this.methodName = name;
        methodSignature = generateMethodSignature(classFullName, name, desc);
    }

    /**
     * 生成方法的签名
     * @param className
     * @param methodName
     * @param desc
     * @return
     */
    private String generateMethodSignature(String className, String methodName, String desc) {
        String newClassName = className.replaceAll("/", ".");
        return newClassName + "." + methodName + desc;
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
        if (methodSignature == null) {
            methodSignature = "";
        }
        //将类名加载到操作数栈
        int len =  methodSignature.length();
        if (len > MAX_SECTION_NAME_LEN) {
            methodSignature = methodSignature.substring(len - MAX_SECTION_NAME_LEN);
        }
        mv.visitLdcInsn(methodSignature);
        //将方法名加载到操作数栈
        mv.visitMethodInsn(INVOKESTATIC, "com/doctor/TraceTag", "i", "(Ljava/lang/String;)V", false);

    }

    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);
        mv.visitMethodInsn(INVOKESTATIC, "com/doctor/TraceTag", "o", "()V", false);
    }
}
