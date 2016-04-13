package com.zhaidou.product.category.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.common.util.Response;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.imageservice.manager.ImagePathManager;
import com.zhaidou.imageservice.model.request.Image;
import com.zhaidou.imageservice.model.request.UploadImageRequestPO;
import com.zhaidou.imageservice.model.response.UploadImageResponsePO;
import com.zhaidou.product.attributies.model.ProductAttrGroupModel;
import com.zhaidou.product.attributies.service.ProductAttrGroupService;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.category.service.CategoryService;
import com.zhaidou.product.category.service.Category_Type;
import com.zhaidou.product.category.util.ImgOwnerEnum;
import com.zhaidou.product.category.util.Utils;
import com.zhaidou.product.info.service.ProductMallViewService;

/**
 * @Title: BaseCategoryController.java 
 *
 * @Package com.teshehui.product.category.controller 
 *
 * @Description:   基础分类Controller
 *
 * @author lvshuding 
 *
 * @date 2015年3月25日 上午10:27:29 
 *
 * @version V1.0
 */
@Controller("baseCategoryController")
@RequestMapping("/base-category")
public class BaseCategoryController extends BaseController{

	private static final Log logger = LogFactory.getLog(BaseCategoryController.class);

	private final static String CURRENT_CATE_ID="currentCateId";
	
	@Value("#{propertyConfigurerForProject2['img_tmp_dir']}")
	private String IMG_TMP_DIR = "image/banner-info"; // 图片文件目录

	@Value("#{propertyConfigurerForProject2['img_tmp_dir_rel']}")
	private String IMG_TMP_DIR_Rel; // 图片文件目录
	
	@Resource
	private ImagePathManager imagePathManager;

	@Autowired
	private CategoryService categoryService;
	
	@Resource
	private ProductMallViewService productMallViewService;

	@Resource
	private ProductAttrGroupService productAttrGroupService;
	
	/**
	 * @Description: 运营分类自动挂载规则时使用
	 * @param req
	 * @param rsp
	 * @param model
	 * return String
	 */
	@RequestMapping("/tree")
	public String toTree(HttpServletRequest req,HttpServletResponse rsp,Model model){
		
		List<CategoryPO> poList = categoryService.queryAll(Category_Type.BASE_CATEGORY);
		
		model.addAttribute("orgTreeStr",Utils.buildJsonFormTree(poList,req.getParameter(CURRENT_CATE_ID)));
		
		return "category/tree_sale_category";
	}
	
	
	@RequestMapping("/main")
	public String toMain(HttpServletRequest req,HttpServletResponse rsp,Model model){
		
		List<CategoryPO> poList = categoryService.queryAll(Category_Type.BASE_CATEGORY);
		
		model.addAttribute("orgTreeStr",Utils.buildJsonFormTree(poList,req.getParameter(CURRENT_CATE_ID)));
		
		return "category/base_category";
	}
	
	@RequestMapping("/to-add")
	public String toAdd(CategoryPO po,HttpServletRequest req,HttpServletResponse rsp,Model model){
		
		model.addAttribute(CURRENT_CATE_ID, po.getParentId());
		
		return "category/base_category_add";
	}
	
	/**
     * 转至图片上传页面
     */
    @RequestMapping(value="/to_image_upload",method = { RequestMethod.GET, RequestMethod.POST })
    public String toImageUpload(Model model){
    	model.addAttribute("frmSubmitUrl", "/base-category/uploadImage");
    	return "category/category_image_upload";
    }
    
