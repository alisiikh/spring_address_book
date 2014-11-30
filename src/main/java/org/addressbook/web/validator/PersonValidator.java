package org.addressbook.web.validator;

import org.addressbook.persistence.domain.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.Validator;

import javax.inject.Inject;

/**
 * @author alisiikh.
 */
@Component
public class PersonValidator implements Validator {

    @Inject
    private SmartValidator smartValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        smartValidator.validate(target, errors);
    }
}
