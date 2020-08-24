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

import br.com.diem.model.Onibus;
import br.com.diem.service.OnibusService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {OnibusController.class})
@AutoConfigureMockMvc
@EnableWebMvc
public class OnibusControllerTest {
	
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private OnibusService service;
	
	@Test
	public void testGetOnibus() throws Exception {
		Onibus bus = new Onibus();
		bus.setId("1234");
		bus.setCodigo("123-4");
		bus.setNome("Linha de Teste");
		
		when(service.findById("1234")).thenReturn(bus);
		
		mock.perform(get("/linhas/{id}", "1234").param("id", "1234"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.nome").value("Linha de Teste"))
			.andReturn();
	}
	
	@Test
	public void testGetAll() throws Exception {
		when(service.getAll()).thenReturn(new ArrayList<>());
		
		mock.perform(get("/linhas"))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	public void testGetOnibusByCodigo() throws Exception {
		Onibus bus = new Onibus();
		bus.setId("1234");
		bus.setCodigo("123-4");
		bus.setNome("Linha de Teste");
		
		when(service.findByCodigo("123-4")).thenReturn(bus);
		
		mock.perform(get("/linhas/codigo/{codigo}", "123-4").param("codigo", "123-4"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.nome").value("Linha de Teste"))
			.andReturn();
	}
	
	@Test
	public void testSaveOnibus() throws Exception{
		Onibus bus = new Onibus();
		bus.setId("1234");
		bus.setCodigo("123-4");
		bus.setNome("Linha de Teste");
		
		when(service.save(bus)).thenReturn(bus);
		
		mock.perform(post("/linhas")
						.contentType("application/json")
						.content(objectToJson(bus)))
				.andExpect(status().isCreated())
				.andReturn();
	}
	
	@Test
	public void testSaveOnibusWithFail_WithoutCodigo() throws Exception{
		Onibus bus = new Onibus();
		bus.setId("1234");
		bus.setNome("Linha de Teste");
		
		when(service.save(bus)).thenReturn(bus);
		
		mock.perform(post("/linhas")
						.contentType("application/json")
						.content(objectToJson(bus)))
				.andExpect(status().is4xxClientError())
				.andReturn();
	}
	
	@Test
	public void testDeleteOnibus() throws Exception{
		Onibus bus = new Onibus();
		bus.setId("1234");
		bus.setCodigo("123-4");
		bus.setNome("Linha de Teste");
		
		doNothing().when(service).delete(bus);
		
		mock.perform(delete("/linhas")
						.contentType("application/json")
						.content(objectToJson(bus)))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void testDeleteOnibusById() throws Exception {
		doNothing().when(service).deleteById("1234");
		
		mock.perform(delete("/linhas/{id}", "1234").param("id", "1234"))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	public void testDeleteOnibusByCodigo() throws Exception {
		doNothing().when(service).deleteByCodigo("123-4");
		
		mock.perform(delete("/linhas/codigo/{codigo}", "123-4").param("codigo", "123-4"))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	public void testDeleteAll() throws Exception {
		doNothing().when(service).deleteAll();
		
		mock.perform(delete("/linhas/all"))
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
