package com.siriusif.service;

import java.util.List;

/**
 * This is interface bluepring for all other services in this application
 * 
 */
public interface GenericDao<E, K> {

	void add(E entity);
	
	void update(E entity);
	
	void remove(E entity);
	
	E find(K key);
    
	List<E> list();	
}
