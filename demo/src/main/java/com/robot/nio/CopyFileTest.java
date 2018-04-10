package com.robot.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * @author robot
 */
public class CopyFileTest {
	public static void main(String[] args) throws IOException {
		FileInputStream inputStream = new FileInputStream("E:\\test\\1.txt");
		FileOutputStream outputStream = new FileOutputStream("E:\\test\\2.txt");
		FileChannel inputChannel = inputStream.getChannel();
		FileChannel outputChannel = outputStream.getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		int size;
		do {
			byteBuffer.clear();
			size = inputChannel.read(byteBuffer);
			byteBuffer.flip();
			outputChannel.write(byteBuffer);
		} while (size != -1);
		inputStream.close();
		outputStream.close();
	}
}
