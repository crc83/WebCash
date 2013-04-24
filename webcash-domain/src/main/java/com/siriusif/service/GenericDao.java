package com.siriusif.service;

import java.io.Serializable;
import java.util.List;

/**
 * This is interface blueprint for all other services in this application
 * 
 */
public interface GenericDao<E, K> {

	/**
	 * Add new entity
	 * @param entity
	 * @return key of added entity 
	 */
	K add(E entity);
	
	/**
	 * Update existing entity
	 * @param entity
	 */
	void update(E entity);
	
	/**
	 * Remove entity
	 * @param entity
	 */
	void remove(E entity);
	
	/**
	 * Find entity by key
	 * @param key key
	 * @return entity
	 */
	E find(K key);
    
	/**
	 * List all entities
	 * @return list of entities
	 */
	List<E> list();
	
	/**
	 * Removes all entities from DB.
	 * NOTE : Use this method only for test purposes
	 * @return 
	 */
	void clearAll();
}
