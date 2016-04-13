/*
 * 文 件 名:  ProductBrandDTO.java
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
 * 对ProductBrandModel 做一个中间层的数据处理以显示出来
 * 
 */
public class ProductBrandPO  implements Serializable
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    /*****  基本字段  *******/
    private Long      brandId;   //品牌id
    private String    brandCode; //品牌编号
    private String    brandName; //品牌名称
    private String    brandEname; //品牌别名
    private String    brandInfo; //品牌说明
    private String    brandCountry; //品牌国家
    private String    brandLogo; //品牌logo
    private Integer   brandStatus; //状态 1:启用 2:禁用 3:已删除
    private String    flagshipStoreCode; //品牌旗舰店铺编号
    private Integer   storeCertificationType; //旗舰店认证状态  1:已认证 2:未认证
    private String    brandStory; //品牌故事
    private String    updateUser; //最后修改者
    private String    creator; //创建者
    
    /************** 添加的辅助字段  ************/
    private List<Integer> statusList; //状态集合
    private String brandStatusPO;//状态 以文字显示
    private String storeCertificationTypePO; //旗舰店认证状态 以文字显示
    private String brandCountryPO; //品牌国家名称显示
    
    public ProductBrandPO(){
    	
    }
    
    public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandCode()
    {
        return brandCode;
    }

    public void setBrandCode(String brandCode)
    {
        this.brandCode = brandCode;
    }
    
    public String getBrandName()
    {
        return brandName;
    }

    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }

    public String getBrandEname()
    {
        return brandEname;
    }

    public void setBrandEname(String brandEname)
    {
        this.brandEname = brandEname;
    }

    public String getBrandInfo()
    {
        return brandInfo;
    }

    public void setBrandInfo(String brandInfo)
    {
        this.brandInfo = brandInfo;
    }

    public String getBrandStory()
    {
        return brandStory;
    }

    public void setBrandStory(String brandStory)
    {
        this.brandStory = brandStory;
    }

    public String getBrandCountry()
    {
        return brandCountry;
    }

    public void setBrandCountry(String brandCountry)
    {
        this.brandCountry = brandCountry;
    }

    public String getBrandLogo()
    {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo)
    {
        this.brandLogo = brandLogo;
    }

    public Integer getBrandStatus() {
		return brandStatus;
	}

	public void setBrandStatus(Integer brandStatus) {
		this.brandStatus = brandStatus;
	}

	public String getFlagshipStoreCode()
    {
        return flagshipStoreCode;
    }

    public void setFlagshipStoreCode(String flagshipStoreCode)
    {
        this.flagshipStoreCode = flagshipStoreCode;
    }

    public Integer getStoreCertificationType() {
		return storeCertificationType;
	}

	public void setStoreCertificationType(Integer storeCertificationType) {
		this.storeCertificationType = storeCertificationType;
	}

	public String getUpdateUser()
    {
        return updateUser;
    }

    public void setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

	public List<Integer> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Integer> statusList) {
		this.statusList = statusList;
	}

	public String getBrandStatusPO() {
		return brandStatusPO;
	}

	public void setBrandStatusPO(String brandStatusPO) {
		this.brandStatusPO = brandStatusPO;
	}

	public String getStoreCertificationTypePO() {
		return storeCertificationTypePO;
	}

	public void setStoreCertificationTypePO(String storeCertificationTypePO) {
		this.storeCertificationTypePO = storeCertificationTypePO;
	}

	public String getBrandCountryPO() {
		return brandCountryPO;
	}

	public void setBrandCountryPO(String brandCountryPO) {
		this.brandCountryPO = brandCountryPO;
	}
    
	
    
}
