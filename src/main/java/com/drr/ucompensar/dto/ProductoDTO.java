package com.drr.ucompensar.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
	
	private String nombreProducto;
	
	private String categoria;
	
	private BigDecimal precio;

}
