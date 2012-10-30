package com.platzerworld.weihnachtsmarkt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.platzerworld.weihnachtsmarkt.model.Biergarten;

public enum Dao {
	INSTANCE;

	public List<Biergarten> listBiergarten() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Biergarten m");
		List<Biergarten> todos = q.getResultList();
		return todos;
	}

	public void add(String userId, String summery, String description, String url) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Biergarten biergarten = new Biergarten();
			em.persist(biergarten);
			em.close();
		}
	}

	public List<Biergarten> getBiergarten(String userId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Todo t where t.author = :userId");
		q.setParameter("userId", userId);
		List<Biergarten> biergarten = q.getResultList();
		return biergarten;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Biergarten biergarten = em.find(Biergarten.class, id);
			em.remove(biergarten);
		} finally {
			em.close();
		}
	}
} 