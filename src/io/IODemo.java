package io;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class IODemo {
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
	
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
	
	//xml 文件操作
	@Test
	public void dom4j(){
		SAXReader reader = new SAXReader();
		XMLWriter writer = null;
		try {
			//读取
			Document document = reader.read(new File("demo.html"));
			System.out.println(document.getRootElement());
			Element root = document.getRootElement();
			System.out.println(root.element("head"));
			Element title = (Element) root.selectSingleNode("//title");
			//修改
			title.setText("Demo");
			System.out.println(title.getTextTrim());
			//保存
			OutputFormat format = OutputFormat.createPrettyPrint();
			writer = new XMLWriter(new FileOutputStream("demo.html"),format);
			writer.write(document);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
