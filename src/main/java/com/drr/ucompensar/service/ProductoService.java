package com.drr.ucompensar.service;

import java.util.List;

import com.drr.ucompensar.dto.ProductoDTO;

public interface ProductoService {
	
	Boolean crearProducto(ProductoDTO productoDTO);
	
	Boolean crearProductos(List<ProductoDTO> lstProductoDTO);

}
