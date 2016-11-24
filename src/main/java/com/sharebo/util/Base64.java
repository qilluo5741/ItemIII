package com.sharebo.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {
	private static BASE64Encoder encoder = new BASE64Encoder();  
    private static BASE64Decoder decoder = new BASE64Decoder();  
    /** 
     * BASE64 编码 
     *  
     * @param s 
     * @return 
     */  
    public static String encodeBufferBase64(byte[] buff)  
    {  
        return buff == null?null:encoder.encodeBuffer(buff).trim();  
    }  
      
      
    /** 
     * BASE64解码 
     *  
     * @param s 
     * @return 
     */  
    public static byte[] decodeBufferBase64(String s)  
    {  
        try  
        {  
            return s == null ? null : decoder.decodeBuffer(s);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }   
        return null;  
    }  
      
  
    /** 
     * base64编码 
     *  
     * @param bytes 
     *            字符数组 
     * @return 
     * @throws IOException 
     */  
    public static String encodeBytes(byte[] bytes) throws IOException  
    {  
        return new BASE64Encoder().encode(bytes).replace("\n", "").replace("\r", "");  
    }  
  
    /** 
     * base64解码 
     *  
     * @param bytes 
     *            字符数组 
     * @return 
     * @throws IOException 
     */  
    public static String decodeBytes(byte[] bytes) throws IOException  
    {  
        return new String(new BASE64Decoder().decodeBuffer(new String(bytes)));  
    }  
}
