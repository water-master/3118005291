package com.wby;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.InputStreamReader;

public class getText {
	/**传入txt路径读取txt文件
     * @param 
     * @return 返回读取到的内容
     */
    public static StringBuffer getArticle(String txtPath) {
        File file = new File(txtPath);
        
        if(file.isFile() && file.exists()){
            try (FileInputStream FileinputStream = new FileInputStream(file);
                 InputStreamReader inputStreamReader = new InputStreamReader(FileinputStream);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);){
            	//流输入
                StringBuffer sb = new StringBuffer();
                String text = null;
                while((text = bufferedReader.readLine()) != null){
                    sb.append(text);
                }
                return sb;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    

    /**写入txt文件
     * @paramtxtPath
     * @param
     */
    public static void writeArticle(String txtPath,String txt){    
       File file = new File(txtPath);
       try (FileOutputStream fileOutputStream = new FileOutputStream(file);){
           if(!file.exists()){
               //判断文件是否存在，如果不存在就新建一个txt
               file.createNewFile();
           }        
           fileOutputStream.write(txt.getBytes());
           fileOutputStream.flush();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
   
}
