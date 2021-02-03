package it.gbs.weather.model;

import java.io.Serializable;

public class DailyFormatted implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5143407917626359431L;
	
	private String maximum;
	
	private String feelsLikeTemperature;
	
	private String humidity;
	
	private String dateTime;

	public String getMaximum() {
		return maximum;
	}

	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}

	public String getFeelsLikeTemperature() {
		return feelsLikeTemperature;
	}

	public void setFeelsLikeTemperature(String feelsLikeTemperature) {
		this.feelsLikeTemperature = feelsLikeTemperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
}
