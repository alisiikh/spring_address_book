package org.addressbook.service.impl;

import org.addressbook.persistence.dao.PhysicalAddressRepository;
import org.addressbook.persistence.domain.PhysicalAddress;
import org.addressbook.service.PhysicalAddressService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author alisiikh.
 */
@Service
public class PhysicalAddressServiceImpl extends CrudServiceImpl<PhysicalAddress, Long> implements PhysicalAddressService {

    @Inject
    private PhysicalAddressRepository physicalAddressRepository;

    @Override
    public PhysicalAddress create() {
        return new PhysicalAddress();
    }

    @Override
    public JpaRepository<PhysicalAddress, Long> getRepository() {
        return physicalAddressRepository;
    }
}
