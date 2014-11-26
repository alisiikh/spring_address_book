package org.addressbook.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * @author alisiikh.
 */
public interface CrudService<T, ID extends Serializable> {
    T get(ID id);

    T save(T t);

    T create();

    void delete(ID id);

    void delete(T t);

    List<T> list();

    List<T> list(Pageable pageable);

    Long count();

    JpaRepository<T, ID> getRepository();
}
