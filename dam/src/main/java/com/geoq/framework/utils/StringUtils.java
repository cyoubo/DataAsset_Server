package com.geoq.framework.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.geoq.framework.log.LoggerContext;

public class StringUtils
{
	public static String JoinStringArrays(Object[] arrays, String split)
	{
		StringBuffer buffer = new StringBuffer();
		for (Object object : arrays)
		{
			buffer.append(object.toString());
			buffer.append(split);
		}
		buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}

	public static String toUTF8URL(String sourceURL)
	{
		String[] items = sourceURL.split("/");
		String[] newItems = new String[items.length];
		for (int i = 0; i < newItems.length; i++)
		{
			if (isContainChinese(items[i]))
			{
				try
				{
					newItems[i] = (java.net.URLEncoder.encode(items[i], "utf-8"));
				} catch (Exception ex)
				{
					newItems[i] = items[i];
				}
			} else
				newItems[i] = items[i];
		}
		System.out.println(JoinStringArrays(newItems, "/"));
		return JoinStringArrays(newItems, "/");
	}

	public static boolean isContainChinese(String str)
	{
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find())
		{
			return true;
		}
		return false;
	}

	public static String ConvertISO2Utf8(String source)
	{
		String result = source;
		try
		{
			if (result != null)
			{
				result = new String(source.getBytes("ISO-8859-1"), "utf-8");
			}
		} catch (Exception e)
		{
			LoggerContext.instance().error(e);
		}
		return result;
	}
	
	
	public static String tryEncodeStr(String value)
	{
		String result = value;
		try
		{
			result = java.net.URLEncoder.encode(value, "utf-8");
		} catch (UnsupportedEncodingException e)
		{
			
			LoggerContext.instance().error(e);
		}
		return result;
	}

}
