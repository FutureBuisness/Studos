package studos.logic;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * gufffno.
 */
public class DbConnect {
/**
 * a.
 */
  private Map<String, String> jdbcUrlSettings = new HashMap<>();
  /**
   * b.
   */
  private String jdbcDbUrl;
  /**c.
   *
   */
  private StandardServiceRegistry registry;
  /**
   * d.
   */
  private SessionFactory sessionFactory;
/**
 * DbConnect shit.
 */
  public DbConnect() {
    jdbcDbUrl = System.getenv("DATABASE_URL");
    if (null != jdbcDbUrl) {
      jdbcUrlSettings.put("hibernate.connection.url", jdbcDbUrl);
      this.registry = new StandardServiceRegistryBuilder().
      configure("hibernate.cfg.xml").
      applySettings(jdbcUrlSettings).
      build();
    }
  }
/**
 * Null shit.
 * @return SesstionFactory thing.
 */
public SessionFactory getSessionFactory() {
  //Create MetadataSources
  MetadataSources sources = new MetadataSources(registry);

  // Create Metadata
  Metadata metadata = sources.getMetadataBuilder().build();

  // Create SessionFactory
  sessionFactory = metadata.getSessionFactoryBuilder().build();

  return sessionFactory;
}

}
