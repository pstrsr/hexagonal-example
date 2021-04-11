package de.strasser.peter.hexagonal.application.customer.exception;

import java.text.MessageFormat;

public class UserIsTooYoungExc extends BusinessException {
    public UserIsTooYoungExc(int age) {
        super(MessageFormat.format("Customer is too young. Expected: > 18 yrs, Actual: {0}", age));
    }
}
