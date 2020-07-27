package br.com.tokiomarine.seguradora.avaliacao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;

@Repository
public interface EstudanteRepository extends CrudRepository	<Estudante, Long> {
	
	List<Estudante> findAll();

	Estudante findById(long id);

}
