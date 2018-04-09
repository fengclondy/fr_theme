package com.fr.hailian.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpJsonHelper {
	// 根据key获取value
	public static String getValueByKey(String jsonStr, String key) {
		JSONObject jsonObj = null;
		if(jsonStr.indexOf("Error")!=-1){
			return "timeout";
		}
		try {
			jsonObj = new JSONObject(jsonStr);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		try {
			if(jsonObj==null||"".equals(jsonObj)){
				return "";
			}
			return jsonObj.get(key) + "";
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "timeout";
	}


	/**
	 * 解析list集合 并且每一个对象的某个属性也是list
	 * 数据格式：
	 * {"status":"success","data":[{"chapter_title":"锟斤拷锟斤拷锟斤拷","chapter_id":"234"
	 * ,"chapter_order":"23",
	 * "units":[{"unit_id":"123","unit_title":"锟斤拷锟皆斤拷","unit_order":"23"}]}]}
	 * 
	 * outBean:锟斤拷锟绞碉拷锟絚lass innerObject锟斤拷锟节诧拷实锟斤拷class innerKey锟斤拷json锟斤拷锟节诧拷json锟斤拷key锟斤拷
	 * 使锟矫ｏ拷getDoubleList(jsonStr,CourseChapter,CourseUnits,"units");
	 */
	public static List<Object> toDoubleList(JSONArray outarray, Class outBean,
			Class innerObject, String innerKey) {
		if (outarray==null||"".equals(outarray.toString())) {
			return null;
		}
		List<Object> list = new ArrayList<Object>();
		try {
			for (int i = 0; i < outarray.length(); i++) {
				Object owerOutBean = new Object();
				owerOutBean = toBeanContainList(outarray.getJSONObject(i), outBean,
						innerObject, innerKey);
				list.add(owerOutBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 解析bean  并且bean的某个属性是list
	 * @param jsonObj
	 * @param outBean
	 * @param innerObject
	 * @param innerKey
	 * @return
	 */
	public static Object toBeanContainList(JSONObject jsonObj, Class outBean,
			Class innerObject, String innerKey) {
		Method[] outmethods = outBean.getDeclaredMethods();
		Object obBean = new Object();
		Field[] fields=outBean.getFields();
		try {
			obBean = outBean.newInstance();
			for (Method outmethod : outmethods) {
				String s = outmethod.getName();
				if (s.startsWith("set")) {
					String field = outmethod.getName();
					field = field.substring(field.indexOf("set") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					if (innerKey.toUpperCase().equals(field.toUpperCase())) {
						List<Object> innerList = new ArrayList<>();
						// 锟斤拷锟斤拷锟节诧拷list
						JSONArray innerArray = new JSONArray(
								jsonObj.get(innerKey) + "");
						for (int x = 0; x < innerArray.length(); x++) {

							Object owerInnerBean = new Object();
							owerInnerBean = toJavaBean(
									innerArray.getJSONObject(x), innerObject);
							innerList.add(owerInnerBean);
						}
						// 锟斤拷锟节诧拷list锟斤拷锟斤拷锟斤拷锟絣ist锟斤拷锟斤拷锟斤拷
						outmethod.invoke(obBean, new Object[] { innerList });
					} else {
						// json锟叫帮拷key锟脚凤拷锟斤拷
						if (jsonObj.toString().indexOf(field) != -1) {
							outmethod.invoke(obBean,
									new Object[] { jsonObj.get(field) + "" });
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obBean;
	}
	public static Object toJavaBean(String result, Class innerObject) {
		JSONObject innerObj=null;
		try {
			innerObj = new JSONObject(result);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Method[] innermethods = innerObject.getDeclaredMethods();
		Object objBean = null;
		try {
			objBean = innerObject.newInstance();
			for (Method inmethod : innermethods) {
				String in = inmethod.getName();
				if (in.startsWith("set")) {
					String infield = inmethod.getName();
					infield = infield.substring(infield.indexOf("set") + 3);
					infield = infield.toLowerCase().charAt(0)
							+ infield.substring(1);
					if(innerObj.toString().indexOf(infield)!=-1){
						String value=innerObj.get(infield)+"";
						if("null".equals(value)){
							value="";
						}
						inmethod.invoke(objBean,
								new Object[] {value});
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objBean;
	}
	
	public static Object toJavaBean(JSONObject innerObj, Class innerObject) {
		Method[] innermethods = innerObject.getDeclaredMethods();
		Object objBean = null;
		try {
			objBean = innerObject.newInstance();
			for (Method inmethod : innermethods) {
				String in = inmethod.getName();
				if (in.startsWith("set")) {
					String infield = inmethod.getName();
					infield = infield.substring(infield.indexOf("set") + 3);
					infield = infield.toLowerCase().charAt(0)
							+ infield.substring(1);
					if(innerObj.toString().indexOf(infield)!=-1){
						inmethod.invoke(objBean,
								new Object[] { innerObj.get(infield) + "" });
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objBean;
	}
	public static Object toJavaBean(JSONArray innerObjArr, Class innerObject) {
		Method[] innermethods = innerObject.getDeclaredMethods();
		Object objBean = null;
		try {
			if(innerObjArr==null||innerObjArr.length()==0){
				return null;
			}
			JSONObject innerObj=innerObjArr.getJSONObject(0);
			objBean = innerObject.newInstance();
			for (Method inmethod : innermethods) {
				String in = inmethod.getName();
				if (in.startsWith("set")) {
					String infield = inmethod.getName();
					infield = infield.substring(infield.indexOf("set") + 3);
					infield = infield.toLowerCase().charAt(0)
							+ infield.substring(1);
					if(innerObj.toString().indexOf(infield)!=-1){
						inmethod.invoke(objBean,
								new Object[] { innerObj.get(infield) + "" });
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objBean;
	}
	/**
	 * 普通的list集合
	 * @param javaBean
	 * @param jsonString
	 * @return
	 */
	public static List<Object> toJavaBeanList(JSONArray array,Class javaBean) {
		List<Object> list = new ArrayList<Object>();
		try {
			if(array==null||array.length()==0){
				return null;
			}
			for (int i = 0; i < array.length(); i++) {
				JSONObject jsonObj = array.getJSONObject(i);
				list.add(toJavaBean(jsonObj,javaBean));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 普通的list集合
	 * @param javaBean
	 * @param jsonString
	 * @return
	 */
	public static List<Object> toJavaBeanList(String result,Class javaBean) {
		List<Object> list = new ArrayList<Object>();
		JSONArray array;
		try {
			array = new JSONArray(result);
			if(array==null||array.length()==0){
				return null;
			}
			for (int i = 0; i < array.length(); i++) {
				JSONObject jsonObj = array.getJSONObject(i);
				list.add(toJavaBean(jsonObj.toString(),javaBean));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<Object> toDoubleList(String re, Class outBean,
			Class innerObject, String innerKey) {
		List<Object> list = new ArrayList<Object>();
		try {
			re=re.substring(1,re.length()-1);
			re=re.replaceAll("\\\\\"", "\"");
			JSONArray outarray=new JSONArray(re);
			if (outarray==null||"".equals(outarray.toString())) {
				return null;
			}
			for (int i = 0; i < outarray.length(); i++) {
				Object owerOutBean = new Object();
				owerOutBean = toBeanContainList(outarray.getJSONObject(i), outBean,
						innerObject, innerKey);
				list.add(owerOutBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private static String firstToUpCase(String str){
		if(str==null||"".equals(str)){
			return "";
		}
		char[] arr = str.toCharArray();       
		if (arr[0] >= 'a' && arr[0] <= 'z') {
		    arr[0] -= 'a' - 'A';
		}
		return new String(arr);
	}
	public static void main(String[] args) {
		//user=(User) HttpJsonHelper.toJavaBean(result, User.class);
	}
}
