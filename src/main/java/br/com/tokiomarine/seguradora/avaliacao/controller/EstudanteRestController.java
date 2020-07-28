package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.List;

import javax.validation.Valid;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteService;


@Controller
@RequestMapping(value="/estudantes")
public class EstudanteRestController {

	
		// TODO caso você não conheça THEMELEAF faça a implementação dos métodos em forma de RESTCONTROLLER (seguindo o padrão RESTFUL)
		@Autowired
		private EstudanteService estudanteService;

		// TODO IMPLEMENTAR A LISTAGEM DE ESTUDANTES (GET) - all
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<Estudante>> findAll() {
			List<Estudante> list = estudanteService.buscarEstudantes();
			return ResponseEntity.ok().body(list);
		}
		
		// (GET) - one by id
		@RequestMapping(value="/{id}", method=RequestMethod.GET)
		public ResponseEntity<Estudante> findById(@PathVariable Integer id) {
			Estudante e = estudanteService.buscarEstudante(id);
			return ResponseEntity.ok().body(e);
		}
		
		// TODO IMPLEMENTAR CADASTRO DE ESTUDANTES (POST)
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert(@Valid @RequestBody Estudante estudante){
			estudanteService.cadastrarEstudante(estudante);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estudante.getId()).toUri();			
			return ResponseEntity.created(uri).build();
		}
		
		// TODO IMPLEMENTAR ATUALIZACAO DE ESTUDANTES (PUT)
		@RequestMapping(value="/{id}",method=RequestMethod.PUT)
		public ResponseEntity<Void> update(@Valid  @RequestBody Estudante estudante, @PathVariable Integer id){
			estudanteService.atualizarEstudante(estudante, id);
			return ResponseEntity.noContent().build();
		}
		
		// TODO IMPLEMENTAR A EXCLUSÃO DE ESTUDANTES (DELETE)
		@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id){
			estudanteService.excluirEstudante(id);
			return ResponseEntity.noContent().build();
		}
	
}
