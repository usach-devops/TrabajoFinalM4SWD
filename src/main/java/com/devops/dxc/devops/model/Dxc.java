package com.devops.dxc.devops.model;

import java.io.Serializable;

public class Dxc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2988002029080131424L;
	
	private int dxc;
	private int saldo;
	private int impuesto;
	private int sueldo;
	private int ahorro;

	public Dxc(int ahorro, int sueldo){
		this.ahorro = ahorro;
		this.sueldo = sueldo;
	}

	public Dxc() {
	}

	public int getDxc() {
		int diez = 0;
		diez = Util.getDxc(ahorro);
		setDxc(diez);
		return this.dxc;
	}
	public void setDxc(int dxc) {
		this.dxc = dxc;
	}
	public int getSaldo() {
		int s = 0;
		s = Util.getSaldo(ahorro, dxc);
		setSaldo(s);
		return this.saldo;		
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	public int getImpuesto() {
		int i = 0;
		i = Util.getImpuesto(sueldo, dxc);
		setImpuesto(i);
		return this.impuesto;
	}
	public void setImpuesto(int impuesto) {
		this.impuesto = impuesto;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public int getAhorro() {
		return ahorro;
	}

	public void setAhorro(int ahorro) {
		this.ahorro = ahorro;
	}
}
