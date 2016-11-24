package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class IODemo {
	
	@Test
	public void copyTestWithChar() {
		String copiedFilePath = "demo.html";
		String savePath = "copy.html";
		System.out.println(copyWithChar(copiedFilePath, savePath));
	}
	
	@Test
	public void copyTestWithByte(){
		String copiedFilePath = "demo.html";
		String savePath = "copy.html";
		System.out.println(copyWithByte(copiedFilePath, savePath));
	}
	
	@Test
	public void dom4j(){
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new File("demo.html"));
			System.out.println(document.getRootElement());
			Element root = document.getRootElement();
			System.out.println(root.element("head"));
			
			Node title = root.selectSingleNode("//title");
			title.setText("Demo");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 复制文件(字符流)
	 * @param copiedFilePath 被复制文件路径
	 * @param copyFileNamePath 保存路径
	 * @return
	 */
	public String copyWithChar(String copiedFilePath, String savePath){
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(copiedFilePath));
			bw = new BufferedWriter(new FileWriter(savePath));
			
			String line = br.readLine();
			while (line !=null) {
				bw.append(line+"\n");
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "File not found!";
		} catch (IOException e) {
			e.printStackTrace();
			return "Copy error!";
		}finally{
			try {
				if (br != null) {
					br.close();
				}
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "Close exception!";
			}
		}
		return "Success";
	}
	
	/**
	 * 复制文件(字节流)
	 * @param copiedFilePath
	 * @param savePath
	 * @return
	 */
	public String copyWithByte(String copiedFilePath, String savePath){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(new File(copiedFilePath)));
			bos = new BufferedOutputStream(new FileOutputStream(new File(savePath)));
			int length ;
			byte[] content = new byte[16];
			while ((length = bis.read(content)) != -1) {
				System.out.println(new String(content, 0, length));
				bos.write(content, 0, length);
//				bos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Copy error!";
		}finally {
			try {
				if (bis !=null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "Close exception!";
			}
		}
		return "Success";
	}
	
}
