package br.com.diem.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.diem.model.Onibus;

@Repository
public interface OnibusRepository extends MongoRepository<Onibus, String>{

	Optional<Onibus> findByCodigo(String codigo);

	void deleteByCodigo(String codigo);

}
