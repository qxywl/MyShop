/*
 * 文 件 名:  ProductImageDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.po.base;

import java.io.Serializable;
import java.util.List;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProductImagePO implements Serializable {

    private static final long serialVersionUID = 1L;

   /* private Long        productImageId;//唯一标识
    private Long        productSkuId;//sku id
    private String      productSkuCode;//sku code

    private String      imageUrl;//图片地址
    private Long        level;//第几张
    private String        type;//图的类型
*/   
    /**
	 * 图片名称
	 */
	private String imageName;	
	
	/**
	 * 图片版本信息
	 */
	private String imageVersion;	
	
	/**
	 * 图片类型
	 */
	private String imageType;
	
	/**
	 * 图片完整路径
	 */
	private String imagePath;

	/**
	 * 排序号
	 */
	private Long imageIndex;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageVersion() {
		return imageVersion;
	}

	public void setImageVersion(String imageVersion) {
		this.imageVersion = imageVersion;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Long getImageIndex() {
		return imageIndex;
	}

	public void setImageIndex(Long imageIndex) {
		this.imageIndex = imageIndex;
	}
    
    
    
  

    
}
