package org.addressbook.persistence.domain;

import org.addressbook.config.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertTrue;

/**
 * @author alisiikh.
 */
@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AddressPersistenceIntegrationTests {

    @Inject
    private EntityManagerFactory emf;

    @Test
    public void testAddressAndPhonesRelationship() {
        EntityManager em = emf.createEntityManager();

        Address address = new Address();
        address.addToPhones(new Phone("+38(063)000-00-00"));
        address.addToPhones(new Phone("+38(063)123-45-67"));

        em.persist(address);

        assertTrue("Address is persisted", address.getId() != null);

        address.getPhones().forEach(p -> assertTrue("Phone is persisted by cascade", p.getId() != null));
    }
}
