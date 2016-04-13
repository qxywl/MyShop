/*
 * 文 件 名:  ChanglogDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-08-31
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.model;
import java.io.Serializable;

import com.zhaidou.common.model.AbstractBase2Model;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  mingbao
 * @version  [版本号, 2015-08-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChanglogModel extends AbstractBase2Model implements Serializable
{

	 /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private String        tableName;
    
    private Long        recordId;
    
    private String        recordCode;
    
    private String        columnName;
    
    private String        oldValue;
    
    private String        newValue;
    
    private String        createUserName;
    
    private String        updateUserName;
    
    private Long        isDeleted;
    
    
    

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public Long getRecordId()
    {
        return recordId;
    }

    public void setRecordId(Long recordId)
    {
        this.recordId = recordId;
    }

    public String getRecordCode()
    {
        return recordCode;
    }

    public void setRecordCode(String recordCode)
    {
        this.recordCode = recordCode;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public void setColumnName(String columnName)
    {
        this.columnName = columnName;
    }

    public String getOldValue()
    {
        return oldValue;
    }

    public void setOldValue(String oldValue)
    {
        this.oldValue = oldValue;
    }

    public String getNewValue()
    {
        return newValue;
    }

    public void setNewValue(String newValue)
    {
        this.newValue = newValue;
    }

    public String getCreateUserName()
    {
        return createUserName;
    }

    public void setCreateUserName(String createUserName)
    {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName()
    {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName)
    {
        this.updateUserName = updateUserName;
    }

    public Long getIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted)
    {
        this.isDeleted = isDeleted;
    }
}
