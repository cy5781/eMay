package cn.emay.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

	public static String getParameter(HttpServletRequest request, String name) {
		String temp = request.getParameter(name);
		if (temp == null || temp.trim().equals(""))
			temp = null;
		else
			temp = temp.trim();
		return temp;
	}

	public static String getParameter(HttpServletRequest request, String name, String defaultValue) {
		String temp = request.getParameter(name);
		if (temp == null || temp.trim().equals("")) {
			temp = defaultValue;
		} else {
			temp = temp.trim();
		}
		return temp;
	}

	public static long getLongParameter(HttpServletRequest request, String name, long defaultNum) {
		String temp = request.getParameter(name);
		if (temp == null || temp.trim().equals(""))
			return defaultNum;
		long num = defaultNum;
		try {
			num = Long.valueOf(temp.trim());
		} catch (Exception ignored) {
		}
		return num;
	}

	public static boolean getBooleanParameter(HttpServletRequest request, String name, boolean defaultValue) {
		String temp = request.getParameter(name);
		if (temp == null || temp.trim().equals(""))
			return defaultValue;
		if (temp.equalsIgnoreCase("true") || temp.equalsIgnoreCase("on") || temp.equalsIgnoreCase("1"))
			return true;
		if (temp.equalsIgnoreCase("false") || temp.equalsIgnoreCase("off") || temp.equalsIgnoreCase("0"))
			return false;
		return defaultValue;
	}

	public static int getIntParameter(HttpServletRequest request, String name, int defaultNum) {
		String temp = request.getParameter(name);
		if (temp == null || temp.trim().equals(""))
			return defaultNum;
		int num = defaultNum;
		try {
			num = Integer.valueOf(temp);
		} catch (Exception e) {
		}
		return num;
	}

	public static float getFloatParameter(HttpServletRequest request, String name, float defaultNum) {
		String temp = request.getParameter(name);
		if (temp == null || temp.trim().equals(""))
			return defaultNum;
		float num = defaultNum;
		try {
			num = Float.valueOf(temp);
		} catch (Exception e) {
		}
		return num;
	}

	public static double getDoubleParameter(HttpServletRequest request, String name, double defaultNum) {
		String temp = request.getParameter(name);
		if (temp == null || temp.trim().equals(""))
			return defaultNum;
		double num = defaultNum;
		try {
			num = Double.valueOf(temp);
		} catch (Exception e) {
		}
		return num;
	}

	public static Date getDateParameter(HttpServletRequest request, String name, String format, Date defaultValue) {
		String temp = request.getParameter(name);
		Date date = DateUtil.parseDate(temp, format);
		if (date == null)
			date = defaultValue;
		return date;
	}

	public static BigDecimal getBigDecimalParameter(HttpServletRequest request, String name, BigDecimal defaultValue) {
		String temp = request.getParameter(name);
		BigDecimal value = defaultValue;
		try {
			value = new BigDecimal(temp);
		} catch (Exception e) {
		}
		return value;
	}

	public static String getRemoteRealIp(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		String remoteAddr = request.getRemoteAddr();
		String forwarded = request.getHeader("X-Forwarded-For");
		String realIp = request.getHeader("X-Real-IP");
		String ip = null;
		if (realIp == null || realIp.length() == 0 || "unknown".equalsIgnoreCase(realIp)) {
			if (forwarded == null || forwarded.length() == 0 || "unknown".equalsIgnoreCase(forwarded)) {
				ip = remoteAddr;
			} else {
				ip = forwarded.split(",")[0];
			}
		} else {
			ip = realIp;
		}
		return ip;
	}

	/**
	 * 获取请求数据
	 * 
	 * @param request
	 * @return
	 */
	public static byte[] getrequestBytesData(HttpServletRequest request) {
		DataInputStream in = null;
		byte[] data = null;
		try {
			int totalbytes = request.getContentLength();
			if (totalbytes <= 0) {
				return null;
			}
			data = new byte[totalbytes];
			in = new DataInputStream(request.getInputStream());
			in.readFully(data);
		} catch (Exception e) {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return data;
	}

}
