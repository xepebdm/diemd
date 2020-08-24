package br.com.diem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diem.component.MainComponent;
import br.com.diem.model.Itinerario;
import br.com.diem.service.ItinerarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/itinerarios")
public class ItinerarioController {

	
	@Autowired
	private ItinerarioService service;
	
	@Autowired
	private MainComponent mainComponent;
	
	
	
	@ApiOperation(value = "Retorna o Itinerário com o ID Linha informado existente na base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna o Itinerário por ID Linha da base de dados"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 404, message = "Não foi localizado Itinerário com os dados informados"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> getItinerarioByIdLinha(@PathVariable(name = "id") String id){
		
		Itinerario itinerario = service.findByIdLinha(id);
		
		return new ResponseEntity<>(itinerario, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna todos os Itinerários existentes na base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna todos os Itinerários da base de dados"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping
	public ResponseEntity<?> getAll(){
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Retorna o Itinerário com o Código Linha informado existente na base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna o Itinerário por Código Linha da base de dados"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 404, message = "Não foi localizado Itinerário com os dados informados"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<?> getItinerarioByCodigo(@PathVariable(name = "codigo") String codigo){
		
		Itinerario itinerario = service.findByCodigo(codigo);
		
		return new ResponseEntity<>(itinerario, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Salva e retorna o Itinerário na base de dados. Caso já exista, esse Itinerário será atualizado")
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "Itinerário salvo/atualizado com sucesso!"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> save(@RequestBody String jsonObj){
		
		Itinerario itinerario = mainComponent.definirPontos(jsonObj);
		
		return new ResponseEntity<>(service.save(itinerario), HttpStatus.CREATED);
	}
	
	
	@ApiOperation(value = "Deleta o Itinerário informado da base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Itinerário deletado com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@DeleteMapping(consumes = "application/json")
	public ResponseEntity<?> deleteItinerario(@RequestBody @Valid Itinerario itinerario){
		service.delete(itinerario);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Deleta o Itinerário por ID Linha informado da base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Itinerário deletado com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItinerarioByLinha(@PathVariable(name = "id") String id){
		
		service.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Deleta o Itinerário por Código Linha informado da base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Itinerário deletado com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@DeleteMapping("/codigo/{codigo}")
	public ResponseEntity<?> deleteItinerarioByCodigo(@PathVariable(name = "codigo") String codigo){
		
		service.deleteByCodigo(codigo);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Deleta todos os Itinerários da base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Itinerários deletados com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@DeleteMapping("/all")
	public ResponseEntity<?> deleteAll(){
		
		service.deleteAll();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
