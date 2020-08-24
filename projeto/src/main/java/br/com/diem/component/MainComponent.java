package br.com.diem.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.diem.model.Itinerario;
import br.com.diem.model.Onibus;
import br.com.diem.model.Ponto;

@Component
public class MainComponent {
	
	
	/**
	 * Filtra a lista de linhas por nome
	 * 
	 *@return listaDeLinhasFiltradasPorNome 
	 */
	public List<Onibus> filtrarLinhasByNome(List<Onibus> lista, String nome){
		List<Onibus> linhas = lista.stream()
				  .filter(o -> o.getNome().equalsIgnoreCase(nome))
				  .collect(Collectors.toList());
		
		return linhas;
	}
	
	/**
	 * Converte o objeto JSON String para objeto Itinerario.
	 * <p>Essa implementação foi necessário pois os valores de paradas do Itinerario são dinamicos
	 * 
	 * @param jsonObj
	 * @return Itinerario
	 */
	public Itinerario definirPontos(String jsonObj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(jsonObj);

			Itinerario itinerario = new Itinerario();
			itinerario.setIdlinha(node.get("idlinha").textValue());
			itinerario.setCodigo(node.get("codigo").textValue());
			itinerario.setNome(node.get("nome").textValue());

			List<Ponto> pts = new ArrayList<>();
			
			for(int i = 0; i <= node.size(); i++) {
				
				List<JsonNode> pontos = node.findValues(String.valueOf(i));

				for (JsonNode price : pontos) {
					Ponto ponto = new Ponto();
					ponto.setLat(price.get("lat").textValue());
					ponto.setLng(price.get("lng").textValue());
					pts.add(ponto);
				}
				
				
			}
			
			itinerario.setPontos(pts);
			
			return itinerario;
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * Realiza o calculo entre as coordenadas informadas e as coordenadas de outros Itinerários e filtra pela Distancia informada no parametro
	 * 
	 * @param itinerarios
	 * @param latitude
	 * @param longitude
	 * @param distancia
	 * @return linhasPorCoordernadas
	 */
	//TODO Revisar perfomance desse metodo
	public Map<String, List<Ponto>> filtroLinhasPorCoordenadas(List<Itinerario> itinerarios, String latitude, String longitude, String distancia){
		Map<String, List<Ponto>> valores = new HashMap<>();
		
		for( Itinerario iti : itinerarios) {
			
			List<Ponto> pontos = new ArrayList<>();
			
			for(Ponto ponto : iti.getPontos()) {
				double distPonto = distance(Double.valueOf(latitude), Double.valueOf(ponto.getLat()), Double.valueOf(longitude), Double.valueOf(ponto.getLng()), 0, 0);
				
				if(Double.valueOf(distancia) >= (distPonto / 1000)) {
					pontos.add(ponto);
				}
			}
			
			if(!pontos.isEmpty()) {
				valores.put(iti.getIdlinha(), pontos);
			}
		}
		
		return valores;
	}
	
	/**
	 * Calcule a distância entre dois pontos de latitude e longitude levando
	 * em consideração a diferença de altura.
	 * <p>Se você não está interessado em altura
	 * diferença passa de 0,0.
	 * <p>Utiliza o método Haversine como base.
	 * 
	 * 
	 * <p>lat1, lon1 ponto inicial
	 * <p>lat2, lon2 ponto final
	 * <p>el1 inicial altitude em metros
	 * <p>el2 final altitude em metros
	 * @return Distancia em Metros
	 */
	private double distance(double lat1, double lat2, double lon1,
	        double lon2, double el1, double el2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	}
	
	
}
