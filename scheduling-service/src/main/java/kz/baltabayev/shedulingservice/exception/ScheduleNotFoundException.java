package kz.baltabayev.shedulingservice.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(Long id) {
        super("Schedule with id %s not found".formatted(id));
    }
}
