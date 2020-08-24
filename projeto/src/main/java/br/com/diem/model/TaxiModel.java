package br.com.diem.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

public class TaxiModel {

	
	@ApiModelProperty(value = "Nome do Ponto de Táxi", required = true)
	@NotBlank(message = "Nome do Ponto é obrigatório")
	private String nomeDoPonto;
	
	@ApiModelProperty(value = "Latitude do Ponto de Táxi", required = true)
	@NotBlank(message = "Latitude é obrigatório")
	private String latitude;
	
	@ApiModelProperty(value = "Longitude do Ponto de Táxi", required = true)
	@NotBlank(message = "Longitude é obrigatório")
	private String longitude;
	
	@JsonIgnore
	private Date horaCadastro;

	public String getNomeDoPonto() {
		return nomeDoPonto;
	}

	public void setNomeDoPonto(String nomeDoPonto) {
		this.nomeDoPonto = nomeDoPonto;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Date getHoraCadastro() {
		return horaCadastro;
	}

	public void setHoraCadastro(Date horaCadastro) {
		this.horaCadastro = horaCadastro;
	}
	
	
	/**
	 * O valor salvo no arquivo tem um formato próprio que é feito dentro do metódo toString
	 * 
	 * @return textoFormatado
	 */
	@Override
	public String toString() {
		
		StringBuilder text = new StringBuilder();
		text.append(this.nomeDoPonto);
		text.append("#" + this.latitude);
		text.append("#" + this.longitude);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		format.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		
		this.horaCadastro = new Date();
		
		text.append("#" + format.format(horaCadastro));
		
		return text.toString();
	}
}
