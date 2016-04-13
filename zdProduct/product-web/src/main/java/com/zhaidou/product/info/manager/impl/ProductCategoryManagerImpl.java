package com.zhaidou.product.info.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zhaidou.common.util.Response;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.category.service.CategoryService;
import com.zhaidou.product.category.service.Category_Type;
import com.zhaidou.product.info.manager.ProductCategoryManager;

@Service("productCategoryManager")
public class ProductCategoryManagerImpl implements ProductCategoryManager {
    private static Logger log = Logger.getLogger(ProductCategoryManagerImpl.class);
   
    @Resource
    private CategoryService categoryService;
    /**
     * 所有一级基础分类
     *
     * @param productModel
     * @return
     */
    @Override
    public List<CategoryPO> getCategoBaseryList(CategoryPO categoryPo) {
        List<CategoryPO> listCategory = null;
        //获取基础分类
        Response response = categoryService.queryDirectSonListByParentCode(categoryPo, Category_Type.BASE_CATEGORY);
        if("200".equals(response.getCode())){
            listCategory = (List<CategoryPO>)response.getModel();
        }else{
            log.error("获取 基础分类失败!");
        }
        
        return listCategory;
    }
    
    /**
     * 分类编码，获取该分类对象
     *
     * @param productModel
     * @return
     */
    @Override
    public CategoryPO getCategory(CategoryPO categoryPo) {
        categoryPo = categoryService.queryOne(categoryPo, Category_Type.BASE_CATEGORY);
        return categoryPo;
    }
    
    /**
     * 所有运营分类
     *
     * @param productModel
     * @return
     */
    @Override
    public List<CategoryPO> getCategorySaleList() {
        List<CategoryPO> listCategory = null;
        listCategory = categoryService.queryAll(Category_Type.SALE_CATEGORY);
        return listCategory;
    }
    
    
	/**
     * @Description: 查询等级分类下的所有分类,如果等级分类为空，则返回所有
     * @param level 等级分类
     * @param type 类型
     * @return List<CategoryModel>
	 * @throws Exception 
     * */
    @Override
    public List<CategoryPO> getAllByLevel(Integer level) throws Exception {
        
        return categoryService.selectAllByLevel(level, Category_Type.SALE_CATEGORY);
        
    }

}
