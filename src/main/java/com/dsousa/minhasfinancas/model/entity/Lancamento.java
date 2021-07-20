package com.dsousa.minhasfinancas.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

import com.dsousa.minhasfinancas.model.enums.StatusLancamento;
import com.dsousa.minhasfinancas.model.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "lancamento", schema = "financas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "mes")
	private Integer mes;

	@Column(name = "ano")
	private Integer ano;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@Column(name = "valor")
	private BigDecimal valor;

	@Column(name = "data_cadastro")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataCadastro;

	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipo;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusLancamento status;
}
