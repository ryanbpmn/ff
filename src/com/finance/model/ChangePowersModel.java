package com.finance.model;

import java.util.Map;

public class ChangePowersModel {
	
	public ChangePowerModel[] changepowerModel;
	
	public double singlePower[] ;  //单笔大于100,小于-100；
	
	public double openPower;  //开盘power
	
	public double closePower;  //收盘power.
	
	public Map change_powers;
	
	public int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public ChangePowerModel[] getChangepowerModel() {
		return changepowerModel;
	}

	public void setChangepowerModel(ChangePowerModel[] changepowerModel) {
		this.changepowerModel = changepowerModel;
	}

	public double[] getSinglePower() {
		return singlePower;
	}

	public void setSinglePower(double[] singlePower) {
		this.singlePower = singlePower;
	}

	public double getOpenPower() {
		return openPower;
	}

	public void setOpenPower(double openPower) {
		this.openPower = openPower;
	}

	public double getClosePower() {
		return closePower;
	}

	public void setClosePower(double closePower) {
		this.closePower = closePower;
	}

	public Map getChange_powers() {
		return change_powers;
	}

	public void setChange_powers(Map change_powers) {
		this.change_powers = change_powers;
	}
	
	
}
