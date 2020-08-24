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

import br.com.diem.model.Onibus;
import br.com.diem.service.OnibusService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/linhas")
public class OnibusController {

	@Autowired
	private OnibusService service;
	
	
	@ApiOperation(value = "Retorna a Linha com o ID informado existente na base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna a Linha com o ID da base de dados"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 404, message = "N�o foi localizada Linha com os dados informados"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> getOnibusByLinha(@PathVariable(name = "id") String id){
		
		Onibus bus = service.findById(id);
		
		return new ResponseEntity<>(bus, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna todas as Linhas existentes na base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna as Linhas da base de dados"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@GetMapping
	public ResponseEntity<?> getAll(){
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna a Linha com o C�digo informado existente na base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna a Linha com o C�digo da base de dados"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 404, message = "N�o foi localizada Linha com os dados informados"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<?> getOnibysByCodigo(@PathVariable(name = "codigo") String codigo){
		
		Onibus bus = service.findByCodigo(codigo);
		
		return new ResponseEntity<>(bus, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Salva e retorna a Linha na base de dados. Caso j� exista, essa Linha ser� atualizada")
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "Linha salva/atualizada com sucesso!"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> save(@RequestBody @Valid Onibus onibus){
		return new ResponseEntity<>(service.save(onibus), HttpStatus.CREATED);
	}
	
	
	@ApiOperation(value = "Deleta a Linha informada da base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Linha deletada com sucesso"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@DeleteMapping(consumes = "application/json")
	public ResponseEntity<?> deleteOnibus(@RequestBody @Valid Onibus onibus){
		service.delete(onibus);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Deleta a Linha pelo ID informado da base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Linha deletada com sucesso"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOnibusByLinha(@PathVariable(name = "id") String id){
		
		service.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Deleta a Linha pelo C�digo informado da base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Linha deletada com sucesso"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@DeleteMapping("/codigo/{codigo}")
	public ResponseEntity<?> deleteOnibusByCodigo(@PathVariable(name = "codigo") String codigo){
		
		service.deleteByCodigo(codigo);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Deleta todas as Linhas  da base de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Linhas deletadas com sucesso"),
	    @ApiResponse(code = 403, message = "Voc� n�o tem permiss�o para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exce��o"),
	})
	@DeleteMapping("/all")
	public ResponseEntity<?> deleteAll(){
		
		service.deleteAll();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
