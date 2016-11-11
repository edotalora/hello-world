package com.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.entities.Carro;

@Stateless
public class CarServiceBean implements CarServiceLocal, CarServiceRemote {
	
	@PersistenceContext (unitName = "PU_CATALOGO")
	private EntityManager em;
	
	@PersistenceContext (unitName = "PU_CATALOGO1")
	private EntityManager em1;

	@SuppressWarnings("unchecked")
	@Override
	public List<Carro> getcarros() {
		final StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT car FROM Carro car ");
		 final List<Carro> cars= em.createQuery(jpql.toString()).getResultList();
		 
		 return cars;
	}

	@Override
	public Carro getCarroById(Long id) {
		final Carro car = em.find(Carro.class, id);
		return car;
	}
	
	@Override
	public String updateCarro(final Long id){
		final Carro car = em.find(Carro.class, id);
		Long cantidad=Long.valueOf(car.getQuantity());
		cantidad=cantidad-1;
		car.setQuantity(cantidad.toString());
		
		em.merge(car);
		
		return "succes";
	}

	@Override
	public void addCarro(String nombre, String ean, String year, String quantity) {
		final Carro car = new Carro();
		car.setEan(ean);
		car.setName(nombre);
		car.setQuantity(quantity);
		car.setYear(year);		
	}
	
	@Override
	public Carro getCarroById1(Long id) {
		final Carro car = em1.find(Carro.class, id);
		return car;
	}
	
	@Override
	public String updateCarro1(final Long id){
		final Carro car = em1.find(Carro.class, id);
		Long cantidad=Long.valueOf(car.getQuantity());
		cantidad=cantidad-1;
		car.setQuantity(cantidad.toString());
		
		em1.merge(car);
		
		return "succes";
	}

}
