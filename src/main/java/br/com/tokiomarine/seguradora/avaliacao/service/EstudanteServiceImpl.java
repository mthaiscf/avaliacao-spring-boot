package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;

		
@Service
public class EstudanteServiceImpl implements EstudanteService {

	@Autowired
	EstudanteRepository repository;

		
	@Override
	public List<Estudante> buscarEstudantes() {	
		List<Estudante> estudantes = this.repository.findAll();
		return estudantes;	
	}
	
	@Override
	public void cadastrarEstudante(@Valid Estudante estudante) {
		repository.save(estudante);
	}
	
	@Override
	public Estudante buscarEstudante(long id) {
		if (id == 0) {
			throw new IllegalArgumentException("Identificador inválido:" + id);
		}
		Estudante estudanteprocurado = this.repository.findById(id);
		return estudanteprocurado;
	}

	@Override
	public void atualizarEstudante(@Valid Estudante estudante) {
		repository.save(estudante);
	}
	
	@Override
	public void excluirEstudante(long id) {	
		this.repository.deleteById(id);
	}	
	
}
