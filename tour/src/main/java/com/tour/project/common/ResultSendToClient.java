package com.tour.project.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;


public class ResultSendToClient {

	public static void onlyResultTo(HttpServletResponse response, int result)
	{
		JSONObject jsonRtnVal = new JSONObject();
        jsonRtnVal.put("result", (Object)String.valueOf(result));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
//        logger.info("result : " + result);
        try {
            response.getWriter().write(jsonRtnVal.toString());
            response.flushBuffer();
        }
        catch (IOException e) {
        	e.getStackTrace();
        }
	}
	public static void onlyResultTo(HttpServletResponse response, Logger logger, String result)
	{
		JSONObject jsonRtnVal = new JSONObject();
        jsonRtnVal.put("result", result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        logger.info("result : " + result);
        try {
            response.getWriter().write(jsonRtnVal.toString());
            response.flushBuffer();
        }
        catch (IOException e) {
        	e.getStackTrace();
        }
	}
	public static void jsonObjectTo(HttpServletResponse response, Logger logger, JSONObject json)
	{
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
        	response.getWriter().write(json.toString());
            response.flushBuffer();
        }
        catch (IOException e) {
        	e.getStackTrace();
        }
	}
	
	public static void jsonArrayTo(HttpServletResponse response, Logger logger, JSONArray jsonArray)
	{
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
        	response.getWriter().write(jsonArray.toString());
            response.flushBuffer();
        }
        catch (IOException e) {
        	e.getStackTrace();
        }
	}
	public static void onlyTextResultTo(HttpServletResponse response, Logger logger, int result)
	{
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        logger.info("result : " + result);
        try {
            response.getWriter().write(String.valueOf(result));
            response.flushBuffer();
        }
        catch (IOException e) {
        	e.getStackTrace();
        }
	}
	public static void userJoinOk_ResultTo(HttpServletResponse response, Logger logger, JSONObject jsonObj)
	{
	    response.setContentType("text/html");
	    response.setCharacterEncoding("UTF-8");
	    if (jsonObj!=null)
	    {
	    	int result = jsonObj.getInt("result");
	    	String strSession = jsonObj.getString("sessionval");
	    	String deviceid = jsonObj.getString("deviceid");
		    logger.info("result : " + result);
		    try {
		        response.getWriter().write(String.valueOf(String.valueOf(result)) + ":" + strSession + "&&" + deviceid);
		        response.flushBuffer();
		    }
		    catch (IOException e) {
		    	e.getStackTrace();
		    }
	    }
	}
}
