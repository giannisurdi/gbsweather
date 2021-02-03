package it.gbs.weather.testingweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

import it.gbs.weather.controller.WeatherForecastController;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingWeather {
	
	@Autowired
	private WeatherForecastController weatherForecastController;

	@Test
	public void contextLoads() {
		assertThat(weatherForecastController).isNotNull();
	}
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnMilanWeather() throws Exception {
		this.mockMvc.perform(get("/getNextTwoDaysWeatherByCityName?cityName=Milan")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Milan")));
	}
	
	@Test
	public void shouldReturnMilanWeatherFormatted() throws Exception {
		this.mockMvc.perform(get("/getNextTwoDaysWeatherByCityNameFormatted?cityName=Milan")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Milan")));
	}
	
	@Test
	public void shouldReturnDefaultErrorMessage() throws Exception {
		this.mockMvc.perform(get("/getNextTwoDaysWeatherByCityName")).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Given cityName is null")));
	}
	
	@Test
	public void shouldReturnNotFoundErrorMessage() throws Exception {
		this.mockMvc.perform(get("/getNextTwoDaysWeatherByCityName?cityName=WMilan")).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("city not found")));
	}

}
