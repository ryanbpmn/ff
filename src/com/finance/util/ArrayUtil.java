package com.finance.util;

public class ArrayUtil {
	 /**
     * ϣ��������С��������
     * ����˼�룺�㷨�Ƚ�Ҫ�����һ������ĳ������d��n/2,nΪҪ�������ĸ������ֳ������飬ÿ���м�¼���±����d.��ÿ����ȫ��Ԫ�ؽ���ֱ�Ӳ�������Ȼ������һ����С��������d/2���������з��飬��ÿ�����ٽ���ֱ�Ӳ������򡣵���������1ʱ������ֱ�Ӳ��������������ɡ�
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
     * ��ӡ�����е�����Ԫ��
     */
     
    public static void printArray(Object[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }
}
