package br.com.diem.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.diem.component.MainComponent;
import br.com.diem.model.Itinerario;
import br.com.diem.model.Onibus;

@SpringBootTest(classes = {MainService.class, MainComponent.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MainServiceTest {
	
	@Autowired
	private MainService service;
	
	@Test
	public void testGetListaDeOnibus() {
		List<Onibus> lista = service.getListaDeOnibus();
		
		assertFalse(lista.isEmpty());
	}
	
	@Test
	public void testeGetItinerarioByOnibusId_5566() {
		Itinerario itinerario = service.getItinerarioByOnibusId("5566");
		
		assertEquals("VILA NOVA", itinerario.getNome());
	}
	
	@Test
	public void testeGetItinerarioByOnibusId_5485() {
		Itinerario itinerario = service.getItinerarioByOnibusId("5485");
		
		assertEquals("3ª PERIMETRAL", itinerario.getNome());
	}
	
	@Test
	public void testeGetLinhasDeOnibusByNome_AlimentadoraRestingaVelha() {
		List<Onibus> lista = service.getListaDeOnibusByNome("ALIMENTADORA RESTINGA VELHA");
		
		assertEquals(1, lista.size());
	}
	
//	@Test
//	public void testeFiltroLinhasPorKm() throws InterruptedException {
//		Map<String, List<Ponto>> linhasPorDistancia = service.getLinhasPorDistancia("-30.03091358393500000", "-51.22586811418300000", "10");
//		
//	}
}
