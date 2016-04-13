package com.zhaidou.product.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class StaticDefaultData {
	public static Map<String, String> CABIN_NAMES = new HashMap<String, String>();// 舱位名称,key=airline+cabin_type+cabin_code
	public static Map<String, String> CTRIP_CITIES = new HashMap<String, String>();// 携程机场城市对应
	public static Map<String, String> CTRIP_CITYIDS = new HashMap<String, String>();// 携程城市三字码与id对应
	public static Map<String, String> CTRIP_AIRLINES = new HashMap<String, String>();// 携程机场城市对应
	public static Map<String, String> CTRIP_AIRPORTS = new HashMap<String, String>();// 携程城市三字码与id对应
	public static Map<String, String> AIRPORTS = new HashMap<String, String>();// 多个机场三字码对应
	public static Map<String, String> AIRPORT_BUILDING = new HashMap<String, String>();// 机场航站楼信息
	public static Map<String, String> COUNTRY = new HashMap<String, String>();// 机场航站楼信息
	public static Map<String, String> IDTYPE = new HashMap<String, String>();
	public static Map<String, String> CITY = new HashMap<String, String>();
	public static String FRONT_CACHE_STR = "OLD_TICKET_";//memcached航线前缀
	
	private static String citiesFile = "CtripCities.json";//src目录下文件直接输名字即可
	private static String countryFile = "country.json";//src目录下文件直接输名字即可
	private static String cityIdsFile = "CtripCityIds.json";//src目录下文件直接输名字即可
	private static String airLines = "AirLines.json";//src目录下文件直接输名字即可
	private static String airPorts = "AirPorts.json";//src目录下文件直接输名字即可
	private static String idTypes = "idType.json";//src目录下文件直接输名字即可
	private static String city = "city.json";//src目录下文件直接输名字即可
	static  {
//		System.out.println("loading StaticDefaultData...");
		// 获取舱位名称
		CABIN_NAMES.put("F", "头等舱");
		CABIN_NAMES.put("Y", "经济舱");
		CABIN_NAMES.put("C", "商务舱");
		// 获取一个城市有多个机场的数据
		setStaticFileData();
	}

	public static void setStaticFileData() {
		try {
			
			String json=FileUtil.getFileDataStr(citiesFile);
//			System.out.println(json);
			GsonBuilder gb = new GsonBuilder();
			Gson g = gb.create();
			CTRIP_CITIES = g.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
			

            String countryjson=FileUtil.getFileDataStr(countryFile);
            COUNTRY = g.fromJson(countryjson, new TypeToken<Map<String, String>>() {}.getType());
            
			String idjson=FileUtil.getFileDataStr(cityIdsFile);
			CTRIP_CITYIDS = g.fromJson(idjson, new TypeToken<Map<String, String>>() {}.getType());
			
			String airLinesJson=FileUtil.getFileDataStr(airLines);
			CTRIP_AIRLINES = g.fromJson(airLinesJson, new TypeToken<Map<String, String>>() {}.getType());
			
			String airPortsJson=FileUtil.getFileDataStr(airPorts);
			CTRIP_AIRPORTS = g.fromJson(airPortsJson, new TypeToken<Map<String, String>>() {}.getType());
			
			String idTypesJson=FileUtil.getFileDataStr(idTypes);
			IDTYPE = g.fromJson(idTypesJson, new TypeToken<Map<String, String>>() {}.getType());
			
			String cityJson=FileUtil.getFileDataStr(city);
            CITY = g.fromJson(cityJson, new TypeToken<Map<String, String>>() {}.getType());
//			System.out.println(CTRIP_CITYIDS);
		} catch (JsonSyntaxException e) {
			System.out.println("setStaticFileData:CITIES="+CTRIP_CITIES+" or CITYIDS="+CTRIP_CITYIDS+" or AIRLINES="+CTRIP_AIRLINES+" or AIRPORTS="+CTRIP_AIRPORTS+" error. "+e);
		}
	}
	
	
}
