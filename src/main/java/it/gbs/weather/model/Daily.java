package it.gbs.weather.model;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Daily implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5143407917626359431L;
	
	private Temp temp;
	
	private FeelsLike feels_like;
	
	private Double humidity;
	
	private Long dt;

	public Long getDt() {
		return dt;
	}

	public void setDt(Long dt) {
		this.dt = dt;
	}

	public Temp getTemp() {
		return temp;
	}

	public void setTemp(Temp temp) {
		this.temp = temp;
	}

	public FeelsLike getFeels_like() {
		return feels_like;
	}

	public void setFeels_like(FeelsLike feels_like) {
		this.feels_like = feels_like;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	
	public String getDateTime() {
	    Date date = new Date(dt*1000);
	    Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
	    return format.format(date);
	}

}
