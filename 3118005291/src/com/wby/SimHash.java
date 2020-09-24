package com.wby;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SimHash {
	//定义一个表示查重特征的SimHash
	     private String tokens;
	     private BigInteger intSimHash;	 
	     private String strSimHash;	 
	     private int  hashbits; 
	     public SimHash(String tokens) {
	         this .tokens = tokens;
	         this .intSimHash = this .simHash();
	     }
	 
	     public SimHash(String tokens,  int hashbits) {
	         this .tokens = tokens;
	         this .hashbits = hashbits;
	         this .intSimHash = this .simHash();
	     }

	     public BigInteger simHash() {
	    	 /**
	    	  * 思路：
	    	  * 1、将文本进行划分，按照格式分词
	    	  * 2、将每一个分词hash为一组固定长度的数列.比如 64bit 的一个整数.
	    	  * 3、建立一个长度为64的整数数组，对每一个分词hash后的数列进行判断,如果是1000...1,那么数组的第一位和
	    	  *        末尾一位加1，中间的62位减一,逢1加1,逢0减1.直到把所有的分词hash数列全部判断完毕.
	    	  * 4、对数组进行判断,大于0的记为1,小于等于0的记为0,得到一个 64bit 的签名。
	    	  **/
	         int [] v = new  int [ this .hashbits]; 
	         StringTokenizer stringTokens = new  StringTokenizer( this .tokens,"，。！、：“”");
	         while (stringTokens.hasMoreTokens()) {
	             String temp = stringTokens.nextToken();
	             // 
	             BigInteger t = this .hash(temp);
	             for ( int  i =  0 ; i <  this .hashbits; i++) {
	                 BigInteger bitmask = new  BigInteger( "1" ).shiftLeft(i);
	                 if (t.and(bitmask).signum() !=  0 ) {
	                     // 计算整个文档的所有特征的向量和
	                     // 这里实际使用中需要 +- 权重，而不是简单的 +1/-1，
	                     v[i] += 1 ;
	                 } else  {
	                     v[i] -= 1 ;
	                 }
	             }
	         }
	         BigInteger fingerprint = new  BigInteger( "0" );
	         StringBuffer simHashBuffer = new  StringBuffer();
	         for ( int  i =  0 ; i <  this .hashbits; i++) {
	             if (v[i] >=  0 ) {
	                 fingerprint = fingerprint.add( new BigInteger( "1" ).shiftLeft(i));
	                 simHashBuffer.append( "1" );
	             } else  {
	                 simHashBuffer.append( "0" );
	             }
	         }
 	         this .strSimHash = simHashBuffer.toString();
 	         
	         return fingerprint;
	     }
	     public BigInteger simHash2() {
	         // 定义特征向量/数组
	         int [] v = new  int [ this .hashbits];
	         StringTokenizer stringTokens = new  StringTokenizer( this .tokens,"，。！、：“”");
	         while (stringTokens.hasMoreTokens()) {
	             String temp = stringTokens.nextToken();	         
	             BigInteger t = this .hash(temp);
	             for ( int  i =  0 ; i <  this .hashbits; i++) {
	                 BigInteger bitmask = new  BigInteger( "1" ).shiftLeft(i);	                
	                 if (t.and(bitmask).signum() !=  0 ) {	                   
	                     v[i] += 1 ;
	                 } else  {
	                     v[i] -= 1 ;
	                 }
	             }
	         }
	         BigInteger fingerprint = new  BigInteger( "0" );
	         StringBuffer simHashBuffer = new  StringBuffer();
	         for ( int  i =  0 ; i <  this .hashbits; i++) {	        
	             if (v[i] >=  0 ) {
	                 fingerprint = fingerprint.add( new BigInteger( "1" ).shiftLeft(i));
	                 simHashBuffer.append( "1" );
	             } else  {
	                 simHashBuffer.append( "0" );
	             }
	         }
	         this .strSimHash = simHashBuffer.toString();
	         System.out.println( this .strSimHash + " length "  +  this .strSimHash.length());
	         return fingerprint;
	     }
	     private BigInteger hash(String source) {
	         if (source ==  null || source.length() ==  0 ) {
	             return new  BigInteger( "0" );
	         } else  {
	             char [] sourceArray = source.toCharArray();
	             BigInteger x = BigInteger.valueOf((( long ) sourceArray[ 0 ]) <<  7 );
	             BigInteger m = new  BigInteger( "1000003" );
	             BigInteger mask = new  BigInteger( "2" ).pow( this .hashbits).subtract( new BigInteger( "1" ));
	             for ( char  item : sourceArray) {
	                 BigInteger temp = BigInteger.valueOf(( long ) item);
	                 x = x.multiply(m).xor(temp).and(mask);
	             }
	             x = x.xor( new BigInteger(String.valueOf(source.length())));
	             if (x.equals( new  BigInteger( "-1" ))) {
	                 x = new  BigInteger( "-2" );
	             }
	             return x;
	         }
	     }
	 
	     public int  hammingDistance(SimHash other) {
	 
	         BigInteger x = this .intSimHash.xor(other.intSimHash);
	         int tot =  0 ;
	 
	         // 统计x中二进制位数为1的个数
	         //统计做出操作后n的数量
	         while (x.signum() !=  0 ) {
	             tot += 1 ;
	             x = x.and(x.subtract( new BigInteger( "1" )));
	         }
	         return tot;
	     }
	 
	     public int  getDistance(String str1, String str2) {
	         int distance;
	         if (str1.length() != str2.length()) {
	             distance = - 1 ;
	         } else  {
	             distance = 0 ;
	             for ( int  i =  0 ; i < str1.length(); i++) {
	                 if (str1.charAt(i) == str2.charAt(i)) {
	                     distance++;
	                 }
	             }
	         }
	         return distance;
	     }
	  
	 
	     public String getTokens() {
			return tokens;
		}

		public void setTokens(String tokens) {
			this.tokens = tokens;
		}

		public BigInteger getIntSimHash() {
			return intSimHash;
		}

		public void setIntSimHash(BigInteger intSimHash) {
			this.intSimHash = intSimHash;
		}

		public String getStrSimHash() {
			return strSimHash;
		}

		public void setStrSimHash(String strSimHash) {
			this.strSimHash = strSimHash;
		}

		

}
