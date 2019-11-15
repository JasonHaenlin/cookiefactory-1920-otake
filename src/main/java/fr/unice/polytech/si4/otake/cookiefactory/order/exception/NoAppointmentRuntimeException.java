package fr.unice.polytech.si4.otake.cookiefactory.order.exception;

/**
 * NoAppointmentRuntimeException
 */
public class NoAppointmentRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoAppointmentRuntimeException() {
        super("No appointment set for the order");
    }

}
