/** 
 * Project Name:monitor_tuning 
 * File Name:CPUContoller.java 
 * Package Name:com.vento.monitor_tuning.chapter2 
 * Date:2018年11月7日下午2:56:36 
 * Copyright (c) 2018, www.vento.com All Rights Reserved. 
 * 
 */
package com.vento.monitor_tuning.chapter2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: CPUContoller <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2018年11月7日 下午2:56:36 <br/>
 * 
 * @author Toly
 * @version
 * @since JDK 1.8
 */
@RestController
public class CPUContoller {

	/**
	 * 
	 * loop:(死循环示例). <br/> 
	 * 
	 * @author Toly
	 * @return 
	 * @since JDK 1.8
	 */
	@RequestMapping("/loop")
	public List<Long> loop() {
		String data = "{\"data\":[{\"partnerid\":]}}";
		;
		return getPartnerIdsFromJson(data);
	}

	/**
	 * 
	 * getPartnerIdsFromJson:(从JSON中获取PartnerIds). <br/> 
	 * TODO(存在死循环).<br/> 
	 * 
	 * @author Toly
	 * @param data
	 * @return 
	 * @since JDK 1.8
	 */
	public static List<Long> getPartnerIdsFromJson(String data) {

		// {\"data\":[{\"partnerid\":982,\"count\":\"10000\",\"cityid\":\"11\"},{\"partnerid\":983,\"count\":\"10000\",\"cityid\":\"11\"},{\"partnerid\":984,\"count\":\"10000\",\"cityid\":\"11\"}]}
		// 上面是正常的数据
		List<Long> list = new ArrayList<Long>(2);
		if (data == null || data.length() <= 0) {
			return list;
		}

		int datapos = data.indexOf("data");
		if (datapos < 0) {
			return list;
		}

		int leftBracket = data.indexOf("[", datapos);
		int rightBracket = data.indexOf("]", datapos);
		if (leftBracket < 0 || rightBracket < 0) {
			return list;
		}

		String partners = data.substring(leftBracket + 1, rightBracket);
		if (partners == null || partners.length() <= 0) {
			return list;
		}

		while (partners != null && partners.length() > 0) {
			int idpos = partners.indexOf("partnerid");
			if (idpos < 0) {
				break;
			}
			int colonpos = partners.indexOf(":", idpos);
			int commapos = partners.indexOf(",", idpos);

			if (colonpos < 0 || commapos < 0) {
				// partners = partners.substring(idpos+"partnerid".length());//1
				continue;
			}

			String pid = partners.substring(colonpos + 1, commapos);
			if (pid == null || pid.length() <= 0) {
				// partners = partners.substring(idpos+"partnerid".length());//2
				continue;
			}

			try {
				list.add(Long.parseLong(pid));
			} catch (Exception e) {
				// do nothing
			}
			partners = partners.substring(commapos);
		}
		return list;
	}

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	/**
	 * 
	 * deadlock:(死锁示例). <br/> 
	 * 
	 * @author Toly
	 * @return 
	 * @since JDK 1.8
	 */
	@RequestMapping("/deadlock")
	public String deadlock() {
		new Thread(() -> {
			synchronized (lock1) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
				synchronized (lock2) {
					System.out.println("Thread1 over");
				}
			}
		}).start();
		new Thread(() -> {
			synchronized (lock2) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
				synchronized (lock1) {
					System.out.println("Thread2 over");
				}
			}

		}).start();
		return "deadlock";
	}

}
