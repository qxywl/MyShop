/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.po.request;

import java.io.Serializable;
import java.util.Map;

import com.zhaidou.framework.util.reflect.EmptyShowAnnotation;

/**
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-19     Created
 *
 * </pre>
 * @since 1.
 */
@EmptyShowAnnotation
public class RequestProductBaseObject implements Serializable{

    private static final long serialVersionUID = -5248448650881845719L;
    /**
     * 业务类别
     */
    @EmptyShowAnnotation
    String businessType;
    /**
     * 客户端类型 
     */
    String clientType;
    /**
     * 版本号
     */
    @EmptyShowAnnotation
    String version;
    /**
     * 附加参数
     */
    String additionalParam;
    /**
     * 请求对象
     */
    Map<String,Object> requestParams;
    
    public String getBusinessType() {
        return businessType;
    }
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    public String getClientType() {
        return clientType;
    }
    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
	public Map<String,Object> getRequestParams() {
		return requestParams;
	}
	public void setRequestParams(Map<String,Object> requestParams) {
		this.requestParams = requestParams;
	}
    public String getAdditionalParam() {
        return additionalParam;
    }
    public void setAdditionalParam(String additionalParam) {
        this.additionalParam = additionalParam;
    }
}
