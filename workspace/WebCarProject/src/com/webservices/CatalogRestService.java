package com.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.entities.Carro;
import com.entities.Producto;
import com.services.CarServiceLocal;
import com.services.ProductServiceLocal;
@Stateless
@Path("/catalogo")
public class CatalogRestService {
	@EJB
	ProductServiceLocal prodServices;
	
	@EJB
	CarServiceLocal carServices;
	
	@GET
	@Path("productos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producto> productos(){
		return prodServices.getProductos();
	}	
	
	@GET
	@Path("carros")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carro> carros(){
		return carServices.getcarros();
	}	
	
	@GET
	@Path("carro/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Carro getCarro(@PathParam(value="id") Long id){
		return carServices.getCarroById(id);
	}		
	
	@GET
	@Path("carupdate/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String create(@PathParam(value="id") Long id){
		return carServices.updateCarro(id);
	}
	
	@GET
	@Path("carro1/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Carro getCarro1(@PathParam(value="id") Long id){
		return carServices.getCarroById1(id);
	}
	
	@GET
	@Path("carupdate1/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String create1(@PathParam(value="id") Long id){
		return carServices.updateCarro1(id);
	}
}
