package com.wby.second;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
public class CalMain {
/*
 * 需求：
 * 
 * 1.使用 -n 参数控制生成题目的个数*
	2.使用 -r 参数控制题目中数值（自然数、真分数和真分数分母）的范围*
	3.生成的题目中计算过程不能产生负数，也就是说算术表达式中如果存在形如e1− e2的子表达式，那么e1≥ e2。*
	4.生成的题目中如果存在形如e1÷ e2的子表达式，那么其结果应是真分数。
	5.每道题目中出现的运算符个数不超过3个。
	6.程序一次运行生成的题目不能重复，即任何两道题目不能通过有限次交换+和×左右的算术表达式变换为同一道题目。
	7.生成的题目存入执行程序的当前目录下的Exercises.txt文件，
	其中真分数在输入输出时采用如下格式，真分数五分之三表示为3/5，真分数二又八分之三表示为2’3/8。
	8.程序应能支持一万道题目的生成。
	9.程序支持对给定的题目文件和答案文件，判定答案中的对错并进行数量统计，输入参数如下：
*/

	/*
	 *需求分析：
	 *核心代码：
	 *可以通过两个参数控制数的大小和运算式的数量
	 *选择语句，用随机数挑选符号，再由随机数生成表达式
	 * 将生成的语句和答案分开，分别存放在不同的txt文件
	 * ？判断重复，将结果相同的两个式子取出=》判断他们的运算符是否相同，若相同=》判断参数是否一致
	 */
	private static Random random = new Random();
	public static int range;
public static void main(String[] args){
	 Scanner sc= new Scanner(System.in);
     System.out.println("请输入产生几以内的数字：");
     range=sc.nextInt();
     System.out.println("请输入产生多少个运算表达式：");
     int num=sc.nextInt();
     int rightcount[]=new int[num+2];
 	int wrongcount[]=new int[num+2];
 	int right1=0;
	int wrong1=0;
	String[] results=new String[num];
	int i;

     for( i=0;i<num;i++){     
    	CalCreat tempCal = new CalCreat(); 
        String[] temp=new String[2];//定义生成的题目
        int fz01= (int) (random.nextInt(range));//分子
    	int fm01= (int) (random.nextInt(range));//分母
    	int fz02= (int) (random.nextInt(range));//另一个分子
    	int fm02= (int) (random.nextInt(range));//另一个分母
    	int sign;//运算符
    	sign= (int) (random.nextInt(4));
    	if(fm01!=0&&fm02!=0) {
    		//分母均不为0时生成带有分数的计算题，同时计算结果
    	if(sign==0) {
    		//加法
    		results[i] = tempCal.add(fz01, fm01, fz02, fm02, temp);    			   	
    		//System.out.println(results[i]);
    	}
    	if(sign==1&&fz01*fm02-fm01*fz02>=0) {
    		//一种减法
    		results[i]= tempCal.minus(fz01, fm01, fz02, fm02, temp);
    		
    	}
    	if(sign==1&&fz01*fm02-fm01*fz02<0) {
    		//另外一种减法
    		results[i]= tempCal.minus(fz02, fm02, fz01, fm01, temp);
    		
    	}
    	if(sign==2) {
    		//乘法  		
    		results[i]= tempCal.multiply(fz01, fm01, fz02, fm02, temp);
    		//System.out.println(results[i]);
    	}
    	if(sign==3&&fz02!=0) {
    		//除法
    		results[i]= tempCal.divide(fz01, fm01, fz02, fm02, temp);
    		//System.out.println(results[i]);
    	}
    	if(sign==3&&fz02==0) {
    		i--;
    		continue;
    	}
    	
    	}
    	else {
    		//分母至少一个为0时生成只含有整式的运算式，同时计算结果
    		fm01=1; fm02=1;
    	    if(sign==0) {
    	    	results[i] = tempCal.add(fz01, fm01, fz02, fm02, temp);    		
    		
    	}
    	if(sign==1&&fz01*fm02-fm01*fz02>=0) {
    		results[i]= tempCal.minus(fz01, fm01, fz02, fm02, temp);
    		
    	}
    	if(sign==1&&fz01*fm02-fm01*fz02<0) {
    		results[i]= tempCal.minus(fz02, fm02, fz01, fm01, temp);
    		
    	}
    	if(sign==2) {
    		results[i]= tempCal.multiply(fz01, fm01, fz02, fm02, temp);
    		
    	}
    	if(sign==3&&fz02!=0) {
    		results[i]= tempCal.divide(fz01, fm01, fz02, fm02, temp);
    		
    	}
    	if(sign==3&&fz02==0) {
    		i--;
    		continue;
    	}
    	
}       	
     FileWriter fw = null;
    try {
   
        File f=new File("D://excsies.txt");//题目写入
        fw = new FileWriter(f, true);
    } catch (IOException e) {
    	e.printStackTrace();
    }if(temp[0]!=null) {
    PrintWriter pw = new PrintWriter(fw);
    pw.println(i+1+"："+temp[0]);
    pw.flush();
    try {
        fw.flush();
        pw.close();
        fw.close();
    } catch (IOException e) {
    	e.printStackTrace();
    }}FileWriter fn = null;
    try {
        
            File f=new File("D://Answer.txt");//答案写入
            fn = new FileWriter(f, true);
        } catch (IOException e) {
        	e.printStackTrace();
        }if(temp[0]!=null) {
        PrintWriter pn = new PrintWriter(fn);
        pn.println(i+1+"："+results[i]);
        pn.flush();
        try {
            fn.flush();
            pn.close();
            fn.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }}
	}
     
     for (String result : results) {
 		System.out.println(result);
 	} 	
     
     System.out.println("输入ok提交！");
     Scanner sc1=new Scanner(System.in);
     String submit=sc1.nextLine();
	    if(submit.equals("ok")){
     String array[]=new String[num];
     try
		{   int k=0;
		    
			FileReader fr = new FileReader("D://Your_answers.txt");
			BufferedReader br = new BufferedReader(fr);
			String s ;
			while((s = br.readLine())!=null) {//读取小学生的答案
				array[k]=s;	k++;
				}br.close();
			fr.close();		
			}catch(IOException e){
				System.out.println("D://指定文件不存在");
			}
    for(int j=0;j<num;j++){
     	if(array[j].equals(results[j])) {//验证答案，统计正确和错误的个数
     		
     		rightcount[j]=j+1;
     		right1++;
     	}
     	else {
     		
     		wrongcount[j]=j+1;
     		wrong1++;
     	}
     }
    FileWriter fg = null;
    try {
        //反馈正确与错误题目的信息
            File f=new File("D://Grade.txt");
            fg = new FileWriter(f, true);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        PrintWriter pg = new PrintWriter(fg);
        pg.println(" ");
        pg.print("Correct:"+right1+"(");
        for (int j = 0; j <= num; j++) {
			if (rightcount[j] != 0) {
				 pg.print(rightcount[j] + ",");
			}
		}
        pg.println(")");
        pg.print("Wrong:"+wrong1+"(");
        for (int j = 0; j <= num; j++) {
			if (wrongcount[j] != 0) {
				 pg.print(wrongcount[j] + ",");
			}
		}
        pg.print(")");
        pg.flush();
        try {
            fg.flush();
            pg.close();
            fg.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }}
}
}
