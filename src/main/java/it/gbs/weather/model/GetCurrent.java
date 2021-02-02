package it.gbs.weather.model;

import java.io.Serializable;

public class GetCurrent implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2733138910844111236L;
	private Coordinates coord;

	public Coordinates getCoord() {
		return coord;
	}

	public void setCoord(Coordinates coord) {
		this.coord = coord;
	}

}
