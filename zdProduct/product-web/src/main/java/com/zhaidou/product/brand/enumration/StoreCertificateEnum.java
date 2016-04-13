package com.zhaidou.product.brand.enumration;

import java.util.Hashtable;



/**
 * 旗舰店认证状态  1:已认证 2:未认证
 * */
public enum StoreCertificateEnum {
	
	certified("1","certified","已认证"), //已认证
	uncertified("2","uncertified","未认证"),//未认证
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

	public static Hashtable<String, StoreCertificateEnum> getHashMap() {
		return aliasEnums;
	}
	
	private static Hashtable<String, StoreCertificateEnum> aliasEnums;
	
	StoreCertificateEnum(String code ,String name,String desc){
		init(code,name,desc);
	}
	
	private void init(String code ,String name,String desc){
       this.code = code;
       this.name = name;
       this.desc = desc;
       synchronized (this.getClass()) {
           if (aliasEnums == null) {
               aliasEnums = new Hashtable<String, StoreCertificateEnum>();
           }
       }
       aliasEnums.put(code, this);
	}



	public static void main(String[] args) {
		System.out.println(StoreCertificateEnum.certified.getInt());
	 
	}
	

}
