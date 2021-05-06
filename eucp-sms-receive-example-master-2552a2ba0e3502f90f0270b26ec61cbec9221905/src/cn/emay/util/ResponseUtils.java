package cn.emay.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtils {

	public static void outputBytes(HttpServletResponse response, byte[] bytes) {
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(bytes);
			out.flush();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void outputWithJson(HttpServletResponse response, Object obj) {
		response.setContentType("text/plain;charset=utf-8");
		String json = JsonHelper.toJsonString(obj);
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(json.getBytes("UTF-8"));
			out.flush();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void outputWithString(HttpServletResponse response, String string) {
		response.setContentType("text/plain;charset=utf-8");
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(string.getBytes("UTF-8"));
			out.flush();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void outputWithJsonp(HttpServletResponse response, String json, String jsonp) {
		response.setContentType("text/javascript;charset=utf-8");
		OutputStream out = null;
		try {
			json = jsonp + "(eval(" + json + "))";
			out = response.getOutputStream();
			out.write(json.getBytes("UTF-8"));
			out.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
