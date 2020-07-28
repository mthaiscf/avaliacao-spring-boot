package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String iniciarCadastro(Estudante estudante) {
		return "cadastrar-estudante";
	}	
	
	@GetMapping("listar")
	public ModelAndView listar(ModelMap model) {
		List<Estudante> estudantes = estudanteService.buscarEstudantes();
        model.addAttribute("estudantes", estudantes);
        return new ModelAndView("/index", model);
	}
	
	
	@PostMapping("add")
	public String adicionarEstudante(@Valid Estudante estudante, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "cadastrar-estudante";
		}
		estudanteService.cadastrarEstudante(estudante);
		return "redirect:listar";
	}

	@GetMapping("editar/{id}")
	public String exibirEdicaoEstudante(long id, Model model) {
		Estudante estudante = estudanteService.buscarEstudante(id);
		model.addAttribute("estudante", estudante);
		return "atualizar-estudante";
	}

	@PostMapping("atualizar/{id}")
	public String atualizarEstudante(@PathVariable("id") long id, @Valid Estudante estudante, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "atualizar-estudante";
		}
		estudanteService.atualizarEstudante(estudante, id);
		model.addAttribute("estudantes", estudanteService.buscarEstudantes());
		return "index";
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
