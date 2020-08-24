package br.com.diem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.diem.service.MainService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/")
public class MainController {

	@Autowired
	private MainService service;
	
	
	@ApiOperation(value = "Retorna uma lista com as linhas disponiveis na API DataPOA")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna a lista de linhas"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(path = "consultarLinhas" )
	public ResponseEntity<?> getListaDeOnibus(){
		return new ResponseEntity<>(service.getListaDeOnibus(), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Retorna uma lista com as linhas disponiveis na API DataPOA filtradas pelo nome da linha")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna a lista de linhas filtradas pelo nome"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(path = "consultarLinhas/nome/{nome}" )
	public ResponseEntity<?> getListaDeLinhasByNome(@PathVariable String nome){
		return new ResponseEntity<>(service.getListaDeOnibusByNome(nome), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Retorna o itinerário da linha disponivel na API DataPOA de acordo com o ID Linha informado")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna o itinerário da linha por ID Linha"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(path = "consultarItinerario/{id}" )
	public ResponseEntity<?> getListaItinerarioById(@PathVariable String id){
		return new ResponseEntity<>(service.getItinerarioByOnibusId(id), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Retorna o nome da linha e seus pontos de paradas de acordo com os valores passado em Latitude, Longitude e raio de Distância (KM) informado")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna a linha e os pontos de paradas"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(path = "consultarLinhasPorDistancia" )
	public ResponseEntity<?> getLinhasPorDistancia(
			@RequestParam(name = "latitude") String latitude, 
			@RequestParam(name = "longitude") String longitude, 
			@RequestParam(name = "distancia") String distancia) {
		return new ResponseEntity<>(service.getLinhasPorDistancia(latitude, longitude, distancia), HttpStatus.OK);
	}
	
	
	
}
