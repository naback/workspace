package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;

public class TopicoForm
{
	@NotNull @NotEmpty @Length(min = 1, max = 50)
	private final String titulo;
	@NotNull @NotEmpty
	private final String mensagem;
	@NotNull @NotEmpty
	private final String nomeCurso;

	public TopicoForm()
	{
		super();
		this.titulo = null;
		this.mensagem = null;
		this.nomeCurso = null;
	}

	public TopicoForm(String titulo, String mensagem, String nomeCurso)
	{
		super();
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.nomeCurso = nomeCurso;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public String getMensagem()
	{
		return mensagem;
	}

	public String getNomeCurso()
	{
		return nomeCurso;
	}

	public Topico converter(CursoRepository cursoRepository)
	{
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}

}
