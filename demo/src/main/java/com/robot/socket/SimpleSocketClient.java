package com.robot.socket;

import com.codyy.oc.commons.utils.SecurityUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.Date;

/**
 * @author robot
 */
public class SimpleSocketClient {
	private BufferedWriter bufferedWriter;
	private BufferedReader bufferedReader;

	public static void main(String[] args) {
		new SimpleSocketClient();
	}

	public SimpleSocketClient() {
		String testServerName = "10.5.31.130";
		int port = 1888;
		String result;
		String from = "f28caa64293a4d02bf9374c72eac90f9";
		String to = "9513750";
		String gid = "9513750";
		String name = "123";
		String license = "smkOMuOgtpbk8VAkDyUsIethocl8kWI62HblqZXCrhfWmc%2BTEhrDogpPPaxflZvV";
		String cipher = SecurityUtil.socketMap65Encrypt("codyy", "codyy");

		try {
			Socket socket = openSocket(testServerName, port);

			// login
			String instruct = loginInstruct(from, to, gid, name, license, cipher);
			result = writeToAndReadFromSocket(socket, instruct);
			System.out.println(result);

			// noticeOnline
			instruct = noticeOnlineInstruct(from, to, gid, name, cipher);
			result = writeToAndReadFromSocket(socket, instruct);
			System.out.println(result);
			// chatGroup
			instruct = chatGroupInstruct(from, to, gid, name, cipher, "hello");
			result = writeToAndReadFromSocket(socket, instruct);
			System.out.println(result);

			// logout
			instruct = logoutInstruct(from, to);
			result = writeToAndReadFromSocket(socket, instruct);
			System.out.println(result);

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String writeToAndReadFromSocket(Socket socket, String writeTo) throws Exception {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bufferedWriter.write(writeTo);
			bufferedWriter.flush();

			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str;
			str = bufferedReader.readLine();
			return str;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private Socket openSocket(String server, int port) throws Exception {
		Socket socket;

		try {
			InetAddress inteAddress = InetAddress.getByName(server);
			SocketAddress socketAddress = new InetSocketAddress(inteAddress, port);

			socket = new Socket();

			int timeoutInMs = 10000;
			socket.connect(socketAddress, timeoutInMs);

			return socket;
		} catch (SocketTimeoutException ste) {
			System.err.println("Timed out waiting for the socket.");
			ste.printStackTrace();
			throw ste;
		}
	}

	private String loginInstruct(String from, String to, String gid, String name, String license, String cipher) {
		StringBuilder str = new StringBuilder();
		str.append("<root api='login' type='login'");
		str.append(" enterpriseId='1' serverType='0'");
		str.append(" from='").append(from).append("'");
		str.append(" to='").append(to).append("'");
		str.append(" gid='").append(gid).append("'");
		str.append(" send_nick='").append(name).append("'");
		str.append(" license='").append(license).append("'");
		str.append(" cipher='").append(cipher).append("'");
		str.append(" time='").append(System.currentTimeMillis()).append("'/>");
		return str.toString();
	}

	private String noticeOnlineInstruct(String from, String to, String gid, String name, String cipher) {
		StringBuilder str = new StringBuilder();
		str.append("<root api='noticeOnline' type='noticeOnline'");
		str.append(" enterpriseId='1' serverType='0'");
		str.append(" from='").append(from).append("'");
		str.append(" to='").append(to).append("'");
		str.append(" gid='").append(gid).append("'");
		str.append(" send_nick='").append(name).append("'");
		str.append(" cipher='").append(cipher).append("'");
		str.append(" time='").append(System.currentTimeMillis()).append("'/>");
		return str.toString();
	}

	private String chatGroupInstruct(String from, String to, String group, String name, String cipher, String msg) {
		StringBuilder str = new StringBuilder();
		str.append("<root api='sendMsgToAll' type='meet'");
		str.append(" enterpriseId='1' serverType='0'");
		str.append(" from='").append(from).append("'");
		str.append(" to='").append(to).append("'");
		str.append(" group='").append(group).append("'");
		str.append(" send_nick='").append(name).append("'");
		str.append(" say='").append(msg).append("'");
		str.append(" cipher='").append(cipher).append("'");
		str.append(" time='").append(System.currentTimeMillis()).append("'/>");
		return str.toString();
	}

	private String logoutInstruct(String from, String to) {
		StringBuilder str = new StringBuilder();
		str.append("<root type='loginout'");
		str.append(" enterpriseId='1' serverType='0'");
		str.append(" from='").append(from).append("'");
		str.append(" to='").append(to).append("''/>");
		return str.toString();
	}

}
