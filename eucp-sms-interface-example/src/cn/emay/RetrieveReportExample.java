package cn.emay;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.emay.util.DateUtil;
import cn.emay.util.Md5;
import cn.emay.util.http.HttpClient;
import cn.emay.util.http.HttpRequest;
import cn.emay.util.http.HttpRequestKV;
import cn.emay.util.http.HttpRequestParams;
import cn.emay.util.http.HttpResponseString;
import cn.emay.util.http.HttpResponseStringPraser;
import cn.emay.util.http.HttpResultCode;
import cn.emay.util.http.HttpsRequestKV;

public class RetrieveReportExample {

	public static void main(String[] args) {
		// appId
		String appId = "EUCe-EMt-SMS9-XXXXX";// 请联系销售，或者在页面中 获取
		// 密钥
		String secretKey = "1234567893214567";// 请联系销售，或者在页面中 获取
		// 接口地址
		String host = "127.0.0.1:8999";// 请联系销售获取
		// 编码
		String encode = "UTF-8";
		// 时间戳
		String timestamp = DateUtil.toString(new Date(), "yyyyMMddHHmmss");
		// 签名
		String sign = Md5.md5((appId + secretKey + timestamp).getBytes());
		String startTime = "20170101120000";
		String endTime = "20170101120000";
		String smsId = "123123123,321321321";
		getReport(appId, host, encode, timestamp, sign, startTime, endTime, smsId);
	}

	/**
	 * 获取状态报告
	 */
	private static void getReport(String appId, String host, String encode,// 时间戳
			String timestamp, String sign, String startTime, String endTime, String smsId) {
		System.out.println("=============begin getReport==================");
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", appId);
		params.put("timestamp", timestamp);
		params.put("sign", sign);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		if (null != smsId && !"".equals(smsId)) {
			params.put("smsId", smsId);
		}
		String url = "http://" + host + "/report/retrieveReport";
		String result = request(params, url);
		System.out.println(result);
		System.out.println("=============end getReport==================");
	}

	/**
	 * 公共请求方法
	 */
	public static String request(Map<String, String> params, String url) {
		HttpRequestParams<Map<String, String>> requestparams = new HttpRequestParams<Map<String, String>>();
		requestparams.setCharSet("UTF-8");
		requestparams.setMethod("POST");
		requestparams.setParams(params);
		requestparams.setUrl(url);
		HttpRequest<Map<String, String>> request;
		if(url.startsWith("https://")) {
			request = new HttpsRequestKV(requestparams,null);
		}else {
			request = new HttpRequestKV(requestparams);
		}
		HttpClient client = new HttpClient();
		String json = null;
		try {
			String mapst = "";
			for (String key : params.keySet()) {
				String value = params.get(key);
				mapst += key + "=" + value + "&";
			}
			mapst = mapst.substring(0, mapst.length() - 1);
			System.out.println("request params: " + mapst);
			HttpResponseString res = client.service(request, new HttpResponseStringPraser());
			if (res == null) {
				System.err.println("请求接口异常");
				return null;
			}
			if (res.getResultCode().equals(HttpResultCode.SUCCESS)) {
				if (res.getHttpCode() == 200) {
					json = res.getResult();
					System.out.println("response json: " + json);
				} else {
					System.out.println("请求接口异常,请求码:" + res.getHttpCode());
				}
			} else {
				System.out.println("请求接口网络异常:" + res.getResultCode().getCode());
			}
		} catch (Exception e) {
			System.err.println("解析失败");
			e.printStackTrace();
		}
		return json;
	}
}
