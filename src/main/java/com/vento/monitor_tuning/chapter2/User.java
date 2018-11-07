/** 
 * Project Name:monitor_tuning 
 * File Name:User.java 
 * Package Name:com.vento.monitor_tuning.chapter2 
 * Date:2018年11月7日上午10:20:36 
 * Copyright (c) 2018, www.vento.com All Rights Reserved. 
 * 
 */    
package com.vento.monitor_tuning.chapter2; 
/** 
 * ClassName: User <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: 2018年11月7日 上午10:20:36 <br/> 
 * 
 * @author Toly 
 * @version  
 * @since JDK 1.8 
 */
public class User {

	private int id;
	private String name;
	/** 
	 * Creates a new instance of User. 
	 * 
	 * @param id
	 * @param name 
	 */
	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	/** 
	 * id. 
	 * 
	 * @return  the id 
	 * @since   JDK 1.8
	 */
	public int getId() {
		return id;
	}
	/** 
	 * id. 
	 * 
	 * @param   id    the id to set 
	 * @since   JDK 1.8
	 */
	public void setId(int id) {
		this.id = id;
	}
	/** 
	 * name. 
	 * 
	 * @return  the name 
	 * @since   JDK 1.8
	 */
	public String getName() {
		return name;
	}
	/** 
	 * name. 
	 * 
	 * @param   name    the name to set 
	 * @since   JDK 1.8
	 */
	public void setName(String name) {
		this.name = name;
	}
}
 