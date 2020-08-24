package br.com.diem.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection="onibus")
public class Onibus {

	@ApiModelProperty(value = "ID da Linha", required = true)
	@Id
	@NotBlank
	private String id;
	
	@ApiModelProperty(value = "Código da Linha", required = true)
	@NotBlank
	private String codigo;
	
	@ApiModelProperty(value = "Nome da Linha", required = true)
	@NotBlank
	private String nome;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

  
}
