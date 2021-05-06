package cn.emay.exec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.emay.util.AES;
import cn.emay.util.GZIPUtils;
import cn.emay.util.RequestUtils;
import cn.emay.util.ResponseUtils;

/**
 * 上行
 * 
 * @author Frank
 * 
 */
public class ReceiveGzipMOServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// 特服号秘钥，运行请自行修改
	public static final String secretKey = "DC92E49E59050C1F";
	// 服务启动地址
	public static final String path = "/receiveGzipMo";

	private static String algorithm = "AES/ECB/PKCS5Padding";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String appId = request.getHeader("appId");
			byte[] bytes = RequestUtils.getrequestBytesData(request);
			bytes = AES.decrypt(bytes, secretKey.getBytes(), algorithm);
			bytes = GZIPUtils.decompress(bytes);
			String mos = new String(bytes, "UTF-8");
			System.out.println("appId为：" + appId + "接收上行数据:" + mos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.outputWithString(response, "success");
	}
}
