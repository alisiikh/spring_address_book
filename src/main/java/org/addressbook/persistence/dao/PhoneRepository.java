package org.addressbook.persistence.dao;

import org.addressbook.persistence.domain.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author alisiikh.
 */
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
