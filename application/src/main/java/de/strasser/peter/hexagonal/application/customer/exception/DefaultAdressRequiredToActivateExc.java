package de.strasser.peter.hexagonal.application.customer.exception;

public class DefaultAdressRequiredToActivateExc extends IllegalStateException {

    public DefaultAdressRequiredToActivateExc() {
        super("Customer needs to have at least a default adress to be able to be activated!");
    }
}
