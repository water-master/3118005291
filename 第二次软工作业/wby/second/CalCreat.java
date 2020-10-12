package com.wby.second;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class CalCreat {
	
	public static String diviNum(int num01, int num02) {
		// ����Լ��,
		int diviNum = 1;
		//�ҵ����Լ��
		for (int i = num01; i >= 1; i--) {
			if (num01 % i == 0 && num02 % i == 0) {
				diviNum = i;
				break;
			}
		}
		int fzNum = num01 / diviNum;
		int fmNum = num02 / diviNum;
		//���ӷ�ĸԼ��
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
		//�жϼٷ����������ٷ���Ϊ������
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
	 * �ӷ�����
	 * @param fz01 ����1
	 * @param fm01 ��ĸ1
	 * @param fz02 ����2
	 * @param fm02 ��ĸ2
	 * @return ���ؽ��
	 */
	public static String add(int fz01,int fm01,int fz02,int fm02,String[] temp) {
		int resultFz,resultFm;
		String result = null;
		resultFz = fz01*fm02+fz02*fm01;
		resultFm = fm01 * fm02;
		if(resultFz!=0) {
			//������ӷ�ĸ���������������
			int gcdNum = gcd(resultFz,resultFm);
			resultFz /= gcdNum;
			resultFm /= gcdNum;
		}		
		temp[0]=turn(fz01,fm01)+'+'+turn(fz02,fm02)+'=';
		System.out.println(temp[0]);
		
		return diviNum(resultFz, resultFm);
	}

	/**
	 * ��������
	 * @param fz01 ����1
	 * @param fm01 ��ĸ1
	 * @param fz02 ����2
	 * @param fm02 ��ĸ2
	 * @return ���ؼ�����
	 */
	public static String  minus(int fz01,int fm01,int fz02,int fm02,String[] temp) {
		int resultFz,resultFm;
		resultFz = fz01*fm02-fz02*fm01;
		resultFm = fm01*fm02;
		//������ӷ�ĸ���������������
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
	 * �˷�����
	 * @param fz01 ����1
	 * @param fm01 ��ĸ1
	 * @param fz02 ����2
	 * @param fm02 ��ĸ2
	 * @return ���ؼ�����
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
		temp[0]=turn(fz01,fm01)+'��'+turn(fz02,fm02)+'=';
		System.out.println(temp[0]);
		return diviNum(resultFz, resultFm);
	}

	/**
	 * ��������
	 * @param fz01 ����1
	 * @param fm01 ��ĸ1
	 * @param fz02 ����2
	 * @param fm02 ��ĸ2
	 * @return ���ؼ�����
	 */
	public static String divide(int fz01,int fm01,int fz02,int fm02,String[] temp) {
		int resultFz,resultFm;
		resultFz=fz01*fm02;
		resultFm=fm01*fz02;
		//������ӷ�ĸ���������������
		if(resultFz!=0) {
			int gcdNum =gcd(resultFz,resultFm);
			resultFz /= gcdNum;
			resultFm /= gcdNum;
		}
		temp[0]=turn(fz01,fm01)+'��'+turn(fz02,fm02)+'=';
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
