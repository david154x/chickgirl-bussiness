package com.drr.ucompensar.service.impl;

import java.math.RoundingMode;
import java.util.ArrayList;
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

	@Override
	public List<ProductoDTO> consultarTodos() {
		try {
			
			List<ProductoEntity> lstProductoEntity = this.productoRepository.findAll();
			
			List<ProductoDTO> lstProductosEncontrados = new ArrayList<>();
			
			if ( lstProductoEntity != null && !lstProductoEntity.isEmpty() ) {
				
				lstProductoEntity.stream().forEach(x -> {
					
					lstProductosEncontrados.add(ProductoDTO.builder()
							.codigoProducto(x.getId())
							.nombreProducto(x.getNombre())
							.categoria(x.getCategoria())
							.precio(x.getPrecio().setScale(0, RoundingMode.HALF_UP))
							.build());
					
				});
				
			}
			
			if ( lstProductosEncontrados != null && !lstProductosEncontrados.isEmpty() )
				return lstProductosEncontrados;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductoDTO consultarXId(Long idProducto) {
		try {
			
			ProductoEntity productoEncontrado = this.productoRepository.findById(idProducto);
			
			if ( productoEncontrado != null && !Objects.isNull(productoEncontrado) ) {
				
				return ProductoDTO.builder()
						.codigoProducto(productoEncontrado.getId())
						.nombreProducto(productoEncontrado.getNombre())
						.categoria(productoEncontrado.getCategoria())
						.precio(productoEncontrado.getPrecio().setScale(0, RoundingMode.HALF_UP))
						.build();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
