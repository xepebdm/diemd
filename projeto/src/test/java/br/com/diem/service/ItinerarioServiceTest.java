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

import br.com.diem.exception.ItinerarioNotFoundException;
import br.com.diem.model.Itinerario;
import br.com.diem.repository.ItinerarioRepository;

@SpringBootTest(classes = {ItinerarioService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ItinerarioServiceTest {

	
	@Autowired
	private ItinerarioService service;
	
	@MockBean
	private ItinerarioRepository repository;
	
	@Test
	public void testSaveWithSuccess() {
		Itinerario itinerario = new Itinerario();
		itinerario.setIdlinha("5529");
		itinerario.setCodigo("250-1");
		itinerario.setNome("1 DE MAIO");
		
		when(repository.save(itinerario)).thenReturn(itinerario);
		
		Itinerario resultReturn = service.save(itinerario);
		
		assertEquals("1 DE MAIO", resultReturn.getNome());
	}
	
	
	@Test
	public void testSaveAndUpdate() {
		Itinerario itinerario = new Itinerario();
		itinerario.setIdlinha("5485");
		itinerario.setCodigo("T11-1");
		itinerario.setNome("3ª PERIMETRAL");
		
		when(repository.save(itinerario)).thenReturn(itinerario);
		
		service.save(itinerario);
		
		itinerario.setNome("3ª PERIMETRAL II");
		
		Itinerario resultReturn = service.save(itinerario);
		
		assertEquals("3ª PERIMETRAL II", resultReturn.getNome());
	}
	
	@Test
	public void testGetItinerarioByIdLinha() {
		Itinerario itinerario = new Itinerario();
		itinerario.setIdlinha("5037");
		itinerario.setCodigo("214-1");
		itinerario.setNome("5ª UNIDADE/ESCOLAR");
		
		when(repository.findById("5037")).thenReturn(Optional.of(itinerario));
		
		Itinerario ItinerarioReturn = service.findByIdLinha("5037");
		
		assertEquals("214-1", ItinerarioReturn.getCodigo());
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetItinerarioByIdLinhaWithEmptyId() {
		service.findByIdLinha("");
	}
	
	@Test(expected = ItinerarioNotFoundException.class)
	public void testGetItinerarioByIdLinhaWithInvalidId() {
		when(repository.findById("9999999")).thenThrow(ItinerarioNotFoundException.class);
		
		service.findByIdLinha("9999999");
	}
	
	@Test
	public void testGetItinerarioByCodigo() {
		Itinerario itinerario = new Itinerario();
		itinerario.setIdlinha("5037");
		itinerario.setCodigo("214-1");
		itinerario.setNome("5ª UNIDADE/ESCOLAR");
		
		when(repository.findByCodigo("214-1")).thenReturn(Optional.of(itinerario));
		
		Itinerario ItinerarioReturn = service.findByCodigo("214-1");
		
		assertEquals("5ª UNIDADE/ESCOLAR", ItinerarioReturn.getNome());
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetItinerarioByCodigoWithEmptyCodigo() {
		service.findByCodigo("");
	}
	
	@Test(expected = ItinerarioNotFoundException.class)
	public void testGetItinerarioByCodigoWithInvalidCodigo() {
		when(repository.findByCodigo("9999999")).thenThrow(ItinerarioNotFoundException.class);
		
		service.findByCodigo("9999999");
	}
	
	
	@Test
	public void testDeleteItinerario() {
		Itinerario itinerario = new Itinerario();
		itinerario.setIdlinha("5039");
		itinerario.setCodigo("2141-1");
		itinerario.setNome("5ª UNIDADE/ESCOLAR II");
		
		doNothing().when(repository).delete(itinerario);
		
		service.delete(itinerario);
	}
	
	@Test
	public void testDeleteItinerarioByIdLinha() {
		doNothing().when(repository).deleteById("99999");
		
		service.deleteById("99999");
	}
	
	@Test
	public void testDeleteItinerarioByCodigo() {
		doNothing().when(repository).deleteByCodigo("99999");
		
		service.deleteByCodigo("99999");
	}
	
	@Test
	public void testDeleteAll() {
		doNothing().when(repository).deleteAll();
		
		service.deleteAll();
	}

}
