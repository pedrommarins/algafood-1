package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	@GetMapping("/{cidadesId}")
	public Cidade buscar(@PathVariable Long cidadesId) {

		return cadastroCidadeService.buscarOuFalhar(cidadesId);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Cidade adicionar(@RequestBody Cidade cidade) {
		return cadastroCidadeService.salvar(cidade);

	}

	@PutMapping("/{cidadesId}")
	public Cidade atualizar(@PathVariable Long cidadesId, @RequestBody Cidade cidade) {

		Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadesId);
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		return cadastroCidadeService.salvar(cidadeAtual);

	}

	@DeleteMapping("/{cidadesId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadesId) {

		cadastroCidadeService.excluir(cidadesId);

	}

}
