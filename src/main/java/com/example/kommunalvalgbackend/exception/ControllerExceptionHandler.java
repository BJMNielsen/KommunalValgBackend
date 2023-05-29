package com.example.kommunalvalg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {


    // Vores http kald vil vi gerne have returner en responseEntity, så vi laver vores exceptions om til at return en responseentity.
    // så vi med det entity kan forklare hvad der gik galt hvis der skete en exception.

    // Her kommer alle exceptions igennem, en global handler.
    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        // Her bruger vi vores ErrorMessage klasse der skal bruge en int statuskode, dato, message og beskrivelse af fejlen.
        // Vi sætter vores generelle status fejlkode til at være INTERNAL_SERVER_ERROR.
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                "Global Exception caught: " + ex.getMessage(),
                "The description of the request: " + request.getDescription(true));
        // ex.getMessage() er den exception vi selv skriver i vores "else { throw new Exception("Random exception besked")
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorMessage> resourceNotFoundExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                "Resource Not Found Exception caught: " + ex.getMessage(),
                "The description of the request: " + request.getDescription(true));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    ResponseEntity<ErrorMessage> resourceAlreadyExistsExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                "Resource Already Exists Exception caught: " + ex.getMessage(),
                "The description of the request: " + request.getDescription(true));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}

