package com.zhaidou.product.po.base;

import java.io.Serializable;

public class JointImagePO  implements Serializable{
	
	
	private static final long serialVersionUID = 18582070461006763L;
	
	private String smallImage; //小图
	private String middleImage; //中图
	private String bigImage;//大图
	
	public String getSmallImage() {
		return smallImage;
	}
	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}
	public String getMiddleImage() {
		return middleImage;
	}
	public void setMiddleImage(String middleImage) {
		this.middleImage = middleImage;
	}
	public String getBigImage() {
		return bigImage;
	}
	public void setBigImage(String bigImage) {
		this.bigImage = bigImage;
	}
	
	
	
	

}
