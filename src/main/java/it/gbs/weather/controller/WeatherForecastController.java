package it.gbs.weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.gbs.weather.message.WeatherErrorMessage;
import it.gbs.weather.service.OpenWeatherService;

@RestController
public class WeatherForecastController {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastController.class);
	
	@Autowired
	private OpenWeatherService openWeatherService;
	
	@RequestMapping("/getNextTwoDaysWeatherByCityName")
	@ResponseBody
	public ResponseEntity<Object> getNextTwoDaysWeatherByCityName(String cityName) throws Exception {
		logger.info("Request getNextTwoDaysWeatherByCityName received with cityName:" + cityName);
		try {
			return new ResponseEntity<>(openWeatherService.getNextTwoDayWeather(cityName),HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error in getNextTwoDaysWeatherByCityName", e);
			return new ResponseEntity<Object>(
					new WeatherErrorMessage(e), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping("/getNextTwoDaysWeatherByCityNameFormatted")
	@ResponseBody
	public ResponseEntity<Object> getNextTwoDaysWeatherByCityNameFormatted(String cityName) {
		logger.info("Request getNextTwoDaysWeatherByCityName received with cityName:" + cityName);
		try {
			return new ResponseEntity<>(openWeatherService.getNextTwoDayWeatherFormatted(cityName),HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error in getNextTwoDaysWeatherByCityNameFormatted", e);
			return new ResponseEntity<Object>(
					new WeatherErrorMessage(e), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
	}

}
