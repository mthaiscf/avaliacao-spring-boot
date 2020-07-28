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
		return this.repository.findAll();	
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
		return this.repository.findById(id);
	}

	@Override
	public void atualizarEstudante(@Valid Estudante estudante, long id) {
		
		Estudante estudanteUpdate = repository.findById(id);
		
		if(estudanteUpdate != null) {
			
			estudanteUpdate.setNome(estudante.getNome());
			estudanteUpdate.setEmail(estudante.getEmail());
			estudanteUpdate.setTelefone(estudante.getTelefone());
			estudanteUpdate.setCurso(estudante.getCurso());
			
			repository.save(estudanteUpdate);
		}
	}
	
	@Override
	public void excluirEstudante(long id) {
		if(this.repository.findById(id) != null) {
			this.repository.deleteById(id);
		}
	}	
	
}
