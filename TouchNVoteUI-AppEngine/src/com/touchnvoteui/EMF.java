package com.touchnvoteui;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMF {
	
	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("transactions-optional");

	private EMF() {
	}

	public static EntityManagerFactory get() {
		return emfInstance;
	}
	
	/*
	private EntityManagerFactory emfInstance;

    private static EMF emf;

    private EMF() {
    }

    public EntityManagerFactory get() {
        if(emfInstance == null) {
            emfInstance = Persistence.createEntityManagerFactory("transactions-optional");
        }
        return emfInstance;
    }

    public static EMF getInstance() {
        if(emf == null) {
            emf = new EMF();
        }
        return emf;
    }
    */
}
