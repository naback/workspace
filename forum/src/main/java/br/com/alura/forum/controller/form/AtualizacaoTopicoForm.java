package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

public class AtualizacaoTopicoForm
{
	@NotNull
	@NotEmpty
	@Length(min = 1, max = 50)
	private String titulo;

	@NotNull
	@NotEmpty
	private String mensagem;

	public AtualizacaoTopicoForm()
	{
		this.titulo = null;
		this.mensagem = null;
	}

	public AtualizacaoTopicoForm(@NotNull @NotEmpty @Length(min = 1, max = 50) String titulo,
			@NotNull @NotEmpty String mensagem)
	{
		super();
		this.titulo = titulo;
		this.mensagem = mensagem;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	public String getMensagem()
	{
		return mensagem;
	}

	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

	public Topico atualizar(Long id, TopicoRepository topicoRepository)
	{
		Topico topico = topicoRepository.getOne(id);
		
		if (this.mensagem != null)
		{
			topico.setMensagem(this.mensagem);
		}
		
		if (this.titulo != null)
		{
			topico.setTitulo(this.titulo);
		}
		
		//jpa atualiza banco automaticamente
		
		return topico;
	}
}
