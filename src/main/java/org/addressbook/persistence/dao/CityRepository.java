package org.addressbook.persistence.dao;

import org.addressbook.persistence.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author alisiikh.
 */
public interface CityRepository extends JpaRepository<City, Long> {
}
