package com.drr.ucompensar.service.impl;

import java.util.Objects;

import com.drr.ucompensar.entity.InventarioEntity;
import com.drr.ucompensar.repository.InventarioRepository;
import com.drr.ucompensar.service.InventarioService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class InventarioServiceImpl implements InventarioService {
	
	@Inject
	private InventarioRepository inventarioRepository;
	
	@Override
	public String descontarInventario(Long idProducto, Integer cantidad) {
		try {
			StringBuilder mensajeFinal = new StringBuilder();
			
			InventarioEntity inventarioDelProducto = this.inventarioRepository.inventarioXProducto(idProducto);
			
			if ( inventarioDelProducto == null || Objects.isNull(inventarioDelProducto) )
				throw new Exception("No se encontro el producto para descontar inventario ");
			
			mensajeFinal.append("El producto " + inventarioDelProducto.getProducto().getNombre()+": ")
						.append("tiene "+inventarioDelProducto.getCantidad()+" unidades disponibles ")
						.append("a este producto se restaran "+cantidad+" solicitadas. ");
			
			inventarioDelProducto.setCantidad(inventarioDelProducto.getCantidad() - cantidad);
			
			this.inventarioRepository.update(inventarioDelProducto);
			
			return mensajeFinal.append("El producto finalmente quedo con "+inventarioDelProducto.getCantidad()+" unidades en el inventario.")
					.toString(); 
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
