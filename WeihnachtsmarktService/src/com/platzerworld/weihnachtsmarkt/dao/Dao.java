package com.platzerworld.weihnachtsmarkt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.platzerworld.weihnachtsmarkt.model.Weihnachtsmarkt;

public enum Dao {
	INSTANCE;

	public List<Weihnachtsmarkt> listBiergarten() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from weihnachtsmarkt m");
		List<Weihnachtsmarkt> todos = q.getResultList();
		return todos;
	}

	public void add(String userId, String summery, String description, String url) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Weihnachtsmarkt biergarten = new Weihnachtsmarkt();
			em.persist(biergarten);
			em.close();
		}
	}

	public List<Weihnachtsmarkt> getBiergarten(String userId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Todo t where t.author = :userId");
		q.setParameter("userId", userId);
		List<Weihnachtsmarkt> biergarten = q.getResultList();
		return biergarten;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Weihnachtsmarkt biergarten = em.find(Weihnachtsmarkt.class, id);
			em.remove(biergarten);
		} finally {
			em.close();
		}
	}
} 