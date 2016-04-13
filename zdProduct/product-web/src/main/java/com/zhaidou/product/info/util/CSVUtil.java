package com.zhaidou.product.info.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;


public class CSVUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(CSVUtil.class);

	/*
	 * 导出内容的格式方法
	 */
	private String exportContentMethod = "toExportCSV";
	/*
	 * 导出的头部
	 */
	private String exportTitle = "toExportCSVTitle";

	/**
	 * 导出csv数据
	 * 调用此方法前，请先确认导出内容的方法和导出头部方法是不是正确
	 *
	 * @param clazz       导出的内容对应的Model类的class对象
	 * @param listContent 导出的内容列表
	 * @param response
	 * @throws Exception
	 */
	public static void exportCSV(Class<?> clazz, List<?> listContent, HttpServletResponse response) throws Exception {
//		if(listContent == null || listContent.size() == 0)
//		{
//			return;
//		}

		String fileName = System.currentTimeMillis() + ".csv";
		PrintWriter printWriter = null;
		response.setContentType("application/csv;charset=utf-8");
		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8"));
			printWriter = response.getWriter();

			Method method = clazz.getDeclaredMethod("toExportCSV", new Class[]{});
			String content = getExportCSVContent(method, listContent);

			Method methodTitle = clazz.getDeclaredMethod("toExportCSVTitle", new Class[]{});
			Object obj = clazz.newInstance();
			String title = String.valueOf(methodTitle.invoke(obj, new Object[]{}));

			content = title + "\n" + content;
			printWriter.write(new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));
			printWriter.write(content);
			printWriter.flush();

		} catch (Exception e) {
			LOGGER.error("exportCSV error", e);

		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}

	}

	private static String getExportCSVContent(Method method, List<?> listContent) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < listContent.size(); i++) {
			Object retValue = method.invoke(listContent.get(i), new Object[]{});
			sb.append(retValue).append("\n");
		}
		return sb.toString();
	}


	public String getExportContentMethod() {

		return exportContentMethod;
	}


	public void setExportContentMethod(String exportContentMethod) {

		this.exportContentMethod = exportContentMethod;
	}


	public String getExportTitle() {

		return exportTitle;
	}


	public void setExportTitle(String exportTitle) {

		this.exportTitle = exportTitle;
	}


}
