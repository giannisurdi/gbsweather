package it.gbs.weather.exception;

public class WeatherErrorMessage {
	
	private String errorMessage;
	
	public WeatherErrorMessage(Exception e) {
		errorMessage = e.getMessage();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
