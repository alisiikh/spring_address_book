package org.addressbook.service;

import org.addressbook.persistence.domain.PhysicalAddress;

import java.util.Collection;

/**
 * @author alisiikh.
 */
public interface PhysicalAddressService extends CrudService<PhysicalAddress, Long> {
    void savePhoneNumbers(PhysicalAddress physicalAddress, Collection<String> phoneNumbers);
}
