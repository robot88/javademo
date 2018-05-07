package com.robot.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author robot
 */
public class ClientDemo {
	public static final String IP_ADDR = "10.5.31.133";
	public static final int PORT = 1888;

	public static void main(String[] args) {
		System.out.println("客户端启动...");
		while (true) {
			Socket socket = null;
			try {
				// 创建一个流套接字并将其连接到指定主机上的指定端口号
				socket = new Socket(IP_ADDR, PORT);
//				socket.setSoTimeout(1000);

				// 读取服务器端数据
				DataInputStream input = new DataInputStream(socket.getInputStream());
				// 向服务器端发送数据
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF(
						"<root type='license' say='sKKo2iWak93lSL9grppMx+Hn2dZ5RrQP9zyXMZCx7JS6RIoEvjuXfRKtG871TUOv1XR3ajsbZnsjBjAZW7XHY+KIYDh+XBir47SYy7XqitaDh5A1z/G5vVsQ9U6BjFIwpeDGzr/AsWEjeRfxDrpJGa6nGZXrqYi7JsxOQjxTD54=' decode='0' />");

				String ret = input.readUTF();
				System.out.println("服务器端返回过来的是: " + ret);

				out.close();
				input.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						socket = null;
						System.out.println(e.getMessage());
					}
				}
			}
		}
	}
}
