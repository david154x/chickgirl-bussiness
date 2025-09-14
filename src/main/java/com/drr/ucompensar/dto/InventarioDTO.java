package com.drr.ucompensar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDTO {
	
	private Integer cantidad;
	
	private ProductoDTO productoDTO;

}
