package com.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.entities.Producto;
import com.services.ProductServiceLocal;

@Stateless
@WebService(wsdlLocation="/META-INF/resources/adriarchivo.wsdl")
public class CatalogService {
@EJB	
ProductServiceLocal productsService;

@WebMethod
public List<Producto> productos(@WebParam(name="prodName") String pName){
	return productsService.getProductos();
}
}
