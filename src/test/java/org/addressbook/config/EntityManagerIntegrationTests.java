package org.addressbook.config;

import org.addressbook.persistence.domain.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertTrue;

/**
 * @author alisiikh.
 */
@ContextConfiguration(classes = {DataSourceConfig.class})
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class EntityManagerIntegrationTests {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void testPhoneManipulationsSuccessful() {
        EntityManager em = getEntityManager();

        Phone phone = new Phone();
        phone.setPhoneNumber("+38(063)-111-11-11");

        em.persist(phone);

        assertTrue("Phone should be persisted, id should be not null", phone.getId() != null);
    }

    protected EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
