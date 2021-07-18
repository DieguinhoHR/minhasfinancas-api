package com.dsousa.minhasfinancas.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioAutenticarDTO {

	private String email;
	private String senha;
}
