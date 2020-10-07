package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController
{
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public List<TopicoDto> lista(String nomeCurso)
	{
		List<Topico> topicos = null;
		
		if (nomeCurso != null)
		{
			topicos = topicoRepository.findByCurso_Nome(nomeCurso);
		}
		else
		{
			topicos = topicoRepository.findAll();
		}
		
		return TopicoDto.converter(topicos);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder)
	{
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri(); //entre chaves é parametro dinamico
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	
	@GetMapping("/{id}")// esse nao vem como interrogação, que o spring pega de forma automática
	public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) // anotação para pegar da url sem a interrogação
	{
		Optional<Topico> topicoOptional = topicoRepository.findById(id);
		
		if (topicoOptional.isPresent())
		{
			return ResponseEntity.ok(new DetalhesDoTopicoDto(topicoOptional.get())); 
		}
		else
		{
			return ResponseEntity.notFound().build();  //tratamento para caso não encontre o id
		}
	}
	
	@PutMapping("/{id}")
	@Transactional // se tiver isso, o spring comita a trasancao no final do metodo e da update em dado de base se tiver
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form)
	{
		Optional<Topico> topicoOptional = topicoRepository.findById(id);
		
		if (topicoOptional.isPresent())
		{
			Topico topico = form.atualizar(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDto(topico));
		}
		else
		{
			return ResponseEntity.notFound().build();  //tratamento para caso não encontre o id
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id)
	{
		Optional<Topico> topicoOptional = topicoRepository.findById(id);
		
		if (topicoOptional.isPresent())
		{
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build(); 
		}
		else
		{
			return ResponseEntity.notFound().build();  //tratamento para caso não encontre o id
		}
	}
}
