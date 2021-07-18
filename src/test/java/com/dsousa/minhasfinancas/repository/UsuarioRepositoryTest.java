package com.dsousa.minhasfinancas.repository;

import java.util.Optional;

import com.dsousa.minhasfinancas.model.entity.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@Autowired
	TestEntityManager entityManager;

	private Usuario usuario;

	@Before
	public void setUp() {
		this.usuario = Usuario.builder()
			.nome("usuario")
			.email("usuario@email.com")
			.build();
	}

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		entityManager.persist(this.usuario);
		boolean result = repository.existsByEmail("usuario@email.com");
		Assertions.assertThat(result).isTrue();
	}

	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {
		boolean result = repository.existsByEmail("usuario@email.com");
		Assertions.assertThat(result).isFalse();
	}

	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		Usuario usuarioSalvo = repository.save(this.usuario);

		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}

	@Test
	public void devePesquisarUmUsuarioPorEmail() {
		entityManager.persist(this.usuario);
		Optional<Usuario> usuario = repository.findByEmail("usuario@email.com");
		Assertions.assertThat(usuario.isPresent()).isTrue();
	}

	@Test
	public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {
		Optional<Usuario> usuario = repository.findByEmail("usuario@email.com");
		Assertions.assertThat(usuario.isPresent()).isFalse();
	}
}
