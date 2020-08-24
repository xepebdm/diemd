package br.com.diem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diem.exception.OnibusNotFoundException;
import br.com.diem.model.Onibus;
import br.com.diem.repository.OnibusRepository;

@Service
public class OnibusService {

	@Autowired
	private OnibusRepository repository;

	public Onibus save(Onibus onibus) {
		return repository.save(onibus);
	}
	
	public List<Onibus> getAll(){
		return repository.findAll();
	}

	public Onibus findById(String id) {

		if (id.isEmpty()) {
			throw new IllegalArgumentException("ID é obrigatório!");
		}

		try {
			Onibus bus = repository.findById(id).get();
			return bus;
			
		} catch (Exception e) {
			throw new OnibusNotFoundException();
		}

	}

	public Onibus findByCodigo(String codigo) {
		if (codigo.isEmpty()) {
			throw new IllegalArgumentException("Código é obrigatório!");
		}

		try {
			Onibus bus = repository.findByCodigo(codigo).get();
			return bus;
			
		} catch (Exception e) {
			throw new OnibusNotFoundException();
		}
	}

	public void delete(Onibus onibus) {
		repository.delete(onibus);
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
