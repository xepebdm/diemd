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
	
	
	
	@ApiOperation(value = "Retorna o Itiner�rio com o ID Linha informado existente na base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna o Itiner�rio por ID Linha da base de dados"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 404, message = "N�o foi localizado Itiner�rio com os dados informados"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> getItinerarioByIdLinha(@PathVariable(name = "id") String id){
		
		Itinerario itinerario = service.findByIdLinha(id);
		
		return new ResponseEntity<>(itinerario, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna todos os Itiner�rios existentes na base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna todos os Itiner�rios da base de dados"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@GetMapping
	public ResponseEntity<?> getAll(){
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Retorna o Itiner�rio com o C�digo Linha informado existente na base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna o Itiner�rio por C�digo Linha da base de dados"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 404, message = "N�o foi localizado Itiner�rio com os dados informados"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<?> getItinerarioByCodigo(@PathVariable(name = "codigo") String codigo){
		
		Itinerario itinerario = service.findByCodigo(codigo);
		
		return new ResponseEntity<>(itinerario, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Salva e retorna o Itiner�rio na base de dados. Caso j� exista, esse Itiner�rio ser� atualizado")
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "Itiner�rio salvo/atualizado com sucesso!"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> save(@RequestBody String jsonObj){
		
		Itinerario itinerario = mainComponent.definirPontos(jsonObj);
		
		return new ResponseEntity<>(service.save(itinerario), HttpStatus.CREATED);
	}
	
	
	@ApiOperation(value = "Deleta o Itiner�rio informado da base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Itiner�rio deletado com sucesso"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@DeleteMapping(consumes = "application/json")
	public ResponseEntity<?> deleteItinerario(@RequestBody @Valid Itinerario itinerario){
		service.delete(itinerario);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Deleta o Itiner�rio por ID Linha informado da base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Itiner�rio deletado com sucesso"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItinerarioByLinha(@PathVariable(name = "id") String id){
		
		service.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Deleta o Itiner�rio por C�digo Linha informado da base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Itiner�rio deletado com sucesso"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@DeleteMapping("/codigo/{codigo}")
	public ResponseEntity<?> deleteItinerarioByCodigo(@PathVariable(name = "codigo") String codigo){
		
		service.deleteByCodigo(codigo);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Deleta todos os Itiner�rios da base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Itiner�rios deletados com sucesso"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@DeleteMapping("/all")
	public ResponseEntity<?> deleteAll(){
		
		service.deleteAll();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
