package studos.logic;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Main util database connection class.
 */
public class DbConnect {
  /**
   * Standard Service Registry.
   */
  private StandardServiceRegistry registry;
  /**
   * Session Factory.
   */
  private SessionFactory sessionFactory;

  /**
   * Builds registry.
   */
  public DbConnect() {
      this.registry = new StandardServiceRegistryBuilder().
      configure("hibernate.cfg.xml").
      build();
  }

  /**
   * SessionFactory getter.
   *
   *@return SesstionFactory.
   */
  public SessionFactory getSessionFactory() {
    // Create MetadataSources
    final MetadataSources sources = new MetadataSources(registry);

  // Create Metadata
  //Metadata metadata = sources.getMetadataBuilder().build();

  // Create SessionFactory
  //sessionFactory = metadata.getSessionFactoryBuilder().build();
  sessionFactory = sources.buildMetadata().buildSessionFactory();

  return sessionFactory;
}

}
