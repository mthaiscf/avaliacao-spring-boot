package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;


@Controller
@RequestMapping(value="/estudantes")
public class EstudanteRestController {

	@Autowired
	private EstudanteRepository estudanteRepository;
	
	@GetMapping(value="/{id}",produces ="application/json")
	public ResponseEntity<Estudante> findById(@PathVariable(value = "id")Long id) {
		Optional<Estudante> estudante=  estudanteRepository.findById(id);		
		return  new ResponseEntity<Estudante>(estudante.get(), HttpStatus.OK);		
	}
	
	@GetMapping(value="/",produces = "application/json")
	public ResponseEntity<List<Estudante>>findAll(){
		List<Estudante>list = (List<Estudante>) estudanteRepository.findAll();
		return new ResponseEntity<List<Estudante>>(list, HttpStatus.OK);		
	}
	
	@PostMapping(value="/",produces = "application/json")
	public ResponseEntity<Estudante>save(@RequestBody Estudante estudante){
		
		Estudante estudanteSalvo = estudanteRepository.save(estudante);
		
		return new ResponseEntity<Estudante>(estudanteSalvo,HttpStatus.OK);	
	}
	
	@PutMapping(value="/",produces = "application/json")
	public ResponseEntity<Estudante>update(@RequestBody Estudante estudante){
		
		Estudante estudanteAlterado = estudanteRepository.save(estudante);
		
		return new ResponseEntity<Estudante>(estudanteAlterado, HttpStatus.OK);	
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {	 
	   estudanteRepository.deleteById(id);
	}
}
