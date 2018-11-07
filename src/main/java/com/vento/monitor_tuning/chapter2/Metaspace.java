/** 
 * Project Name:monitor_tuning 
 * File Name:Metaspace.java 
 * Package Name:com.vento.monitor_tuning.chapter2 
 * Date:2018年11月7日上午10:39:16 
 * Copyright (c) 2018, www.vento.com All Rights Reserved. 
 * 
 */    
package com.vento.monitor_tuning.chapter2;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/** 
 * ClassName: Metaspace <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: 2018年11月7日 上午10:39:16 <br/> 
 * 
 * @author Toly 
 * @version  
 * @since JDK 1.8 
 */
public class Metaspace extends ClassLoader{
	
	public static List<Class<?>> createClasses() {
		//类持有
		List<Class<?>> classes = new ArrayList<Class<?>>();
		
		for (int i=0; i<10000000; i++) {
			ClassWriter cw = new ClassWriter(0);
			// 定义一个类名称为Class(i)，访问域为public, 父类为java.lang.Object
			cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
			// 定义一个构造函数<init>方法
			MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null,null);
			// 第一个指令为加载this
			mw.visitVarInsn(Opcodes.ALOAD,0);
			// 第二个指令为调用父类Object的构造函数
			mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			// 第三条指令为return
			mw.visitInsn(Opcodes.RETURN);
			mw.visitMaxs(1, 1);
			mw.visitEnd();
			Metaspace test = new Metaspace();
			byte[] code = cw.toByteArray();
			// 定义类
			Class<?> exampleClass = test.defineClass("Class"+i,code, 0,code.length);
			classes.add(exampleClass);
		}
		return classes;
	}
	

}
 