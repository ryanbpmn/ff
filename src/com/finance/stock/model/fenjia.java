package com.finance.stock.model;

import java.util.Date;

public class fenjia implements Comparable<fenjia>{
	public Date jiaoyi_date; //����ʱ��.
	public double price;  //���׼۸�
	public String change; //�۸�䶯
	public int vol;       //�ɽ���
	public Double money;  //�ɽ����
	public String type;   //����,���̣����̣�������.
	
	
	
	public String toString(){
		return jiaoyi_date+" "+price+" "+change+" "+vol+" "+type;
	}
	@Override
	public int compareTo(fenjia arg0) {
		// TODO Auto-generated method stub
		System.out.println("compare:"+this.getPrice().compareTo(arg0.getPrice()));
		return this.getPrice().compareTo(arg0.getPrice());
	}
	public Date getJiaoyi_date() {
		return jiaoyi_date;
	}
	public void setJiaoyi_date(Date jiaoyi_date) {
		this.jiaoyi_date = jiaoyi_date;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public int getVol() {
		return vol;
	}
	public void setVol(int vol) {
		this.vol = vol;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
