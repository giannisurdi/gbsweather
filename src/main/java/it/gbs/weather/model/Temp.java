package it.gbs.weather.model;

import java.io.Serializable;

public class Temp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3455491803850361278L;
	
	private Double max;

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

}
