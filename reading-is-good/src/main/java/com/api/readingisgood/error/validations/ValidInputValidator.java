package com.api.readingisgood.error.validations;

import com.api.readingisgood.error.exceptions.BussinesException;
import com.api.readingisgood.models.CustomerRequest;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ValidInputValidator implements ConstraintValidator<ValidInput, Object[]> {

    @Override
    public void initialize(ValidInput constraint) {
        //no need
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        if (value[0] == null) {
            return true;
        }

        if (!(value[0] instanceof CustomerRequest)) {
            throw new IllegalArgumentException(
                    "Illegal method signature, expected customer");
        }

        CustomerRequest req = (CustomerRequest) value[0];
        if (req.getCustomerId() == null || req.getCustomerId() == "") {
            throw new BussinesException(
                    "customerId must be fulfilled", new Exception());
        }

        return true;
    }
}
