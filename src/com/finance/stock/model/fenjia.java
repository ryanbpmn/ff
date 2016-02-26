package com.finance.stock.model;

import java.util.Date;

public class fenjia implements Comparable<fenjia>{
	public Date jiaoyi_date; //交易时间.
	public double price;  //交易价格
	public String change; //价格变动
	public int vol;       //成交量
	public Double money;  //成交金额
	public String type;   //性质,买盘，买盘，中性盘.
	
	
	
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
