package com.finance.util;

public class ArrayUtil {
	 /**
     * 希尔排序（最小增量排序）
     * 基本思想：算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，每组中记录的下标相差d.对每组中全部元素进行直接插入排序，然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后，排序完成。
     * @param array
     */
    public  static void shellSort(Double[] array){  
        double d1=array.length;  
        double temp=0;  
        while(true){  
            d1= Math.ceil(d1/2);  
            int d=(int) d1;  
            for(int x=0;x<d;x++){  
                for(int i=x+d;i<array.length;i+=d){  
                    int j=i-d;  
                    temp=array[i];  
                    for(;j>=0&&temp<array[j];j-=d){  
                        array[j+d]=array[j];  
                    }  
                    array[j+d]=temp;  
                }  
            }  
            if(d==1)  
                break;  
        }  
    }  
     
    public static void selectSort(Double[] array){
        int position=0;  
        for(int i=0;i<array.length;i++){  
               
            int j=i+1;  
            position=i;  
           // System.out.print(array[i]);
            Double temp=array[i];  
            for(;j<array.length;j++){  
            //	System.out.println(array[j]+"-"+temp);
            if(array[j]<temp){  
                temp=array[j];  
                position=j;  
            }  
            }  
            array[position]=array[i];  
            array[i]=temp;  
        } 
    }
    /**
     * 打印数组中的所有元素
     */
     
    public static void printArray(Object[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }
}
