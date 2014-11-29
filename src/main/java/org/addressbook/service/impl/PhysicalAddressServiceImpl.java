package org.addressbook.service.impl;

import org.addressbook.persistence.dao.PhysicalAddressRepository;
import org.addressbook.persistence.domain.Phone;
import org.addressbook.persistence.domain.PhysicalAddress;
import org.addressbook.service.PhysicalAddressService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author alisiikh.
 */
@Service
public class PhysicalAddressServiceImpl extends CrudServiceImpl<PhysicalAddress, Long> implements PhysicalAddressService {

    @Inject
    private PhysicalAddressRepository physicalAddressRepository;


    @Transactional
    public void savePhoneNumbers(PhysicalAddress physicalAddress, Collection<String> phoneNumbers) {
        Set<String> storedPhoneNumbers = physicalAddress.getPhones().parallelStream()
                .map(Phone::getPhoneNumber).collect(Collectors.toSet());

        Set<String> allPhoneNumbers = new HashSet<>();

        allPhoneNumbers.addAll(storedPhoneNumbers);
        allPhoneNumbers.addAll(phoneNumbers);

        allPhoneNumbers.parallelStream().filter(p -> !p.isEmpty()).forEach(p -> {
            if (!storedPhoneNumbers.contains(p)) {
                physicalAddress.addToPhones(p);
            } else if (!phoneNumbers.contains(p)) {
                physicalAddress.removeFromPhones(p);
            }
        });

        save(physicalAddress);
    }

    @Override
    public PhysicalAddress create() {
        return new PhysicalAddress();
    }

    @Override
    public JpaRepository<PhysicalAddress, Long> getRepository() {
        return physicalAddressRepository;
    }
}
