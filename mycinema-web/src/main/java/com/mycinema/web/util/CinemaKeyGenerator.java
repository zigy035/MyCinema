package com.mycinema.web.util;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CinemaKeyGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor implementor, Object object) throws HibernateException {
		return UUID.randomUUID().toString();
	}

}
