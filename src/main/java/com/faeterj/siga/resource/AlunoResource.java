package com.faeterj.siga.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.faeterj.siga.model.Aluno;
import com.faeterj.siga.repository.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoResource
{
	@Autowired
	private AlunoRepository alunoRep;
	
	@GetMapping
	public List<Aluno> listar()
	{
		return alunoRep.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody Aluno aluno, HttpServletResponse response)
	{
		Aluno alunoSalvo = alunoRep.save(aluno);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
			path("/{id}").buildAndExpand(alunoSalvo.getId()).toUri();
		
		response.setHeader("Location",uri.toASCIIString());
	}
}
