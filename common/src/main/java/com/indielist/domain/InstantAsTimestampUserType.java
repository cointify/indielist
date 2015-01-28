package com.indielist.domain;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;
import java.time.Instant;

/**
 * @author jsingh on 15-01-10.
 */
public class InstantAsTimestampUserType implements UserType {

    private static final int[] SQL_TYPES = new int[]{Types.TIMESTAMP};

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class returnedClass() {
        return Instant.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true;
        }
        if (x == null || y == null) {
            return false;
        }
        Instant dtx = (Instant) x;
        Instant dty = (Instant) y;
        return dtx.equals(dty);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        Timestamp ts = (Timestamp) StandardBasicTypes.TIMESTAMP.nullSafeGet(rs, names, session, owner);
        if (ts == null) {
            return null;
        }
        Instant instant = ts.toInstant();
        return instant;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        if (value != null){
            Timestamp ts = Timestamp.from((Instant) value);
            StandardBasicTypes.TIMESTAMP.nullSafeSet(st, ts, index, session);
        } else {
            st.setNull(index, Types.TIMESTAMP);
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Instant) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}