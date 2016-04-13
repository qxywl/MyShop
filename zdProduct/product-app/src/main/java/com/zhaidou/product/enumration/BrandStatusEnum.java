package com.zhaidou.product.enumration;

import java.util.Hashtable;



/**
 * 品牌状态 1:启用 2:禁用 3:已删除
 * */
public enum BrandStatusEnum {
	
	enable("1","enable","启用"), //启用
	disable("2","disable","禁用"),//禁用
	delete("3","delete","已删除"),//已删除
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

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static Hashtable<String, BrandStatusEnum> getHashMap() {
		return aliasEnums;
	}
	
	private static Hashtable<String, BrandStatusEnum> aliasEnums;
	
	BrandStatusEnum(String code ,String name,String desc){
		init(code,name,desc);
	}
	
	private void init(String code ,String name,String desc){
       this.code = code;
       this.name = name;
       this.desc = desc;
       synchronized (this.getClass()) {
           if (aliasEnums == null) {
               aliasEnums = new Hashtable<String, BrandStatusEnum>();
           }
       }
       aliasEnums.put(code, this);
	}


	public static void main(String[] args) {
		System.out.println(BrandStatusEnum.enable.getInt());
	 
	}
	

}
