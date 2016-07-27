package com.ellaapp.webapplication.daofactory;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class HibernateInterceptor extends EmptyInterceptor {

    private static final long serialVersionUID = 1L;

    public void onDelete(Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {

        // log delete events
        //System.out.println("Delete event");
    }

    // called when a Student gets updated.
    public boolean onFlushDirty(Object entity,
            Serializable id,
            Object[] currentState,
            Object[] previousState,
            String[] propertyNames,
            Type[] types) {
        if (entity instanceof AuditDates) {
            int indexOfLastUpdate = Arrays.asList(propertyNames).indexOf("dateUpdated");

            currentState[indexOfLastUpdate] = new Date();
            //System.out.println("Update Operation");
            return true;
        }
        return false;
    }

    // called on load events
    public boolean onLoad(Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {

        // log loading events
        //System.out.println("Load Operation");
        return true;
    }

    public boolean onSave(Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
        if (entity instanceof AuditDates) {
            //System.out.println("Create Operation");
            int indexOfDateUpdated = Arrays.asList(propertyNames).indexOf("dateUpdated");
            int indexOfDateInserted = Arrays.asList(propertyNames).indexOf("dateInserted");

            state[indexOfDateUpdated] = new Date();
            state[indexOfDateInserted] = new Date();
            return true;
        }
        return false;
    }

    //called before commit into database
    public void preFlush(Iterator iterator) {
        //System.out.println("Before commiting");
    }

    //called after committed into database
    public void postFlush(Iterator iterator) {
        //System.out.println("After commiting");
    }
}
