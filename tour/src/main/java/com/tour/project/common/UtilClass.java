/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  com.unace.server.UtilClass
 *  javax.servlet.http.HttpServletRequest
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.client.ClientProtocolException
 *  org.apache.http.client.HttpClient
 *  org.apache.http.client.methods.HttpGet
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.bouncycastle.util.encoders.Base64
 *  org.slf4j.Logger
 */
package com.tour.project.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Exception performing whole class analysis ignored.
 */
public class UtilClass {
	private static final Logger logger = LoggerFactory.getLogger(UtilClass.class);
	
    public UtilClass() {
    }
    
    public static String SHA256(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(source.getBytes("UTF-8"));
            return UtilClass.getString((byte[])bytes);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            byte b = bytes[i];
            String hex = Integer.toHexString(255 & b);
            if (hex.length() == 1) {
                sb.append("0");
            }
            sb.append(hex);
        }
        return sb.toString();
    }
    
    public static String exceptionToString(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionAsString = "";
        if (sw.toString().length()>500)
        	exceptionAsString = sw.toString().substring(0,500);
        else	
        	exceptionAsString= sw.toString();
        
        try {
        	exceptionAsString = sw.toString();
	        sw.close();
	        pw.close();
	        sw = null;
	        pw = null;
        }catch (Exception ex1) {
        	
        }
        return exceptionAsString;
    }
    
    public static String isDevice(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent").toUpperCase();
    		
        if(userAgent.indexOf("MOBI") > -1) {
             return "MOBILE";
        } else {
            return "PC";
        }
    }
    
    public static Workbook excelSupportUtil(String sheetName, String[] headers, String[] keys, List<LinkedHashMap<String, Object>> list) {
    	Workbook wb = null;
    	try {
	    	wb = new XSSFWorkbook();
	    	Sheet sheet = wb.createSheet(sheetName);
	    	
	    	Row row = null;
	        Cell cell = null;
	        int rowNum = 0;
	    	
	        row = sheet.createRow(rowNum++);
	        
	        // header
	        for (int i = 0; i < headers.length; i++) {
	        	cell = row.createCell(i);
	        	cell.setCellValue(headers[i]);
	        }
	        
	        int cellIndex = 0;
	        
	        if (!StringUtil.isEmpty(list)) {
	        	for (LinkedHashMap<String, Object> map : list) {
	        		row = sheet.createRow(rowNum++);
	        		
	        		for (String key : keys) {
	        			String cellData = String.valueOf(map.get(key));
	        			cell = row.createCell(cellIndex++);
	        			cell.setCellValue(cellData);
	        		}
	        		cellIndex = 0;
	        	}
	        	
	        }
	        
    	} catch (Exception e) {
    		throw e;
    	} 
    	
    	return wb;
        
    }
}
