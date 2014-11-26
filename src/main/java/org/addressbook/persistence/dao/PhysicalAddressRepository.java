package org.addressbook.persistence.dao;

import org.addressbook.persistence.domain.PhysicalAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author alisiikh.
 */
public interface PhysicalAddressRepository extends JpaRepository<PhysicalAddress, Long> {
}