    /**
     * @Description:   上传图片
     * @param request
     * @param response
     * @param file
     * return String
     */
//    @ResponseBody
//    @RequestMapping(value="/uploadImage",method={ RequestMethod.POST },produces = {"application/json;charset=UTF-8"})
    public AjaxObject uploadImage(HttpServletRequest request,HttpServletResponse response,
    		@RequestParam("file")   MultipartFile file, String categoryCode){
    	 
//    	 String image = "image/category/base"; //图片文件
    	 
//    	 String returnStr = Utils.uploadImage(image, file,request).toString();
    	 
    	 AjaxObject ajaxObj = Utils.uploadImage(IMG_TMP_DIR, file, request);
    	 
 		if(ajaxObj.getStatusCode() ==200 ){
 			RequestObject requestObj = new RequestObject();
 			UploadImageRequestPO requestPo = new UploadImageRequestPO();
// 			String bannerCode = NumberingRuleUtil.newBaseCode("B");
 			requestPo.setTmpPath(IMG_TMP_DIR_Rel);
 			requestPo.setNeedlevel(0);
 			requestPo.setRelationCode(categoryCode);
 			requestPo.setRelationType(ImgOwnerEnum.BASE_CATEGORY.getValue());
 			Image image = new Image(1,ajaxObj.getMessage(),"1");
 			List<Image> imageList = new ArrayList<Image>();
 			imageList.add(image);
 			requestPo.setImages(imageList);
 			requestObj.setRequestParams(requestPo);
 			ResponseObject responseObj = imagePathManager.uploadImage(requestObj);
 			if(responseObj.getCode() == -1){
 				return AjaxObject.newError(responseObj.getMsg());
 			}
 			UploadImageResponsePO responsePO = (UploadImageResponsePO)responseObj.getData();
 			String imgType = responsePO.getImageType();
 			String imgPath = responsePO.getImagePath();
 			List<Image> images = responsePO.getImages();
 			if(images != null && images.size() > 0){
 				String path = imgPath + "/" + images.get(0).getImageName() + imgType;
 				ajaxObj.setMessage(path);
 			}
// 			logger.info(JSON.toJSONString(responseObj));
 		}
 		return ajaxObj;
    	
    }
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(CategoryPO po,HttpServletRequest req,HttpServletResponse rsp, @RequestParam("imageFile")   MultipartFile file){//,RedirectAttributes attr){
		
		//这里要获取登录用户的用户名
		String userName = Utils.getUserNameFromRequest(req);
		if(po.getParentId() == null || po.getParentId().longValue() == 0l){
			if(!super.allowOperateLevel1(userName)){
				return AjaxObject.newError("你没有权限添加1级基础分类").toString();
			}
		}
		CategoryPO tmpPo = categoryService.selectByParentIdAndCategoryName(po, Category_Type.BASE_CATEGORY);
		if(tmpPo != null){
			return AjaxObject.newError("同一父分类下不能添加同名的分类").toString();
		}
		
		String categoryCode;
		try {
			CategoryPO parent = new CategoryPO();
			parent.setId(po.getParentId());
			categoryCode = categoryService.buildCurrentCode(parent, Category_Type.BASE_CATEGORY);
		} catch (Exception e) {
			return AjaxObject.newError(e.getMessage()).toString();
		}
		
		if(file.getSize() > 0){
			AjaxObject result = uploadImage(req, rsp, file, categoryCode);
			if(result.getStatusCode() != 200){
				return result.toString();
			}
			po.setImageUrl(result.getMessage());
		}
		po.setCategoryCode(categoryCode);
		po.setCreateUser(userName);
		po.setCreateDate(new Date(System.currentTimeMillis()));
		po.setUpdateUser(userName);
		po.setUpdateDate(po.getCreateDate());
		
		po.setShowFlag(1);
		po.setDeleteFlag(0);
		
		Response result = categoryService.insert(po, Category_Type.BASE_CATEGORY);
//		attr.addAttribute(MSG_NAME, result.getMsg());
//		attr.addAttribute(CURRENT_CATE_ID, po.getId());
//		
		return AjaxObject.newOk(result.getMsg()).toString();
		
		//return "redirect:main.do";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String toDelete(String delIds,HttpServletRequest req,HttpServletResponse rsp){
		
		if(StringUtils.isBlank(delIds)){
			return AjaxObject.newErrorForward("没有选中要删除的基础分类", "base-category/main").toString();
		}
		//这里要获取登录用户的用户名
		String userName = Utils.getUserNameFromRequest(req);
		try{
			if(categoryService.checkExistLevel1ByIds(delIds, Category_Type.BASE_CATEGORY)){
				if(!super.allowOperateLevel1(userName)){
					return AjaxObject.newErrorForward("你没有权限删除1级基础分类", "base-category/main").toString();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return AjaxObject.newErrorForward("后台异常", "base-category/main").toString();
		}
		
		AjaxObject checkResult = this.noProductsInSubCategory(delIds);
		if(checkResult.getStatusCode() == AjaxObject.STATUS_CODE_FAILURE){
			return checkResult.toString();
		}
		
		boolean deleteOne = true;
		if(delIds.indexOf(",") > -1){
			deleteOne = false;
		}
		
		CategoryPO po = new CategoryPO();
		po.setDeleteFlag(1);
		po.setUpdateUser(userName);
		po.setUpdateDate(new Date(System.currentTimeMillis()));
		
		Response result = null;
		if(deleteOne){
			po.setId(Long.valueOf(delIds));
			result = categoryService.delete(po, Category_Type.BASE_CATEGORY);
		}else {
			result = categoryService.deleteBatch(po,delIds, Category_Type.BASE_CATEGORY);
		}
		return AjaxObject.newOkForward(result.getMsg(), "base-category/main").toString();
	}
	
	@RequestMapping("/to-update")
	public String toUpdate(CategoryPO po,HttpServletRequest req,HttpServletResponse rsp,Model model){
		
		po.setId(po.getParentId());
		po.setParentId(null);
		try{
			po = categoryService.queryOne(po, Category_Type.BASE_CATEGORY);
			List<ProductAttrGroupModel> attrGroupList = productAttrGroupService.getAttrGroupListByCategoryId(po.getId());
			model.addAttribute("curPo", po);
			model.addAttribute("attrGroupList", attrGroupList);
		}catch(ZhaidouRuntimeException e){
			model.addAttribute("errorMsg", e.getMessage());
		}
		return "category/base_category_update";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(CategoryPO po,HttpServletRequest req,HttpServletResponse rsp,RedirectAttributes attr, @RequestParam("imageFile")   MultipartFile file){
		
		//这里要获取登录用户的用户名
		String userName = Utils.getUserNameFromRequest(req);
		
		CategoryPO tmp1 = new CategoryPO();
		tmp1.setId(po.getId());
		tmp1 = categoryService.queryOne(tmp1, Category_Type.BASE_CATEGORY);
		
		if(tmp1!=null && tmp1.getParentCode() == null &&!super.allowOperateLevel1(userName)){
			return AjaxObject.newErrorForward("你没有权限修改1级基础分类","base-category/main").toString();
		}
		
		po.setParentId(tmp1.getParentId());
		CategoryPO tmpPo = categoryService.selectByParentIdAndCategoryName(po, Category_Type.BASE_CATEGORY);
		if(tmpPo != null  && tmpPo.getId().longValue() != po.getId().longValue()){
			return AjaxObject.newError("同一父分类下不能存在同名的分类").toString();
		}
		
		if(file.getSize() > 0){
			AjaxObject result = uploadImage(req, rsp, file, tmp1.getCategoryCode());
			if(result.getStatusCode() != 200){
				return result.toString();
			}
			po.setImageUrl(result.getMessage());
		}
		
		
		po.setUpdateUser(userName);
		po.setUpdateDate(new Date(System.currentTimeMillis()));
		
		Response result = categoryService.update(po, Category_Type.BASE_CATEGORY);
		
		attr.addAttribute(CURRENT_CATE_ID, po.getId());
		
        return AjaxObject.newOk(result.getMsg()).toString();
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
     * 导出运营分类商品
     * @return String 返回JSON字符格式结果
     * */
	@RequestMapping(value="/export",method={  RequestMethod.GET})
    @ResponseBody
    public String export(HttpServletRequest req,HttpServletResponse resp){
    	
        String strReturn = null;
        AjaxObject returnAjax = null;
        List<CategoryPO> catePOList = null;
       
        String fileName = "基础分类表单.xlsx";
        String user_agent = req.getHeader("user-agent");
    	if(user_agent != null && user_agent.indexOf("Firefox")>0){
    		try {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				logger.error("编码格式转换异常"+e.getMessage(), e);
			}
    	}else{
    		fileName = toUtf8String(fileName);
    	}
    	String [] strExportTitle ={"一级分类编号","一级分类名称","二级分类编号","二级分类名称","三级分类编号","三级分类名称"};
    	List<String> titleList = Arrays.asList(strExportTitle);
    	
    	Workbook book = null;
    	OutputStream os = null;
    	try {
        	
    		 catePOList = this.categoryService.queryAll(Category_Type.BASE_CATEGORY);
        	 book =  this.categoryService.writeExcel(titleList,  catePOList);
             resp.reset();
             resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
             resp.setContentType("application/vnd.ms-excel; charset=utf-8");
             os = resp.getOutputStream();
             book.write(os);
             returnAjax = AjaxObject.newOk("导出成功");
        } catch (Exception e) {
        	logger.error("export运营异常信息==",  e);
        	returnAjax = AjaxObject.newError("export运营异常!"+e.getMessage());
        	
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    logger.error("关闭输出流出错", e);
                }
            }
        }
    	
    	strReturn = returnAjax.toString();
        return strReturn;
    }
	
	
	/**
	 * 校验是否有商品已经挂载到所选运营分类或子分类下， 只有所有该子树下所有分类都没有挂载商品时才可以删除
	 * @param model
	 * @param categoryId
	 * @return
	 */
	//@RequestMapping("/noProductsInSubCategory")
	//@ResponseBody
	private AjaxObject noProductsInSubCategory(String ids){
		if(ids == null){
			return AjaxObject.newError("运营分类ID 为空, 请选择运营分类节点");
		}
		try{
			List<String> categoryCodeList = categoryService.queryBaseCodeListByIds(ids);
			if(categoryCodeList == null){
				return AjaxObject.newError("oops~~未查询到所选分类~~");
			}
			
			if(productMallViewService.countByCatCode(categoryCodeList) > 0){
				return AjaxObject.newError("所勾选要删除的分类已挂载商品，无法操作");
			}else{
				return AjaxObject.newOk("");
			}
		}catch(ZhaidouRuntimeException e){
			return AjaxObject.newError(e.getMessage());
		}catch(Exception e){
			return AjaxObject.newError(e.getMessage());
		}
	}
	
	
	/**
     * 乱码解决
     * */
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}
