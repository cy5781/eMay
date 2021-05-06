package cn.emay.exec;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JettyServer {

	private Server server;

	public JettyServer(int port) {
		server = new Server(port);
	}

	public void start() {
		try {
			ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
			servletContextHandler.setContextPath("/");
			servletContextHandler.setMaxFormContentSize(10000000);
			servletContextHandler.addServlet(new ServletHolder(new ReceiveReportServlet()), ReceiveReportServlet.path);
			servletContextHandler.addServlet(new ServletHolder(new ReceiveMOServlet()), ReceiveMOServlet.path);
			servletContextHandler.addServlet(new ServletHolder(new ReceiveGzipReportServlet()), ReceiveGzipReportServlet.path);
			servletContextHandler.addServlet(new ServletHolder(new ReceiveGzipMOServlet()), ReceiveGzipMOServlet.path);
			server.setHandler(servletContextHandler);
			server.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void stop() {
		if (server.isStarted() || server.isStarting()) {
			try {
				server.stop();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

}
