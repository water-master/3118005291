package com.wby.second;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class CalCreat {
	
	public static String diviNum(int num01, int num02) {
		// 分数约分,
		int diviNum = 1;
		//找到最大公约数
		for (int i = num01; i >= 1; i--) {
			if (num01 % i == 0 && num02 % i == 0) {
				diviNum = i;
				break;
			}
		}
		int fzNum = num01 / diviNum;
		int fmNum = num02 / diviNum;
		//分子分母约分
		if (fzNum == 0) 
		{
			return "0";
		}
		if(fmNum==1) return fzNum+"";
		else  
			return turn(fzNum,fmNum);
		
	}
	
	public static String turn(int fz,int fm) 
	{
		//判断假分数，并化假分数为带分数
		if(fz>=fm) {
			int normalNum;
			normalNum=fz/fm;
			int fzNum;
			fzNum=fz%fm; 
			{if(fzNum==0) {return normalNum+"";}
			return normalNum+"'"+fzNum+"/"+fm;}
		}
		return fz+"/"+fm;
	}
	
	/**
	 * 加法运算
	 * @param fz01 分子1
	 * @param fm01 分母1
	 * @param fz02 分子2
	 * @param fm02 分母2
	 * @return 返回结果
	 */
	public static String add(int fz01,int fm01,int fz02,int fm02,String[] temp) {
		int resultFz,resultFm;
		String result = null;
		resultFz = fz01*fm02+fz02*fm01;
		resultFm = fm01 * fm02;
		if(resultFz!=0) {
			//化简分子分母（除以最大公因数）
			int gcdNum = gcd(resultFz,resultFm);
			resultFz /= gcdNum;
			resultFm /= gcdNum;
		}		
		temp[0]=turn(fz01,fm01)+'+'+turn(fz02,fm02)+'=';
		System.out.println(temp[0]);
		
		return diviNum(resultFz, resultFm);
	}

	/**
	 * 减法运算
	 * @param fz01 分子1
	 * @param fm01 分母1
	 * @param fz02 分子2
	 * @param fm02 分母2
	 * @return 返回计算结果
	 */
	public static String  minus(int fz01,int fm01,int fz02,int fm02,String[] temp) {
		int resultFz,resultFm;
		resultFz = fz01*fm02-fz02*fm01;
		resultFm = fm01*fm02;
		//化简分子分母（除以最大公因数）
		if(resultFz!=0) {
			int gcdNum = gcd(resultFz,resultFm);
			resultFz /= gcdNum;
			resultFm /= gcdNum;
		}
		temp[0]=turn(fz01,fm01)+'-'+turn(fz02,fm02)+'=';
		System.out.println(temp[0]);
		
		return diviNum(resultFz, resultFm);
	}

	/**
	 * 乘法运算
	 * @param fz01 分子1
	 * @param fm01 分母1
	 * @param fz02 分子2
	 * @param fm02 分母2
	 * @return 返回计算结果
	 */
	public static String multiply(int fz01,int fm01,int fz02,int fm02,String[] temp) {
		int resultFz ,resultFm;
		resultFz=fz01*fz02;
		resultFm=fm01*fm02;
		if(resultFz!=0) {
			int gcdNum = gcd(resultFz,resultFm);
			resultFz /= gcdNum;
			resultFm /= gcdNum;
		}
		temp[0]=turn(fz01,fm01)+'×'+turn(fz02,fm02)+'=';
		System.out.println(temp[0]);
		return diviNum(resultFz, resultFm);
	}

	/**
	 * 除法运算
	 * @param fz01 分子1
	 * @param fm01 分母1
	 * @param fz02 分子2
	 * @param fm02 分母2
	 * @return 返回计算结果
	 */
	public static String divide(int fz01,int fm01,int fz02,int fm02,String[] temp) {
		int resultFz,resultFm;
		resultFz=fz01*fm02;
		resultFm=fm01*fz02;
		//化简分子分母（除以最大公因数）
		if(resultFz!=0) {
			int gcdNum =gcd(resultFz,resultFm);
			resultFz /= gcdNum;
			resultFm /= gcdNum;
		}
		temp[0]=turn(fz01,fm01)+'÷'+turn(fz02,fm02)+'=';
		System.out.println(temp[0]);
		return diviNum(resultFz, resultFm);
	}
	
    public static int gcd(int num01, int num02) {
        int num = 0;
        while (num02 != 0) {
            num = num01 % num02;
            num01 = num02;
            num02 = num;
        }
        return num01;
    }
    
    
}
