package it.gbs.weather.model;

import java.io.Serializable;
import java.util.List;

public class GetDaily implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5143407917626359431L;
	
	private List<Daily> daily;
	
	private String cityName;

	public List<Daily> getDaily() {
		return daily;
	}

	public void setDaily(List<Daily> daily) {
		this.daily = daily;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
