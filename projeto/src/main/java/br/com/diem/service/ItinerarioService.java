package br.com.diem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diem.exception.ItinerarioNotFoundException;
import br.com.diem.model.Itinerario;
import br.com.diem.repository.ItinerarioRepository;

@Service
public class ItinerarioService {

	@Autowired
	private ItinerarioRepository repository;

	public Itinerario save(Itinerario itinerario) {
		return repository.save(itinerario);
	}

	public List<Itinerario> getAll() {
		return repository.findAll();
	}

	public Itinerario findByIdLinha(String idLinha) {

		if (idLinha.isEmpty()) {
			throw new IllegalArgumentException("ID é obrigatório!");
		}

		try {
		
			Itinerario itinerario = repository.findById(idLinha).get();
			return itinerario;
		
		} catch (Exception e) {
			throw new ItinerarioNotFoundException();
		}

	}

	public Itinerario findByCodigo(String codigo) {

		if (codigo.isEmpty()) {
			throw new IllegalArgumentException("Código é obrigatório!");
		}

		try {
			
			Itinerario itinerario = repository.findByCodigo(codigo).get();
			return itinerario;

		} catch (Exception e) {
			throw new ItinerarioNotFoundException();
		}

	}

	public void delete(Itinerario itinerario) {
		repository.delete(itinerario);
	}

	public void deleteById(String id) {
		repository.deleteById(id);
	}

	public void deleteByCodigo(String codigo) {
		repository.deleteByCodigo(codigo);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

}
