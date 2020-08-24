package br.com.diem.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.diem.exception.OnibusNotFoundException;
import br.com.diem.model.Onibus;
import br.com.diem.repository.OnibusRepository;

@SpringBootTest(classes = {OnibusService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class OnibusServiceTest {
	
	@Autowired
	private OnibusService service;
	
	@MockBean
	private OnibusRepository repository;
	
	@Test
	public void testSaveWithSuccess() {
		Onibus bus1 = new Onibus();
		bus1.setId("5529");
		bus1.setCodigo("250-1");
		bus1.setNome("1 DE MAIO");
		
		when(repository.save(bus1)).thenReturn(bus1);
		
		Onibus onibusReturn = service.save(bus1);
		
		assertEquals("1 DE MAIO", onibusReturn.getNome());
	}
	
	
	@Test
	public void testSaveAndUpdate() {
		Onibus bus = new Onibus();
		bus.setId("5485");
		bus.setCodigo("T11-1");
		bus.setNome("3ª PERIMETRAL");
		
		when(repository.save(bus)).thenReturn(bus);
		
		service.save(bus);
		
		bus.setNome("3ª PERIMETRAL II");
		
		Onibus onibusReturn = service.save(bus);
		
		assertEquals("3ª PERIMETRAL II", onibusReturn.getNome());
	}
	
	@Test
	public void testGetOnibusById() {
		Onibus bus = new Onibus();
		bus.setId("5037");
		bus.setCodigo("214-1");
		bus.setNome("5ª UNIDADE/ESCOLAR");
		
		when(repository.findById("5037")).thenReturn(Optional.of(bus));
		
		Onibus onibusReturn = service.findById("5037");
		
		assertEquals("214-1", onibusReturn.getCodigo());
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetOnibusByIdWithEmptyId() {
		service.findById("");
	}
	
	@Test(expected = OnibusNotFoundException.class)
	public void testGetOnibusByIdWithInvalidId() {
		when(repository.findById("9999999")).thenThrow(OnibusNotFoundException.class);
		
		service.findById("9999999");
	}
	
	@Test
	public void testGetOnibusByCodigo() {
		Onibus bus = new Onibus();
		bus.setId("5037");
		bus.setCodigo("214-1");
		bus.setNome("5ª UNIDADE/ESCOLAR");
		
		when(repository.findByCodigo("214-1")).thenReturn(Optional.of(bus));
		
		Onibus onibusReturn = service.findByCodigo("214-1");
		
		assertEquals("5ª UNIDADE/ESCOLAR", onibusReturn.getNome());
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetOnibusByCodigoWithEmptyCodigo() {
		service.findByCodigo("");
	}
	
	@Test(expected = OnibusNotFoundException.class)
	public void testGetOnibusByCodigoWithInvalidCodigo() {
		when(repository.findByCodigo("9999999")).thenThrow(OnibusNotFoundException.class);
		
		service.findByCodigo("9999999");
	}
	
	@Test
	public void testDeleteOnibus() {
		Onibus bus = new Onibus();
		bus.setId("5039");
		bus.setCodigo("2141-1");
		bus.setNome("5ª UNIDADE/ESCOLAR II");
		
		doNothing().when(repository).delete(bus);
		
		service.delete(bus);
	}
	
	@Test
	public void testDeleteOnibusById() {
		doNothing().when(repository).deleteById("99999");
		
		service.deleteById("99999");
	}
	
	@Test
	public void testDeleteOnibusByCodigo() {
		doNothing().when(repository).deleteByCodigo("99999");
		
		service.deleteByCodigo("99999");
	}
	
	
	@Test
	public void testDeleteAll() {
		doNothing().when(repository).deleteAll();
		
		service.deleteAll();
	}
	
}
