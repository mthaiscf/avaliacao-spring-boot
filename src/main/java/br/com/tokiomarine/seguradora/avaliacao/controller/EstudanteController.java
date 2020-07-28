package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;	

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteService;

@Controller
@RequestMapping("/estudantes/")
public class EstudanteController {

	@Autowired
	EstudanteService estudanteService;
	
	@GetMapping("criar")
	public ModelAndView iniciarCadastro(Estudante estudante, ModelMap model) {
		return new ModelAndView("/cadastrar-estudante", model);
	}	
	
	@GetMapping("listar")
	public ModelAndView listar(ModelMap model) {
		List<Estudante> estudantes = estudanteService.buscarEstudantes();
        model.addAttribute("estudantes", estudantes);
        return new ModelAndView("/index", model);
	}
	
	
	@PostMapping("add")
	public ModelAndView adicionarEstudante(@Valid Estudante estudante, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return new ModelAndView("/cadastrar-estudante", model);
		}
		estudanteService.cadastrarEstudante(estudante);
		List<Estudante> estudantes = estudanteService.buscarEstudantes();
        model.addAttribute("estudantes", estudantes);
        return new ModelAndView("/index", model);
	}

	@GetMapping("editar/{id}")
	public ModelAndView exibirEdicaoEstudante(@PathVariable("id") long id, ModelMap model) {
		Estudante estudante = estudanteService.buscarEstudante(id);
		model.addAttribute("estudante", estudante);
		return new ModelAndView("atualizar-estudante", model);
	}

	@PostMapping("atualizar/{id}")
	public ModelAndView atualizarEstudante(@PathVariable("id") long id, @Valid Estudante estudante, BindingResult result, ModelMap model) {	
		if (result.hasErrors()) {
			return new ModelAndView("atualizar-estudante", model);
		}
		estudanteService.atualizarEstudante(estudante, id);
		model.addAttribute("estudantes", estudanteService.buscarEstudantes());
		return new ModelAndView("/index", model);
	}

	@GetMapping("apagar/{id}")
	public ModelAndView apagarEstudante(@PathVariable("id") long id, ModelMap model) {		
		if (estudanteService.buscarEstudante(id) != null) {
			estudanteService.excluirEstudante(id);
		}
		List<Estudante> estudantes = estudanteService.buscarEstudantes();
        model.addAttribute("estudantes", estudantes);
        return new ModelAndView("/index", model);	
	}
	
	
}
