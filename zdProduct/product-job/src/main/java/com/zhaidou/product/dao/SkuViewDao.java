package com.zhaidou.product.dao;

import java.util.List;

public interface SkuViewDao {
    
    public void deleteSkuViewBySkuCode(List<String> codes)throws Exception;
    
    public void addSkuViewList(List<String> codes)throws Exception;
}
