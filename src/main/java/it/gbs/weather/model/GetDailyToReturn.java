package it.gbs.weather.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GetDailyToReturn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5143407917626359431L;
	
	private List<DailyToReturn> daily;
	
	private String cityName;
	
	public GetDailyToReturn() {
		daily = new ArrayList<>();
	}

	public List<DailyToReturn> getDaily() {
		return daily;
	}

	public void setDaily(List<DailyToReturn> daily) {
		this.daily = daily;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
