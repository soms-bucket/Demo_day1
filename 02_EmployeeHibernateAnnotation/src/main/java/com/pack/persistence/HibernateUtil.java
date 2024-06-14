package com.pack.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
 
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();
                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);
                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();
                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }
 
    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
/*
* 
* ServiceRegistry: 
* 			defines service registry contracts that applications are likely to want to utilize for configuring Hibernate behavior. The two popular implementations are BootstrapServiceRegistry, StandardServiceRegistry and SessionFactoryServiceRegistry.
   BootstrapServiceRegistry: 
   				holds the services Hibernate will need during bootstrapping and at run time. For example, it can store the reference to ClassLoaderService, IntegratorService or StrategySelector through BootstrapServiceRegistry instance.
StandardServiceRegistry: 
		hosts and manages services in runtime.
SessionFactoryServiceRegistry: 
		is designed to hold services that need access to the SessionFactory, for example, EventListenerRegistry and StatisticsImplementor.
MetaData: 
		an object containing the parsed representations of an application domain model and its mapping to a database.
MetadataSources:
		provides the source information to be parsed to form MetaData.
* */