package com.services;

import java.util.List;

import javax.ejb.Local;

import com.entities.Producto;

@Local
public interface ProductServiceLocal {

	public List<Producto> getProductos();

	public void addProducto(final Producto producto);

	public void removeProducto(final Long idProducto);

	public Producto getProducto(final Long idProducto);

	public void editarProducto(final Producto producto);

}
