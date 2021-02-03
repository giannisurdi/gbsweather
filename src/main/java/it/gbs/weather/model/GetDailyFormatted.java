package it.gbs.weather.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GetDailyFormatted implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5143407917626359431L;
	
	private List<DailyFormatted> daily;
	
	private String cityName;
	
	public GetDailyFormatted() {
		daily = new ArrayList<>();
	}

	public List<DailyFormatted> getDaily() {
		return daily;
	}

	public void setDaily(List<DailyFormatted> daily) {
		this.daily = daily;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
