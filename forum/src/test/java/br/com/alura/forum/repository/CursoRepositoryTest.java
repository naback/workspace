package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Curso;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest  // Própria para testes de repositories -> já provê controle de transação e outros...
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) Essa configuração deve ser inserida no caso de banco de dados real e não em memória(h2)
//@ActiveProfiles("test")  É uma boa prática separa em profile de teste para quando for testar o banco de teste... obs: vai carregar o application-test.properties ao inves do application.properties
public class CursoRepositoryTest
{
    @Autowired
    private CursoRepository repository;

    @Test
    public void deveriaCarregarUmCursoAoBuscarPeloNome()
    {
        String nomeCurso = "HTML 5";
        Curso curso = repository.findByNome(nomeCurso);

        Assertions.assertNotNull(curso);
        Assertions.assertEquals(nomeCurso, curso.getNome());
    }

    @Test
    public void naoDeveriaCarregarUmCursoAoBuscarPeloNomeQueNaoExiste()
    {
        String nomeCurso = "JPA";
        Curso curso = repository.findByNome(nomeCurso);

        Assertions.assertNull(curso);
    }
}
