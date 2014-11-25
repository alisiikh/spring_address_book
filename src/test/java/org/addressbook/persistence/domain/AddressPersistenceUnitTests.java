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
public class AddressPersistenceUnitTests {

    @Inject
    private EntityManagerFactory emf;

    @Test
    public void testAddressAndPhonesRelationship() {
        EntityManager em = emf.createEntityManager();

        PhysicalAddress address = new PhysicalAddress();
        address.addToPhones(new Phone("+38(063)000-00-00"));
        address.addToPhones(new Phone("+38(063)123-45-67"));

        Country ukraine = new Country(804, "UKR", "UKRAINE");

        City dnipro = new City("DNIPROPETROVSK");
        City kyiv = new City("KYIV");
        City lviv = new City("LVIV");

        ukraine.addToCities(dnipro);
        ukraine.addToCities(lviv);
        ukraine.addToCities(kyiv);
        em.persist(ukraine);

        address.setCountry(ukraine);
        address.setCompanyName("NAFTOGAZ");
        address.setCity(kyiv);
        address.setEmail("naftogaz@ukr.net");
        address.setPostalCode("00000");

        em.persist(address);

        assertTrue("Address is persisted", address.getId() != null);

        address.getPhones().forEach(p -> assertTrue("Phone is persisted by cascade", p.getId() != null));
    }
}
