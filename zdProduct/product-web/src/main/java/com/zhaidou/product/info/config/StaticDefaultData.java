package com.zhaidou.product.info.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.zhaidou.product.info.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class StaticDefaultData {

	private static final Logger LOGGER = LoggerFactory.getLogger(StaticDefaultData.class);

	public static Map<String, String> addProductImport = new HashMap<String, String>();
	public static Map<String, String> updateProductImport = new HashMap<String, String>();
	public static Map<String,String> productExport = new HashMap<String, String>();
	public static Map<String,String> productColor = new HashMap<String, String>();
	public static Map<String,String> productSize = new HashMap<String, String>();
	public static void setStaticFileData() {
		try {
			GsonBuilder gb = new GsonBuilder();
			Gson g = gb.create();
			String airLinesJson = null;
			try {
				airLinesJson = FileUtil.getFileDataStr("addProductImport.json");
				airLinesJson = new String(airLinesJson.getBytes(), Charset.forName("UTF-8"));
				addProductImport = g.fromJson(airLinesJson, new TypeToken<Map<String, String>>() {
				}.getType());
			} catch (Exception e) {
				LOGGER.error("初始化addProductImport数据失败！", e);
			}
			try {
				airLinesJson = FileUtil.getFileDataStr("updateProductImport.json");
				airLinesJson = new String(airLinesJson.getBytes(), Charset.forName("UTF-8"));
				updateProductImport = g.fromJson(airLinesJson, new TypeToken<Map<String, String>>() {
				}.getType());
			} catch (Exception e) {
				LOGGER.error("初始化updateProductImport数据失败！", e);
			}
			try {
				airLinesJson = FileUtil.getFileDataStr("productExport.json");
				airLinesJson = new String(airLinesJson.getBytes(), Charset.forName("UTF-8"));
				productExport = g.fromJson(airLinesJson, new TypeToken<Map<String, String>>() {
				}.getType());
			} catch (Exception e) {
				LOGGER.error("初始化productExport数据失败！", e);
			}
			//颜色初始化
			airLinesJson = FileUtil.getFileDataStr("productColor.json");
			airLinesJson = new String(airLinesJson.getBytes(), Charset.forName("UTF-8"));
			productColor = g.fromJson(airLinesJson, new TypeToken<Map<String, String>>() {
			}.getType());
			//颜色初始化
			airLinesJson = FileUtil.getFileDataStr("productSize.json");
			airLinesJson = new String(airLinesJson.getBytes(), Charset.forName("UTF-8"));
			productSize = g.fromJson(airLinesJson, new TypeToken<Map<String, String>>() {
			}.getType());
		} catch (JsonSyntaxException e) {
			LOGGER.error("初始化数据失败！", e);
		}
	}
}