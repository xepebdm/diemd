package br.com.diem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diem.model.TaxiModel;
import br.com.diem.service.TaxiService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/taxi")
public class TaxiController {

	
	@Autowired
	private TaxiService service;
	
	
	@ApiOperation(value = "Retorna uma lista com os pontos de Táxis cadastros")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna a lista com os pontos de Táxis cadastrados"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping
	public ResponseEntity<?> getAll(){
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Salva um novo ponto de Táxi na base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "Retorna o ponto de Táxi que foi cadastrado"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid TaxiModel taxi){
		return new ResponseEntity<>(service.save(taxi), HttpStatus.CREATED);
	}
	
	
}
