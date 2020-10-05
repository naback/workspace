package br.com.alura.forum.controller.form;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;

public class TopicoForm
{
	private final String titulo;
	private final String mensagem;
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
