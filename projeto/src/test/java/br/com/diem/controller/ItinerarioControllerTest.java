package br.com.diem.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.diem.component.MainComponent;
import br.com.diem.model.Itinerario;
import br.com.diem.service.ItinerarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ItinerarioController.class, MainComponent.class})
@AutoConfigureMockMvc
@EnableWebMvc
public class ItinerarioControllerTest {

	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private ItinerarioService service;
	
	
	@Test
	public void testGetItinerario() throws Exception {
		Itinerario itinerario = new Itinerario();
		itinerario.setIdlinha("5529");
		itinerario.setCodigo("250-1");
		itinerario.setNome("1 DE MAIO");
		
		when(service.findByIdLinha("5529")).thenReturn(itinerario);
		
		mock.perform(get("/itinerarios/{id}", "5529").param("id", "5529"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.nome").value("1 DE MAIO"))
			.andReturn();
	}
	
	@Test
	public void testGetAll() throws Exception {
		when(service.getAll()).thenReturn(new ArrayList<>());
		
		mock.perform(get("/itinerarios"))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	public void testGetItinerarioByCodigo() throws Exception {
		Itinerario itinerario = new Itinerario();
		itinerario.setIdlinha("5529");
		itinerario.setCodigo("250-1");
		itinerario.setNome("1 DE MAIO");
		
		when(service.findByCodigo("250-1")).thenReturn(itinerario);
		
		mock.perform(get("/itinerarios/codigo/{codigo}", "123-4").param("codigo", "123-4"))
			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.nome").value("1 DE MAIO"))
			.andReturn();
	}
	
	@Test
	public void testSaveItinerario() throws Exception{
		Itinerario itinerario = new Itinerario();
		itinerario.setIdlinha("5529");
		itinerario.setCodigo("250-1");
		itinerario.setNome("1 DE MAIO");
		
		when(service.save(itinerario)).thenReturn(itinerario);
		
		mock.perform(post("/itinerarios")
						.contentType("application/json")
						.content(objectToJson(itinerario)))
				.andExpect(status().isCreated())
				.andReturn();
	}
	
//	@Test
//	public void testSaveItinerarioWithFail_WithoutCodigo() throws Exception{
//		Itinerario itinerario = new Itinerario();
//		itinerario.setIdlinha("5529");
//		itinerario.setNome("1 DE MAIO");
//		
//		when(service.save(itinerario)).thenReturn(itinerario);
//		
//		mock.perform(post("/itinerarios")
//						.contentType("application/json")
//						.content(objectToJson(itinerario)))
//				.andExpect(status().is4xxClientError())
//				.andReturn();
//	}
	
	@Test
	public void testDeleteItinerario() throws Exception{
		Itinerario itinerario = new Itinerario();
		itinerario.setIdlinha("5529");
		itinerario.setCodigo("250-1");
		itinerario.setNome("1 DE MAIO");
		
		doNothing().when(service).delete(itinerario);
		
		mock.perform(delete("/itinerarios")
						.contentType("application/json")
						.content(objectToJson(itinerario)))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void testDeleteItinerarioById() throws Exception {
		doNothing().when(service).deleteById("1234");
		
		mock.perform(delete("/itinerarios/{id}", "1234").param("id", "1234"))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	public void testDeleteItinerarioByCodigo() throws Exception {
		doNothing().when(service).deleteByCodigo("123-4");
		
		mock.perform(delete("/itinerarios/codigo/{codigo}", "123-4").param("codigo", "123-4"))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	public void testDeleteAll() throws Exception {
		doNothing().when(service).deleteAll();
		
		mock.perform(delete("/itinerarios/all"))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	
	
	private String objectToJson(Object obj){
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
