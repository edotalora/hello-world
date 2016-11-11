package com.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.entities.Producto;

@Stateless
public class ProductServiceBean implements ProductServiceLocal,ProductServiceRemote{
	
	@PersistenceContext (unitName = "PU_CATALOGO")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> getProductos() {
		final Query query = em.createQuery("SELECT prod FROM Producto prod");
		final List<Producto> productos = query.getResultList();
		
		return productos; //TODO devolver las copias.
	}

	@Override
	public void addProducto(Producto producto) {
		final Producto prod= new Producto();
		prod.setName(producto.getName());
		prod.setPrice(producto.getPrice());
		
		em.persist(prod);
	}

	@Override
	public void removeProducto(Long idProducto) {
		final Producto producto = em.find(Producto.class, idProducto);
		em.remove(producto);
	}

	@Override
	public Producto getProducto(Long idProducto) {
		if(idProducto == null){
			return null;
		}
		final Producto producto = em.find(Producto.class, idProducto);
		return producto;//TODO retornar copia del producto.
	}

	@Override
	public void editarProducto(Producto producto) {
		final Producto prod = em.find(Producto.class, producto.getId());
		
		prod.setName(producto.getName());
		prod.setPrice(producto.getPrice());
		
		em.merge(prod);
	}

}
