package br.com.diem.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection = "itinerarios")
public class Itinerario {

	@ApiModelProperty(value = "ID da Linha", required = true)
	@Id
	@NotBlank
	private String idlinha;
	
	@ApiModelProperty(value = "Nome da Linha", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(value = "Código da Linha", required = true)
	@NotBlank
	private String codigo;
	
	@ApiModelProperty(value = "Pontos de paradas da Linha", required = true)
	private List<Ponto> pontos;

	public List<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}

	public String getIdlinha() {
		return idlinha;
	}

	public void setIdlinha(String idlinha) {
		this.idlinha = idlinha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
