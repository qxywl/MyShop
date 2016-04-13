package com.zhaidou.product.category.controller;

import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.enumration.ShelvesType;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.category.model.MountProduct;
import com.zhaidou.product.category.service.CategoryService;
import com.zhaidou.product.category.service.Category_Type;
import com.zhaidou.product.category.service.MountProductService;
import com.zhaidou.product.category.service.MountType;
import com.zhaidou.product.category.util.Utils;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: SaleCategoryController.java 
 *
 * @Package com.teshehui.product.category.controller 
 *
 * @Description:   运营分类Controller
 *
 * @author lvshuding 
 *
 * @date 2015年3月27日 下午4:24:58 
 *
 * @version V1.0
 */
@Controller("saleCategoryProductController")
@RequestMapping("/sale-category/product")
public class SaleCategoryProductController extends BaseController{

	private static Logger logger = Logger.getLogger(SaleCategoryProductController.class);
//	private final static Integer limitRows = 3000; // 限制导出三千条
//	private final static String templateFile = "/resources/product_mount_template.xlsx"; //模板路径
	private final static String MSG_NAME="msgInfo";
	private final static String CURRENT_CATE_ID="currentCateId";
	private final static String UPLOAD_EXCEL = "category/product/upload_excel";
	private final static String MOUNT_LIST = "category/product/list";

	/**导出字段和表名*/
	private static String [] strExportTitle ={"商品编号","商品名称","上下架状态","是否下架显示"};
	private static String [] strExportField ={"productCode","productName","productShelves","productDownShow"};
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MountProductService mountProductService;
	
	@RequestMapping("/main")
	public String toMain(HttpServletRequest req,HttpServletResponse rsp,Model model){
		/*
		 * 校验用户登录
		 * 
		 * 
		 */
		
		List<CategoryPO> poList = categoryService.queryAll(Category_Type.SALE_CATEGORY);
		
		model.addAttribute("orgTreeStr",Utils.buildJsonFormTree(poList,req.getParameter(CURRENT_CATE_ID)));
		
		model.addAttribute(MSG_NAME, req.getParameter(MSG_NAME));
		
		return "category/sale_category";
	}
	
	
	@RequestMapping(value="/list")
	public String list(Model model, HttpServletRequest req, @ModelAttribute(value="currentCateId") Long categoryId, Page page){
		MountProduct relationModel = new MountProduct();
//		String categoryIdStr = req.getParameter("currentCateId");
//		Long categoryId = null;
//		if(categoryIdStr != null && categoryIdStr != ""){
		relationModel.setCategoryId(categoryId);
		List<MountProduct> relationList = this.mountProductService.getProductsByCategoryId(relationModel, page);
		Map<Integer,String> mountTypeMap = new HashMap<Integer,String>();
		mountTypeMap.put(MountType.MANUAL.getValue(), "手动挂载");
		mountTypeMap.put(MountType.AUTO.getValue(), "自动挂载");
		Map<Integer,String> shelvesTypeMap = new HashMap<Integer,String>();
		shelvesTypeMap.put(ShelvesType.UP.getValue(), "上架");
		shelvesTypeMap.put(ShelvesType.DOWN.getValue(), "下架");
		
		model.addAttribute("relationList", relationList);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("page", page);
		model.addAttribute("mountTypeMap", mountTypeMap);
		model.addAttribute("shelvesTypeMap", shelvesTypeMap);
		return MOUNT_LIST;
	}
	
	@RequestMapping(value="/download/template")
	public String downloadTemplate(HttpResponse resp){
		return "";
	}
	
