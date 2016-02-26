package com.finance.model;

public class ChangeVolMoneyModel {
	public String change;
	public double vol;
	public double money;
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public double getVol() {
		return vol;
	}
	public void setVol(double vol) {
		this.vol = vol;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "ChangeVolMoneyModel [change=" + change + ", vol=" + vol
				+ ", money=" + money + "]";
	}
	
}
