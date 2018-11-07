/** 
 * Project Name:monitor_tuning 
 * File Name:MemoryController.java 
 * Package Name:com.vento.monitor_tuning.chapter2 
 * Date:2018年11月7日上午10:17:21 
 * Copyright (c) 2018, www.vento.com All Rights Reserved. 
 * 
 */
package com.vento.monitor_tuning.chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: MemoryController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2018年11月7日 上午10:17:21 <br/>
 * 
 * @author Toly
 * @version
 * @since JDK 1.8
 */
@RestController
public class MemoryController {

	private List<User> userList = new ArrayList<User>();
	private List<Class<?>> classList = new ArrayList<Class<?>>();

	/**
	 * 
	 * heap:(尝试进行堆内存溢出). <br/>
	 * TODO(-Xmx16M -Xms16M).<br/>
	 * Exception in thread "http-nio-8080-exec-1" java.lang.OutOfMemoryError: Java
	 * heap space
	 * 
	 * @author Toly
	 * @return
	 * @since JDK 1.8
	 */
	@GetMapping("/heap")
	public String heap() {
		int i = 0;
		while (true) {
			userList.add(new User(i++, UUID.randomUUID().toString()));
		}
	}

	/**
	 * 
	 * nonheap:(通过ASM包加载Class对象,尝试进行非堆内存溢出). <br/>
	 * TODO(-XX:MetaspaceSize=32M -XX:MaxMetaspaceSize=32M).<br/>
	 * Exception in thread "main" java.lang.OutOfMemoryError: Metaspace Exception in
	 * thread "ContainerBackgroundProcessor[StandardEngine[Tomcat]]"
	 * java.lang.OutOfMemoryError: Metaspace
	 * 
	 * @author Toly
	 * @return
	 * @since JDK 1.8
	 */
	@GetMapping("/nonheap")
	public String nonheap() {
		while (true) {
			classList.addAll(Metaspace.createClasses());
		}
	}
}
