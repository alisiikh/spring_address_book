package org.addressbook.service.impl;

import org.addressbook.service.CrudService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author alisiikh.
 */
@Service
public abstract class CrudServiceImpl<T, ID extends Serializable> implements CrudService<T, ID> {

    @Transactional(readOnly = true)
    @Override
    public T get(ID id) {
        return getRepository().findOne(id);
    }

    @Transactional
    @Override
    public T save(T t) {
        return getRepository().save(t);
    }

    @Transactional
    @Override
    public void delete(ID id) {
        getRepository().delete(id);
    }

    @Transactional
    @Override
    public void delete(T t) {
        getRepository().delete(t);
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> list() {
        return getRepository().findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> list(Pageable pageable) {
        return getRepository().findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Long count() {
        return getRepository().count();
    }
}
