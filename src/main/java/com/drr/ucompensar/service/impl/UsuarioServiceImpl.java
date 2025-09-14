package com.drr.ucompensar.service.impl;

import java.util.Objects;

import com.drr.ucompensar.entity.UsuarioEntity;
import com.drr.ucompensar.repository.UsuarioRepository;
import com.drr.ucompensar.service.UsuarioService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

	@Inject
	private UsuarioRepository usuarioRepository;

	@Override
	public Boolean crearUsuario(String nombre) {
		try {
			
			UsuarioEntity nuevoUsuario = this.usuarioRepository.create(
					UsuarioEntity.builder()
					.nombre(nombre)
					.build());
			
			if ( nuevoUsuario != null && !Objects.isNull(nuevoUsuario) )
				return Boolean.TRUE;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}
	
}
