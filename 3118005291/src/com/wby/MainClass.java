package com.wby;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MainClass {
	
	public static void main(String[] args) {
		Scanner scn =null;
		try {
			scn = new Scanner(System.in);
			System.out.print("请输入论文原文的路径");
			String txtPath = scn.next();
			StringBuffer sb2 = getText.getArticle(txtPath);
			
			System.out.print("请输入抄袭论文的路径");
			String textPath = scn.next();		
			StringBuffer sb1 = getText.getArticle(textPath);
								
			SimHash hash1 = new  SimHash(sb1.toString(),  64 );
			SimHash hash2 = new  SimHash(sb2.toString(),  64 );
			
			int distance = hash1.getDistance(hash1.getStrSimHash() , hash2.getStrSimHash());
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
	        //System.out.println(hash1.hammingDistance(hash2) + " "  + distance);
	        //System.out.println("hash1和hash2的相似率"+ decimalFormat.format((distance/64.0)));
	        
	        
	        String outpath = "D:\\temp\\output.txt";
	        	        
	        String content = "\r\n抄袭论文文件的路径：" + textPath + "\r\n论文原文的路径：" + txtPath + "\r\n论文重复率为" + decimalFormat.format((distance/64.0));
	        getText.writeArticle(outpath, content);
	        
		} catch (NullPointerException e1) {
			System.out.println("输入路径错误或文件不存在");
			e1.printStackTrace();
		} catch (Exception e) {
			System.out.println("输入错误导致程序出错");
			e.printStackTrace();
		} finally {
			if(scn != null) {
				scn.close();
			}
		}
		
       
	}
}
