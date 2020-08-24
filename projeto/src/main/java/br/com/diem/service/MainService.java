package br.com.diem.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.diem.component.MainComponent;
import br.com.diem.model.Itinerario;
import br.com.diem.model.Onibus;
import br.com.diem.model.Ponto;

@Service
public class MainService {
	
	public MainService() {
		this.entity = this.configuracoes(); 
	}
	
	private final String URL_BASE = "http://www.poatransporte.com.br/php/facades/process.php?";
	
	private HttpEntity<String> entity = null;
	
	@Autowired
	private MainComponent mainComponent;
	
	
	/**
	 * Realiza a consulta na API Data POA e busca todas as linhas de ônibus disponiveis
	 * 
	 * @return listaDeLinhas
	 */
	public List<Onibus> getListaDeOnibus(){
		RestTemplate restTemplate = new RestTemplate();
		
		String URL_LINHAS_ONIBUS = "a=nc&p=%&t=o";
		
		setConverter(restTemplate);
		
		ResponseEntity<List<Onibus>> responseEntity = restTemplate.exchange(this.URL_BASE + URL_LINHAS_ONIBUS, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Onibus>>(){});
		
		return responseEntity.getBody();
	}

	/**
	 * Realiza a consulta na API Data POA e busca o Itinerário da Linha informado por ID
	 * 
	 * @return Itinerario
	 */
	public Itinerario getItinerarioByOnibusId(String idLinha) {
		RestTemplate restTemplate = new RestTemplate();
		
		String URL_ITINERARIO_E_ID_ONIBUS = "a=il&p=" + idLinha;
		
		ResponseEntity<String> response = restTemplate.exchange(this.URL_BASE + URL_ITINERARIO_E_ID_ONIBUS, HttpMethod.GET, null, String.class);
		
		Itinerario itinerario = mainComponent.definirPontos(response.getBody());
		
		return itinerario;
	}
	

	public List<Onibus> getListaDeOnibusByNome(String nome) {
		List<Onibus> lista = this.getListaDeOnibus();
		
		lista = mainComponent.filtrarLinhasByNome(lista, nome);
		
		return lista;
	}
	
	public Map<String, List<Ponto>> getLinhasPorDistancia(String latitude, String longitude, String distancia) {
		Map<String, List<Ponto>> valores = new HashMap<>();
		
		List<Itinerario> itinerarios = new ArrayList<>();
		
		for(Onibus bus: this.getListaDeOnibus()) {
			itinerarios.add(this.getItinerarioByOnibusId(bus.getId()));

			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		valores = mainComponent.filtroLinhasPorCoordenadas(itinerarios, latitude, longitude, distancia);
		
		return valores;
	}
	
	private void setConverter(RestTemplate restTemplate) {
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));        
		messageConverters.add(converter);
		
		restTemplate.setMessageConverters(messageConverters);
	}
	
	private HttpEntity<String> configuracoes() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.USER_AGENT, "");
		headers.setAccept(Arrays.asList(MediaType.ALL));
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		return entity;
	}
	
}
