package com.projeto.FillipeAlbuquerque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.FillipeAlbuquerque.repository.ContatoRepository;
import com.projeto.FillipeAlbuquerque.model.Contato;

@Controller

public class ContatoController {
	@Autowired
	ContatoRepository contatoRepository;
	
	@GetMapping("/")
	public ModelAndView contato() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("contato", new Contato());
		mv.setViewName("contato");
		return mv;
	}//fim get login
	
	@PostMapping("/salvarContato")
	public ModelAndView contato(Contato user)throws Exception{
		ModelAndView mv=new ModelAndView();
		contatoRepository.save(user);
		mv.addObject("cadastroSucesso", true);
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@GetMapping("/listarContatos")
	public ModelAndView listarContatos() {
	    ModelAndView mv = new ModelAndView();
	    mv.addObject("contatos", contatoRepository.findAll());
	    mv.setViewName("listarContatos");
	    return mv;
	}

	
	@GetMapping("/listarExcluirContatos")
	public ModelAndView listarExcluirContatos() {
	    ModelAndView mv = new ModelAndView();
	    mv.addObject("contatos", contatoRepository.findAll());
	    mv.setViewName("listarExcluirContatos");
	    return mv;
	}

	@GetMapping("/excluirContato/{id}")
	public String excluirContato(@PathVariable("id") Long id) {
	    contatoRepository.deleteById(id);
	    return "redirect:/listarExcluirContatos";
	}

	@GetMapping("/atualizarContatos")
	public ModelAndView atualizarContatos() {
	    ModelAndView mv = new ModelAndView();
	    mv.addObject("contatos", contatoRepository.findAll());
	    mv.setViewName("atualizarContatos");
	    return mv;
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarContato(@PathVariable("id") Long id) {
	    ModelAndView mv = new ModelAndView();
	    Contato contato = contatoRepository.findById(id).orElse(null);
	    if (contato != null) {
	        mv.addObject("contato", contato);
	        mv.setViewName("alterar"); // Certifique-se de que "alterar" corresponde ao nome do seu arquivo HTML
	    } else {
	        mv.setViewName("redirect:/listarContatos");
	    }
	    return mv;
	}
	
	@PostMapping("/alterar")
	public String salvarAlteracaoContato(@ModelAttribute("contato") Contato contato) {
	    contatoRepository.save(contato); // Salva as alterações no banco de dados
	    return "redirect:/listarContatos"; // Redireciona para a listagem de contatos após a alteração
	}



		
	
	}

