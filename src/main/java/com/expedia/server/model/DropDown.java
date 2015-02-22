package com.expedia.server.model;

public class DropDown {
	
	private int key ;
	private String value ;
	
	
	DropDown()
	{
		
	}
	
	public DropDown(int key,String value)
	{
		this.key = key;
		this.value = value;
	}
	/**
	 * @return the key;
	 */
	public int getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(int key) {
		this.key = key;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	

}
