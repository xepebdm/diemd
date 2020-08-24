package br.com.diem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.diem.model.Itinerario;

@Repository
public interface ItinerarioRepository extends MongoRepository<Itinerario, String>{

	List<Itinerario> findAllByNome(String nome);

	Optional<Itinerario> findByCodigo(String codigo);

	void deleteByCodigo(String codigo);

	void deleteAllByNome(String nome);

}
