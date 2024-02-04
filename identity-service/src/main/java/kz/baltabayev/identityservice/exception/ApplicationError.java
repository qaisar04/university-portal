package kz.baltabayev.identityservice.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ApplicationError {
    private int status;
    private String message;
    @Builder.Default
    private Date timestamp = new Date();
}
