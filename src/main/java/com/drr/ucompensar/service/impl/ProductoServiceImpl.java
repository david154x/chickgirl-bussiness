package com.drr.ucompensar.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import com.drr.ucompensar.dto.ProductoDTO;
import com.drr.ucompensar.entity.ProductoEntity;
import com.drr.ucompensar.repository.ProductoRepository;
import com.drr.ucompensar.service.ProductoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductoServiceImpl implements ProductoService {
	
	@Inject
	private ProductoRepository productoRepository;

	@Override
	public Boolean crearProducto(ProductoDTO productoDTO) {
		try {
			
			ProductoEntity productoCreado = this.productoRepository.create(
					ProductoEntity.builder()
					.nombre(productoDTO.getNombreProducto())
					.categoria(productoDTO.getCategoria())
					.precio(productoDTO.getPrecio())
					.build());
			
			if ( productoCreado != null && !Objects.isNull(productoCreado) )
				return Boolean.TRUE;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean crearProductos(List<ProductoDTO> lstProductoDTO) {
		try {
			
			Integer cantidadRecibida = lstProductoDTO.size();
			AtomicInteger cantidadGuardada = new AtomicInteger(0);
			
			lstProductoDTO.stream().forEach(x -> {
				
				ProductoEntity productoCreado = this.productoRepository.create(
						ProductoEntity.builder()
						.nombre(x.getNombreProducto())
						.categoria(x.getCategoria())
						.precio(x.getPrecio())
						.build());
				
				if ( productoCreado != null && !Objects.isNull(productoCreado) )
					cantidadGuardada.getAndIncrement();
				
			});
			
			if ( Integer.valueOf(cantidadGuardada.get()).equals(cantidadRecibida) )
				return Boolean.TRUE;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}

}
