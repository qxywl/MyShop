package com.zhaidou.product.info.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zhaidou.product.attributies.model.ProductCateAttrGroupModel;
import com.zhaidou.product.attributies.service.ProductAttrGroupService;
import com.zhaidou.product.info.manager.ProductAttrbuteManager;


@Service("productAttrbuteManager")
public class ProductAttrbuteManagerImpl implements ProductAttrbuteManager {
    private static Logger logger = Logger.getLogger(ProductAttrbuteManagerImpl.class);
    
    @Resource
    private ProductAttrGroupService productAttrGroupService;
    
    /**
     * 根据基础分类 code 获取属性组，属性项
     *
     * @param categoryCode
     * @return
     */
    @Override
    public List<ProductCateAttrGroupModel> getAttrByCategory(String categoryCode) {
        List<ProductCateAttrGroupModel> listAttr = null;
        List<ProductCateAttrGroupModel> listCateAttr = new ArrayList<ProductCateAttrGroupModel>();
        try {
            listAttr = productAttrGroupService.getProductAttrValByCateCode(categoryCode);
        } catch (Exception e) {
            logger.error("根据基础分类 code 获取属性组，属性项 异常！");
            e.printStackTrace();
        }
        String attrValue = "";
        ProductCateAttrGroupModel temp = null;
        
        boolean flag = true;
        List<Integer> listInt = new ArrayList<Integer>();
        
        if(listAttr!=null && listAttr.size()>0){
            for (int i = 0; i < listAttr.size(); i++)
            {
                for(int x:listInt){
                    if(i==x){
                        flag=false;
                    }
                }
                
                if(flag){
                    temp = listAttr.get(i);
                    attrValue = attrValue+temp.getAttrValue();
                    if(temp.getAttrDesign()!=null && temp.getAttrDesign()==1){
                        for (int j = i + 1; j < listAttr.size(); j++)
                        {
                            if (temp.getAttrCode().equals(listAttr.get(j).getAttrCode()))
                            {
                                attrValue = attrValue+","+listAttr.get(j).getAttrValue();
                                listInt.add(j);
                            }
                        }
                        temp.setAttrValue(attrValue);
                    }
                       listCateAttr.add(temp);
                }
                attrValue="";
                flag = true;
            }
        }
        return listCateAttr;
    }
    
}
