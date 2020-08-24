package br.com.diem.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.diem.model.TaxiModel;

@SpringBootTest(classes = {TaxiService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TaxiServiceTest {

	
	@Autowired
	private TaxiService service;
	
	
	@Test
	public void saveWithSuccess() {
		TaxiModel taxi = new TaxiModel();
		taxi.setNomeDoPonto("PONTO-DE-TESTE");
		taxi.setLatitude("-30.0103346");
		taxi.setLongitude("-51.1724526");
		
		service.save(taxi);
		
		assertTrue(service.getAll().contains("PONTO-DE-TESTE"));
	}
}