	/**
	 * 转至上传页面
	 */
	@RequestMapping(value = "/upload_excel/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String toUpload(HttpServletRequest request,Model model, @PathVariable("id") Long id) {
		CategoryPO tempCategoryPO = new CategoryPO();
		
		if(id != null){
			tempCategoryPO.setId(id);
			CategoryPO categoryPO = this.categoryService.queryOne(tempCategoryPO, Category_Type.SALE_CATEGORY);
			if(categoryPO != null){
				model.addAttribute("saleCategory", categoryPO);
			}
		}
		return UPLOAD_EXCEL;
	}
	
	
	/**
	 * 转至运营分类下挂载的商品编辑排序值页面
	 */
	@RequestMapping(value = "/toUpdateOrderVal/{relationId}" , method = { RequestMethod.GET})
	public String toUpdateOrderVal(@PathVariable Long relationId,
			Map<String, Object> map){
		
		MountProduct mountProModel = null;
		try {
			mountProModel = mountProductService.getMpOrderValue(relationId);
		} catch (Exception e) {
			logger.error("获运营分类下商品排序值报错！！！", e);
			return "error/500";
		}
		map.put("mountProModel",mountProModel);
		return "category/product/cate_pro_order_edit";
	}
	
	
	/**
	 * 更新运营分类下挂载的商品编辑排序值页面
	 */
	@ResponseBody
	@RequestMapping(value = "/updateOrderVal" , method = { RequestMethod.POST})
	public String updateOrderVal(MountProduct mpModel,
			Map<String, Object> map){
		
		int influence = 0;
		AjaxObject returnAjax = null;
		try {
			influence = mountProductService.updateMpOrderValueByRelId(mpModel);
			if(influence > 0){
				returnAjax = AjaxObject.newOkForward("操作成功！", "sale-category/product/list?"+CURRENT_CATE_ID+"="+mpModel.getCategoryId());
			}else{
				returnAjax = AjaxObject.newError("操作失败！");
			}
			
		} catch (Exception e) {
			logger.error("更新营分类下商品排序值报错！！！", e);
			returnAjax = AjaxObject.newError("操作失败！");
		}
		
		return returnAjax.toString();
		
	}
	
	
	/**
	 * @Description: 手动挂载商品
	 * */
	@RequestMapping(value = "/uploadExcel", method = { RequestMethod.POST }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String uploadExcel(MultipartHttpServletRequest request, @ModelAttribute CategoryPO saleCategory) {

		AjaxObject returnAjax = null;

		MultipartFile mf = request.getFile("file");
		String filename = mf.getOriginalFilename();
		//这里要获取登录用户的用户名
		String userName = Utils.getUserNameFromRequest(request);
		saleCategory.setUpdateUser(userName);

		logger.info("手动挂载商品上传[用户:" + userName + "\t文件名:" + filename + "\t文件大小:" + mf.getSize() + "]");
		if(!(filename.endsWith(".xls") || filename.endsWith(".xlsx"))){
			return AjaxObject.newError("导入失败：文件类型不正确").toString();
		}
		try {
			if (mf.getSize() == 0) {
				returnAjax = AjaxObject.newError("文件为空");
			} else {
				ResponseObject responseObj = mountProductService.processMpExcel(filename, mf.getInputStream(), saleCategory);
				if(responseObj.getCode() == ResponseObject.CODE_SUCCESS_VALUE){
					returnAjax = AjaxObject.newOkForward(responseObj.getMsg(), "sale-category/product/list?"+CURRENT_CATE_ID+"="+saleCategory.getId());
				}else{
					returnAjax = AjaxObject.newError(responseObj.getMsg());
				}
			}
		} catch(NullPointerException e){
			logger.error("读取上传文件出错2:", e);
			returnAjax = AjaxObject.newError(e.getMessage());
		} catch (IOException e) {
			logger.error("读取上传文件出错", e);
			returnAjax = AjaxObject.newError("导入失败：导入格式有问题");
		} catch (Exception e) {
			logger.error("产品手动挂载失败", e);
			returnAjax = AjaxObject.newError("导入失败：系统错误");
		}
		String ss = returnAjax.toString();
		logger.info("returnAjax ============" + ss);
		return returnAjax.toString();
	}

	/**
	 * 校验是否有商品已经挂载到所选运营分类下
	 * @param model
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/noProducts")
	@ResponseBody
	public String noProducts(Model model, @RequestParam("currentCateId") Long categoryId){
		if(categoryId == null){
			return AjaxObject.newError("运营分类ID 为空, 请选择运营分类节点").toString();
		}
		try{
			if(mountProductService.hasMountProduct(categoryId)){
				return AjaxObject.newError("已有商品挂在在该分类下，无法添加子分类").toString();
			}else{
				return AjaxObject.newOk("").toString();
			}
		}catch(ZhaidouRuntimeException e){
			return AjaxObject.newError(e.getMessage()).toString();
		}
	}
	
	
	
	
	/**
     * 导出categoryId 分类下的所有商品
     * @return String 返回JSON字符格式结果
     * */
	@RequestMapping(value="/exportCategoryProds/{categoryId}",method={  RequestMethod.GET})
    @ResponseBody
    public String export(@PathVariable Long categoryId ,HttpServletRequest req,HttpServletResponse resp){
    	
        String strReturn = null;
        AjaxObject returnAjax = null;
        List<MountProduct> MountProductList = null;
       
        String fileName = "分类下的商品.xlsx";
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
    	List<String> titleList = Arrays.asList(strExportTitle);
    	List<String> fieldList = Arrays.asList(strExportField);
    	
    	Workbook book = null;
    	OutputStream os = null;
    	try {
    		
    		 MountProductList = mountProductService.queryExprotProdsByCategoryId(categoryId);
    		 book = mountProductService.exportExcel(MountProductList, titleList, fieldList);
             resp.reset();
             resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
             resp.setContentType("application/vnd.ms-excel; charset=utf-8");
             os = resp.getOutputStream();
             book.write(os);
             returnAjax = AjaxObject.newOk("导出成功");
             
        } catch (Exception e) {
        	logger.error("export分类下的商品==",  e);
        	returnAjax = AjaxObject.newError("export分类下的商品!"+e.getMessage());
        	
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
