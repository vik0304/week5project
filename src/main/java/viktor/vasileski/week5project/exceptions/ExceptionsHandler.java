package viktor.vasileski.week5project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import viktor.vasileski.week5project.payloads.ErrorsDTO;
import viktor.vasileski.week5project.payloads.ErrorsListDTO;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler extends RuntimeException {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handlerNotFound(NotFoundException e){
        return new ErrorsDTO("Elemento non trovato o id errato", LocalDateTime.now());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsListDTO handleValidationErrors(ValidationException e){
        return new ErrorsListDTO(e.getMessage(), LocalDateTime.now(), e.getErrors());
    }
}
