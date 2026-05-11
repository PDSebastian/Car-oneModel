package ro.mycode.system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.mycode.car.exceptions.CarAlreadyExistsException;
import ro.mycode.car.exceptions.CarNotFoundException;
import ro.mycode.car.exceptions.InvalidModelException;
import ro.mycode.car.exceptions.InvalidYearException;
import ro.mycode.system.consntants.HintsConstants;
import ro.mycode.user.exceptions.UserAlreadyExistsException;
import ro.mycode.user.exceptions.UserNotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CarAlreadyExistsException.class)
     public ResponseEntity<ApiErrorResponse> handleCarAlreadyExistsException(CarAlreadyExistsException e){

      ApiErrorResponse apiErrorResponse=ApiErrorResponse.builder()
              .hint(HintsConstants.CAR_ALREADY_EXISTS_HINT_MESSAGE)
              .message(e.getMessage())
              .dateTime(LocalDateTime.now())
              .status(HttpStatus.CONFLICT.value())
              .build();




        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(apiErrorResponse);
    }
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCarNotFoundExceptiom(CarNotFoundException e){
        ApiErrorResponse apiErrorResponse=ApiErrorResponse.builder()
                .hint(HintsConstants.CAR_NOT_FOUND_HINT_MESSAGE)
                .message(e.getMessage())
                .dateTime(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiErrorResponse);
    }
    @ExceptionHandler(InvalidModelException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidModelException(InvalidModelException e){
        ApiErrorResponse apiErrorResponse=ApiErrorResponse.builder()
                .hint(HintsConstants.INVALID_MODEL_HINT_MESSAGE)
                .message(e.getMessage())
                .dateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiErrorResponse);
    }
    @ExceptionHandler(InvalidYearException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidYearException(InvalidYearException e){
        ApiErrorResponse apiErrorResponse=ApiErrorResponse.builder()
                .hint(HintsConstants.INVALID_YEAR_HINT_MESSAGE)
                .message(e.getMessage())
                .dateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();



        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiErrorResponse);
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        ApiErrorResponse apiErrorResponse=ApiErrorResponse.builder()
                .hint(HintsConstants.USER_ALREADY_EXISTS_HINT_MESSAGE)
                .message(e.getMessage())
                .dateTime(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(apiErrorResponse);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotFoundException(UserNotFoundException e){
        ApiErrorResponse apiErrorResponse=ApiErrorResponse.builder()
                .hint(HintsConstants.USER_NOT_FOUND_HINT_MESSAGE)
                .message(e.getMessage())
                .dateTime(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiErrorResponse);
    }





}
