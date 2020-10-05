package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>
{
	// nomenclatura Spring onde ele gera a query automaticamente
	List<Topico> findByCurso_Nome(String nomeCurso); // Curso é o atributo de relacionamento e nome o parametro dele ex: Curso.nome  Sem o underline tbm funciona, o underline 
													// serve para tirar ambiguidade pra caso na Entidade topico exista um parametro chamado cursoNome

	// manuals
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
	List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso);
}
