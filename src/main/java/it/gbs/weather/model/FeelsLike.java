package it.gbs.weather.model;

import java.io.Serializable;

public class FeelsLike implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7773595253322083211L;
	
	private Double day;

	public Double getDay() {
		return day;
	}

	public void setDay(Double day) {
		this.day = day;
	}

}
