package com.ae.dataGenerateTool.data;
@Deprecated
public enum CommonBoundary {
	 ZERO(0,1),MININT(-2147483648,2),MAXINT(2147483647,3),MINSHORT(-32768,4),MAXSHORT(32767,5),
	 MINLONG(-2147483648,6),MAXLONG(2147483647,7);
     public int index;
     public long num;
     CommonBoundary(long num,int index){
    	 this.index=index;
    	 this.num=num;
     }
     public long getNum() {
 		return this.num;
 	}
 	public int getIndex() {
 		return this.index;
 	}
}
