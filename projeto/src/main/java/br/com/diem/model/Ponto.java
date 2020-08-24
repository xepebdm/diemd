package br.com.diem.model;

import io.swagger.annotations.ApiModelProperty;

public class Ponto {

	@ApiModelProperty(value = "Latitude do ponto de parada")
	private String lat;
	
	@ApiModelProperty(value = "Longitude do ponto de parada")
	private String lng;

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
	
	
}
