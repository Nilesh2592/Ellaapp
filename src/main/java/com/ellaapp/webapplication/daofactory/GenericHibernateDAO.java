package com.ellaapp.webapplication.daofactory;

import com.ellaapp.webapplication.util.HibernateFactory;
import java.io.Serializable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

public abstract class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private static final Logger LOG = LogManager.getLogger(GenericHibernateDAO.class);

    final SessionFactory factory = HibernateFactory.getSessionFactory();

    public GenericHibernateDAO() {
    }

    public SessionFactory getSessionFactory() {
        return factory;
    }

    protected abstract Class<T> getPersistentClass();

    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(List<Order> orders, Criterion... criterions) {
        List<T> results = null;
        LOG.info("Entering findByCriteria(List<Order> orders, Criterion... criterion)");

        Session session = this.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(getPersistentClass());
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        for (Order order : orders) {
            criteria.addOrder(order);
        }
        results = criteria.list();
        tx.commit();
        session.close();
        LOG.info("Exiting findByCriteria(List<Order> orders, Criterion... criterion) With List ");
        return results;
    }

    public T findById(ID id) {
        return findById(id, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(ID id, boolean lock) {
        LOG.info("Entered  findById(ID id, boolean lock): " + getPersistentClass().getSimpleName());
        Session session = this.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        T entity;
        if (lock) {
            entity = (T) session.get(getPersistentClass(), id, LockOptions.UPGRADE);
        } else {
            entity = (T) session.get(getPersistentClass(), id);
        }
        tx.commit();
        session.close();
        LOG.info("Exiting  findById(ID id, boolean lock)");
        return entity;
    }

    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    @Override
    public List<T> findByExample(T exampleInstance, String... excludeProperty) {
        LOG.info("Entered findByExample(T exampleInstance, String... excludeProperty): " + getPersistentClass().getSimpleName());
        List<T> results = null;
        Session session = this.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(getPersistentClass());
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        criteria.add(example);
        tx.commit();
        session.close();
        LOG.info("Exiting findByExample(T exampleInstance, String... excludeProperty)");
        return results;
    }

    @Override
    public void makePersistent(T entity) {

        LOG.info("Entered makePersistent(T entity): " + getPersistentClass().getSimpleName());
        Session session = this.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(entity);
            tx.commit();
        } catch (RuntimeException ex) {
            tx.rollback();
            throw ex;
        } finally {
            session.close();
        }

        LOG.info("Exiting makePersistent(T entity)");

    }

    @SuppressWarnings("unchecked")
    public T merge(T entity) {
        LOG.info("Entered merge(T entity)" + getPersistentClass().getSimpleName());
        Session session = this.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            entity = (T) session.merge(entity);
            tx.commit();
        } catch (RuntimeException ex) {
            tx.rollback();
            throw ex;
        } finally {
            session.close();
        }
        LOG.info("Exiting merge(T entity)");
        return entity;
    }

    public void save(List<T> list) {
        LOG.info("Entered save(List<T> list): " + getPersistentClass().getSimpleName());
        Session session = this.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            for (T entity : list) {
                session.saveOrUpdate(entity);
            }
            tx.commit();
        } catch (RuntimeException ex) {
            tx.rollback();
            throw ex;
        } finally {
            session.close();
        }
        LOG.info("Exiting save(List<T> list)");
    }

    public void save(T entity) {
        LOG.info("Entered save(T entity): " + getPersistentClass().getSimpleName());
        Session session = this.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(entity);
            tx.commit();
        } catch (RuntimeException ex) {
            tx.rollback();
            throw ex;
        } finally {
            session.close();
        }
        LOG.info("Exiting save(T entity)");

    }

    @Override
    public void makeTransient(T entity) {
        LOG.info("Entered makeTransient(T entity)" + getPersistentClass().getSimpleName());
        Session session = this.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(entity);
            tx.commit();
        } catch (RuntimeException ex) {
            tx.rollback();
            throw ex;
        } finally {
            session.close();
        }

        LOG.info("Exiting makeTransient(T entity)");
    }

    /**
     * Get the list of records of <T> for the given criterion and order.
     *
     * @param criterion Conditions for the result to be filtered.
     * @return<T> List of <T> on the given criterion and order.
     */
    protected List<T> findByCriteria(Criterion... criterion) {
        return findByCriteria(null, 0, -1, criterion);
    }

    /**
     * Get the list of records of <T> for the given criterion and order.
     *
     * @param maxResultLimit
     * @param criterion Conditions for the result to be filtered.
     * @return<T> List of <T> on the given criterion and order.
     */
    protected List<T> findByCriteria(int maxResultLimit, Criterion... criterion) {
        return findByCriteria(null, 0, maxResultLimit, criterion);
    }

    /**
     * Get the list of records of <T> for the given criterion and order.
     *
     * @param firstRecord
     * @param criterion Conditions for the result to be filtered.
     * @param maxResultLimit
     * @return<T> List of <T> on the given criterion and order.
     */
    protected List<T> findByCriteria(int firstRecord, int maxResultLimit, Criterion... criterion) {
        return findByCriteria(null, firstRecord, maxResultLimit, criterion);
    }

    /**
     * Get the list of records of <T> for the given criterion and order.
     *
     * @param orderBy The order in which you want the result should be sorted.
     * @param criterion Conditions for the result to be filtered.
     * @return List of <T> on the given criterion and order.
     */
    protected List<T> findByCriteria(Order orderBy, Criterion... criterion) {
        return findByCriteria(orderBy, 0, -1, criterion);
    }

    /**
     * It will get the first record from the criteria.
     *
     * @param criterion to select the record.
     * @return First object from the result if available, <code>null</code>
     * other wise.
     */
    protected T getFirstObjectByCriteria(Criterion... criterion) {

        LOG.info("Entered getFirstObjectByCriteria(Criterion... criterion)");

        T t = null;
        List<T> list = findByCriteria(null, 0, 1, criterion);
        if (list.size() > 0) {
            t = list.get(0);
        }

        LOG.info("Exiting getFirstObjectByCriteria(Criterion... criterion)");
        return t;
    }

    /**
     * It will get the first record from the criteria.
     *
     * @param orderBy if you want to sort the result.
     * @param criterion to select the record.
     * @return First object from the result if available, <code>null</code>
     * other wise.
     */
    protected T getFirstObjectByCriteria(Order orderBy, Criterion... criterion) {

        LOG.info("Entered getFirstObjectByCriteria(Order orderBy, Criterion... criterion)");

        T t = null;
        List<T> list = findByCriteria(orderBy, 0, 1, criterion);
        if (list.size() > 0) {
            t = list.get(0);
        }

        LOG.info("Exiting getFirstObjectByCriteria(Order orderBy, Criterion... criterion)");
        return t;
    }

    protected T getLastObjectByCriteria(Criterion... criterion) {

        LOG.info("Entered getLastObjectByCriteria(Criterion... criterion)");

        T t = null;
        List<T> list = findByCriteria(criterion);
        if (list.size() > 0) {
            t = list.get(list.size() - 1);
        }

        LOG.info("Exiting getLastObjectByCriteria(Criterion... criterion)");
        return t;
    }

    /**
     * This method will take the criterion from the user and return the result
     * based on that.<br>
     * If provided with the maxResultLimit ( &gt; 0 ) it will apply the limit.
     * And if the Order is provided it will sort it based on that.
     *
     * @param orderBy
     * @param maxResultLimit
     * @param criterion
     * @return
     */
    protected List<T> findByCriteria(Order orderBy, int maxResultLimit, Criterion... criterion) {
        return findByCriteria(orderBy, 0, maxResultLimit, criterion);
    }

    /**
     * This method will take the criterion from the user and return the result
     * based on that.<br>
     * If provided with the maxResultLimit ( &gt; 0 ) it will apply the limit.
     * And if the Order is provided it will sort it based on that.
     *
     * @param orderBy
     * @param firstRecord
     * @param maxResultLimit
     * @param criterion
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Order orderBy, int firstRecord, int maxResultLimit, Criterion... criterion) {

        LOG.info("Entered findByCriteria(Order orderBy, int firstRecord, int maxResultLimit, Criterion... criterion)");
        List<T> results = null;
        Session session = this.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(getPersistentClass());
        if (maxResultLimit > 0) {
            criteria.setMaxResults(maxResultLimit);
        }
        if (firstRecord > 0) {
            criteria.setFirstResult(firstRecord);
        }
        for (Criterion c : criterion) {
            criteria.add(c);
        }
        if (orderBy != null) {
            criteria.addOrder(orderBy);
        }
        results = criteria.list();
        tx.commit();
        session.close();
        LOG.info("Exiting findByCriteria(Order orderBy, int firstRecord, int maxResultLimit, Criterion... criterion)");
        return results;
    }

    /**
     * This method is used for retrieving the count of the records. Using this
     * method will return a scalar value that could be either integer or long.
     *
     * @param criterions
     * @return either integer or long
     */
    protected Number getCountByCriteria(Criterion... criterions) {

        LOG.info("Entered getCountByCriteria( Criterion... criterions )");
        Session session = this.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(getPersistentClass());
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        Number countNumber = (Number) criteria.setProjection(Projections.rowCount()).uniqueResult();
        tx.commit();
        session.close();
        LOG.info("Exiting getCountByCriteria( Criterion... criterions )");
        return countNumber;
    }

}
