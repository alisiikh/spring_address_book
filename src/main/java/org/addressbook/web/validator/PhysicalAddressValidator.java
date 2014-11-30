package org.addressbook.web.validator;

import org.addressbook.persistence.domain.PhysicalAddress;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.inject.Inject;

/**
 * @author alisiikh.
 */
@Component
public class PhysicalAddressValidator implements Validator {

    @Inject
    private SmartValidator smartValidator;

    @Inject
    private Validator personValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(PhysicalAddress.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PhysicalAddress physicalAddress = (PhysicalAddress) target;

        smartValidator.validate(target, errors);

        try {
            errors.pushNestedPath("person");
            ValidationUtils.invokeValidator(personValidator, physicalAddress.getPerson(), errors);
        } finally {
            errors.popNestedPath();
        }
    }
}
