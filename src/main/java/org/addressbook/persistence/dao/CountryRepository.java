package org.addressbook.persistence.dao;

import org.addressbook.persistence.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author alisiikh.
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
}
