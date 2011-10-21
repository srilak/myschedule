package myschedule.web.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A holder for a viewName and dataMap that are used to render view.
 * 
 * @author Zemian Deng <saltnlight5@gmail.com>
 *
 */
public class ViewData {
	protected String viewName;
	protected Map<String, Object> dataMap = new HashMap<String, Object>();
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public ViewData(String viewName) {
		this.viewName = viewName;
	}
	
	public ViewData(String viewName, Object ... dataArray) {
		this.viewName = viewName;
		addData(dataArray);
	}
	
	public ViewData(String viewName, Map<String, Object> dataMap) {
		this.viewName = viewName;
		this.dataMap.putAll(dataMap);
	}
	
	public ViewData addNestedMap(String key, Object ... dataArray) {
		dataMap.put(key, mkMap(dataArray));		
		return this;
	}
	
	public ViewData addData(Object ... dataArray) {
		dataMap.putAll(mkMap(dataArray));		
		return this;
	}
	
	public ViewData addMap(Map<String, Object> dataMap) {
		this.dataMap.putAll(dataMap);
		return this;
	}
	
	public String getViewName() {
		return viewName;
	}
	
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getData(String key) {
		return (T)dataMap.get(key);
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "ViewData[" + viewName + ", " + dataMap + "]";
	}
		
	public static ViewData view(String viewName) {
		return new ViewData(viewName);
	}
	public static ViewData viewData(String viewName, Object ... dataArray) {
		return new ViewData(viewName, dataArray);
	}
	public static ViewData viewData(String viewName, Map<String, Object> dataMap) {
		return new ViewData(viewName, dataMap);
	}
	
	public static Map<String, Object> mkMap(Object ... dataArray) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (dataArray.length % 2 != 0) {
			throw new IllegalArgumentException("Data must come in pair: key and value.");
		}
		
		for (int i = 0; i < dataArray.length; i++) {
			Object keyObj = dataArray[i];
			if (!(keyObj instanceof String)) {
				throw new IllegalArgumentException("Key must be a String type, but got: " + keyObj.getClass());
			}
			String key = (String)keyObj;
			Object value = dataArray[++i];
			map.put(key, value);
		}
		return map;
	}
	
}
