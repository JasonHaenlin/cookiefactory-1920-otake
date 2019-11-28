package fr.unice.polytech.si4.otake.cookiefactory.order.exception;

/**
 * NoAppointmentRuntimeException
 */
public class BadAppointmentRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BadAppointmentRuntimeException() {
        super("No appointment set for the order");
    }

}
