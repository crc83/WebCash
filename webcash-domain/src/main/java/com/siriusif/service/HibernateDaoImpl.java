package com.siriusif.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.siriusif.service.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
/**
 * Basic DAO operations dependent with Hibernate's specific classes
 * 
 * @see SessionFactory
 */
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public class HibernateDaoImpl<E, K extends Serializable> implements GenericDao<E, K> {
 
    private SessionFactory sessionFactory;
    protected Class<? extends E> daoType;
    
    @SuppressWarnings("unchecked")
	public HibernateDaoImpl() {
        daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }
 
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public void clearAll() {
    	for (E entity : list()){
    		currentSession().delete(entity);
    	}
    }
 
    public void add(E entity) {
        currentSession().save(entity);
    }
 
    public void update(E entity) {
        currentSession().saveOrUpdate(entity);
    }
 
    public void remove(E entity) {
        currentSession().delete(entity);
    }
 
    @SuppressWarnings("unchecked")
	public E find(K key) {
        return (E) currentSession().get(daoType, key);
    }

    @SuppressWarnings("unchecked")
	public List<E> list() {
        return currentSession().createCriteria(daoType).list();
    }
    
    protected void initializeCollection(Collection<?> collection) {
        // works with Hibernate EM 3.6.1-SNAPSHOT
        if(collection == null) {
            return;
        }
        collection.iterator().hasNext();
    }
}
