package vivian.jdk.Generics;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyPhotoCopy {

	public static void main(String[] args) {
		FileInputStream m = null;
		BufferedInputStream n = null;
		FileOutputStream s = null;
		BufferedOutputStream j = null;
		try {
			File filePath = new File("C:\\Users\\no1\\AppData\\Local\\Microsoft\\BingDesktop\\themes\\");
			for(String fileName:filePath.list()){
				m = new FileInputStream("C:\\Users\\no1\\AppData\\Local\\Microsoft\\BingDesktop\\themes\\"+fileName);// 原文件的路径
				n = new BufferedInputStream(m);
				s = new FileOutputStream("e:\\pingan\\photos\\"+fileName);// 准备把原文件复制到的路径
				j = new BufferedOutputStream(s);
				byte[] str = new byte[5000];
				while (n.read(str) != -1) {
					j.write(str);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				j.close();
				s.close();
				n.close();
				m.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
