package com.dbtransactional.transactionalDemo.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.SharedEntityManagerCreator;

import com.dbtransactional.transactionalDemo.dao.SomeDao;
import com.dbtransactional.transactionalDemo.domain.Student;

@Configuration
@EntityScan(basePackageClasses = Student.class )
public class TransactionConfiguration {

//	
// This is not inject the Shared entity manager as a result @Transactional will fail	
//	@Bean
//	public SomeDao someDao(EntityManagerFactory entityManagerfactory) {
//		return new SomeDao().setEntityManager(entityManagerfactory.createEntityManager());
//	}
	@Bean
	public EntityManager sharedEntityManager(EntityManagerFactory entityManagerfactory) {
		return SharedEntityManagerCreator.createSharedEntityManager(entityManagerfactory);
	}
	
	
	@Bean
	public SomeDao someDao(@Qualifier("sharedEntityManager") EntityManager entityManager) {
		return new SomeDao().setEntityManager(entityManager);
	}
}
