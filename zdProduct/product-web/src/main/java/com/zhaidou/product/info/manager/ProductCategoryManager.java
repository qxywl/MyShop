package com.zhaidou.product.info.manager;

import java.util.List;

import com.zhaidou.product.category.model.CategoryPO;

public interface ProductCategoryManager {
    
    /**
     * 所有一级基础分类
     *
     * @param productModel
     * @return
     */
    public List<CategoryPO> getCategoBaseryList(CategoryPO categoryPo);
    /**
     * 分类编码，获取该分类对象
     *
     * @param productModel
     * @return
     */
    public CategoryPO getCategory(CategoryPO categoryPo);
    
    /**
     * 所有运营分类
     *
     * @param productModel
     * @return
     */
    public List<CategoryPO> getCategorySaleList();
    
    /**
     * @Description: 查询等级分类下的所有分类,如果等级分类为空，则返回所有
     * @param level 等级分类
     * @param type 类型
     * @return List<CategoryModel>
     * */
    public List<CategoryPO> getAllByLevel(Integer level) throws Exception;
}
