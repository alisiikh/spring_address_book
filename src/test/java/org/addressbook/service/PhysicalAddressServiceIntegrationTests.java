package org.addressbook.service;

import org.addressbook.config.ApplicationConfig;
import org.addressbook.persistence.domain.Phone;
import org.addressbook.persistence.domain.PhysicalAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;

/**
 * @author alisiikh.
 */
@ContextConfiguration(classes = {ApplicationConfig.class})
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class PhysicalAddressServiceIntegrationTests {

    @Inject
    private PhysicalAddressService physicalAddressService;

    @Test
    public void testCreateNewAddress() {
        PhysicalAddress physicalAddress = physicalAddressService.create();
        assert physicalAddress != null;
    }

    @Test
    public void testSaveNewPhysicalAddress() {
        PhysicalAddress physicalAddress = physicalAddressService.create();

        physicalAddress.setStreet1("Fake street 1");

        physicalAddressService.save(physicalAddress);

        assert physicalAddress.getId() != null;
    }

    @Test
    public void testUpdatePhysicalAddress() {
        PhysicalAddress physicalAddress = physicalAddressService.create();
        physicalAddress.setStreet1("Fake street 1");
        physicalAddressService.save(physicalAddress);

        Long oldId = physicalAddress.getId();
        assert oldId != null;

        physicalAddress.setPostalCode("fakepostalcode");

        physicalAddressService.save(physicalAddress);

        assert physicalAddress.getId().equals(oldId);
    }

    @Test
    public void testDeletePhysicalAddress() {
        PhysicalAddress physicalAddress = physicalAddressService.create();
        physicalAddress.setStreet1("Fake street 1");
        physicalAddressService.save(physicalAddress);

        Long oldId = physicalAddress.getId();

        physicalAddressService.delete(physicalAddress);

        PhysicalAddress p = physicalAddressService.get(oldId);

        assertTrue("Address instance should be removed", p == null);
    }

    @Test
    public void testSavePhoneNumbers() {
        PhysicalAddress physicalAddress = physicalAddressService.create();
        physicalAddress.setStreet1("Fake street 1");

        physicalAddressService.savePhoneNumbers(physicalAddress, new HashSet<>(
                Arrays.asList("+38(050) 000-00-00", "+38(063) 000-00-00", "+38(093) 000-00-00")
        ));

        assertTrue("Three phone numbers got persisted", physicalAddress.getPhones().size() == 3);

        physicalAddressService.savePhoneNumbers(physicalAddress, new HashSet<>(Arrays.asList("+38(093) 000-00-00")));

        assertTrue("Two phone numbers should be remove, one remains", physicalAddress.getPhones().size() == 1);
    }
}
