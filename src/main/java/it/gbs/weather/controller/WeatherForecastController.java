package it.gbs.weather.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class WeatherForecastController {
	
	@RequestMapping("/getCurrentWeatherByCityName")
	public String getCurrentWeatherByCityName() {
		return "Greetings from Spring Boot!";
	}
	
	@RequestMapping("/getNextTwoDaysWeatherByCityName")
	public String getNextTwoDaysWeatherByCityName() {
		return "Greetings from Spring Boot!";
	}

}
