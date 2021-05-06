package cn.emay.exec;

public class Main {
	public static void main(String[] args) throws Exception {
		try {
			int port;
			try {
				port = Integer.valueOf(args[0]);
			} catch (Exception e) {
				System.out.println("args is wrong......");
				port = 8088;
			}
			JettyServer server = new JettyServer(port);
			server.start();
			System.out.println("server is starting......");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("server start error \nUSEAGE:\n\tjava -jar emas-test-route.jar 8080");
		}
	}
}