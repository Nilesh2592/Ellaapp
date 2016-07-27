package com.ellaapp.webapplication.daofactory;

import java.io.Serializable;
import java.util.List;

/**
 * An interface shared by all business data access objects.
 * <p>
 * All CRUD (create, read, update, delete) basic data access operations are
 * isolated in this interface and shared across all DAO implementations.
 * The current design is for a state-management oriented persistence layer
 * (for example, there is no UPDATE statement function) that provides
 * automatic transactional dirty checking of business objects in persistent
 * state.
 * </p>
 * @author Christian Bauer
 * @param <T>
 * @param <ID>
 * @see <a href="http://community.jboss.org/wiki/GenericDataAccessObjects">
 *     http://community.jboss.org/wiki/GenericDataAccessObjects</a>
 */
public interface GenericDAO<T, ID extends Serializable> {

    T findById(ID id, boolean lock);

    List<T> findAll();

    List<T> findByExample(T exampleInstance, String... excludeProperty);

    void makePersistent(T entity);

    void makeTransient(T entity);

    

}