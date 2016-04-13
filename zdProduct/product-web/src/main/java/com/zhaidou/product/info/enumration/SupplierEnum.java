package com.zhaidou.product.info.enumration;

import java.util.Hashtable;



/**
 * 品牌状态 1:启用 2:禁用 3:已删除
 * */
public enum SupplierEnum {
	
	activation("1","activation","激活"), //启用
	unactivated("2","unactivated","未激活"),//禁用
	disable("3","disable","作废"),//已删除
	;
	private String code; 
	private String name;
	private String desc;



	
	public Integer getInt() {
		return Integer.valueOf(code);
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}

	public void mian(){

	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static Hashtable<String, SupplierEnum> getHashMap() {
		return aliasEnums;
	}
	
	private static Hashtable<String, SupplierEnum> aliasEnums;
	
	SupplierEnum(String code ,String name,String desc){
		init(code,name,desc);
	}
	
	private void init(String code ,String name,String desc){
       this.code = code;
       this.name = name;
       this.desc = desc;
       synchronized (this.getClass()) {
           if (aliasEnums == null) {
               aliasEnums = new Hashtable<String, SupplierEnum>();
           }
       }
       aliasEnums.put(code, this);
	}
	

}
