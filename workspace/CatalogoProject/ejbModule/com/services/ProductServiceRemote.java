package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Producto;

@Remote
public interface ProductServiceRemote {

	public List<Producto> getProductos();

	public void addProducto(final Producto producto);

	public void removeProducto(final Long idProducto);

	public Producto getProducto(final Long idProducto);

	public void editarProducto(final Producto producto);

}
