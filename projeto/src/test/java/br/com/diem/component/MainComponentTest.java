package br.com.diem.component;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.diem.model.Itinerario;
import br.com.diem.model.Onibus;

@SpringBootTest(classes = {MainComponent.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MainComponentTest {
	
	@Autowired
	private MainComponent mainComponent;
	
	private List<Onibus> lista = new ArrayList<>();
	
	
	@Before
	public void setUp() {
		
		Onibus bus1 = new Onibus();
		bus1.setId("5529");
		bus1.setCodigo("250-1");
		bus1.setNome("1 DE MAIO");
		
		Onibus bus2 = new Onibus();
		bus2.setId("5530");
		bus2.setCodigo("250-2");
		bus2.setNome("1 DE MAIO");
		
		Onibus bus3 = new Onibus();
		bus3.setId("5485");
		bus3.setCodigo("T11-1");
		bus3.setNome("3ª PERIMETRAL");
		
		Onibus bus4 = new Onibus();
		bus4.setId("5486");
		bus4.setCodigo("T11-2");
		bus4.setNome("3ª PERIMETRAL");
		
		Onibus bus5 = new Onibus();
		bus5.setId("5037");
		bus5.setCodigo("214-1");
		bus5.setNome("5ª UNIDADE/ESCOLAR");
		
		Onibus bus6 = new Onibus();
		bus6.setId("5038");
		bus6.setCodigo("214-2");
		bus6.setNome("5ª UNIDADE/ESCOLAR");
		
		Onibus bus7 = new Onibus();
		bus7.setId("5039");
		bus7.setCodigo("2141-1");
		bus7.setNome("5ª UNIDADE/ESCOLAR II");
		
		Onibus bus8 = new Onibus();
		bus8.setId("5040");
		bus8.setCodigo("2141-2");
		bus8.setNome("5ª UNIDADE/ESCOLAR II");
		
		lista.add(bus1);
		lista.add(bus2);
		lista.add(bus3);
		lista.add(bus4);
		lista.add(bus5);
		lista.add(bus6);
		lista.add(bus7);
		lista.add(bus8);

	}
	
	@Test
	public void testfiltrarLinhasByNome_1DeMaio() {
		List<Onibus> linhas = mainComponent.filtrarLinhasByNome(lista, "1 de maio");
		
		assertEquals(2, linhas.size());
	}
	
	@Test
	public void testfiltrarLinhasByNome_5UnidadeEscolarII() {
		List<Onibus> linhas = mainComponent.filtrarLinhasByNome(lista, "5ª UNIDADE/ESCOLAR II");
		
		assertEquals(2, linhas.size());
	}
	
	@Test
	public void getPontosFromJson() {
		String jsonObj = "{\"0\":{\"lat\": \"-30.03091358393500000\", \"lng\": \"-51.22586811418300000\"}"
				+ ",\"1\": {\"lat\": \"-30.03084258393500000\",\"lng\": \"-51.22691411418400000\"}"
				+ ",\"idlinha\": \"5566\",\"nome\": \"VILA NOVA\",\"codigo\": \"266-1\"}";
		
		Itinerario itinerario = mainComponent.definirPontos(jsonObj);
		
		assertEquals(2, itinerario.getPontos().size());
	}
	

}
