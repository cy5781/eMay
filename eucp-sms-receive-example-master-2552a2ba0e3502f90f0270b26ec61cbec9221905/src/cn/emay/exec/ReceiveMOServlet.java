package cn.emay.exec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.emay.util.ResponseUtils;

public class ReceiveMOServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// 服务启动地址
	public static final String path = "/receiveMo";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String appId = request.getHeader("appId");
		String mos = request.getParameter("mos");
		System.out.println("appId为：" + appId + "接收上行数据:" + mos);
		ResponseUtils.outputWithString(response, "success");
	}
}
