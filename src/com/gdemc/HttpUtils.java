package com.gdemc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class HttpUtils {

	//����url���ʷ����������ط�������Ӧ�ı�
	public static String doGet(String url) throws Exception 
	{ 
		    //����һ��URL����URL
		    URL localURL = new URL(url);
	      
		    //���ô��������
		    //System.setProperty("http.proxyHost", "127.0.0.1");  
	        //System.setProperty("http.proxyPort", "8888"); 
		    
	        URLConnection connection = localURL.openConnection();
	        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
   
	        //��������ͷ��������
	        httpURLConnection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C; .NET4.0E");
	        
	        
	        //��������������Ķ���
	        InputStream inputStream = null;
	        InputStreamReader inputStreamReader = null;
	        BufferedReader reader = null;
	        StringBuffer resultBuffer = new StringBuffer();
	        String tempLine = null;
	        
	        //302ǿ���������ת��200 ok
	        if (httpURLConnection.getResponseCode() >= 300) {
	            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
	        }
	        
	        try {
	            inputStream = httpURLConnection.getInputStream();
	      
	        
	        	//get header by 'key'
	        	String encoding = httpURLConnection.getHeaderField("Content-Encoding");
	            
	        	//������ص���ѹ��HTML����
	        	if(encoding!=null && encoding.equals("gzip"))
	        	{
	        	 System.out.println("����һ��ѹ����HTML\n");
	             GZIPInputStream gzin;  
	             gzin = new GZIPInputStream(inputStream); 
	             //�Է���ҳ�����ݽ���utf-8���룬�Ӷ����Ĳ�������
	             inputStreamReader = new InputStreamReader(gzin,"utf-8");
	            
	        	}
	        	else
	        	{
	        	   inputStreamReader = new InputStreamReader(inputStream,"utf-8");
	        	}
	            reader = new BufferedReader(inputStreamReader);
	            
	            while ((tempLine = reader.readLine()) != null) {
	                resultBuffer.append(tempLine+"\n");
	            }
	            
	        } finally {
	            
	            if (reader != null) {
	                reader.close();
	            }
	            
	            if (inputStreamReader != null) {
	                inputStreamReader.close();
	            }
	            
	            if (inputStream != null) {
	                inputStream.close();
	            }
	            
	        }
	        
	        return resultBuffer.toString();
	}
	
}
