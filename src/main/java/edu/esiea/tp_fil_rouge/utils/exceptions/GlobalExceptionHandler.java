package edu.esiea.tp_fil_rouge.utils.exceptions;

import java.net.URI;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ProblemDetail> notFound(NotFoundException ex, HttpServletRequest req) {
      var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
      pd.setTitle("Not found");
      pd.setDetail(ex.getMessage());
      pd.setInstance(URI.create(req.getRequestURI()));
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pd);
  }

    @ExceptionHandler(IllegalStateException.class) 
    public ResponseEntity<ProblemDetail> handleConflict(RuntimeException ex, HttpServletRequest req) {
        var pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pd.setTitle("Conflict Detected");
        pd.setDetail(ex.getMessage());
        pd.setInstance(URI.create(req.getRequestURI()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(pd);
    }

    @ExceptionHandler({
        IllegalArgumentException.class,           
        HttpMessageNotReadableException.class,    
        MissingServletRequestParameterException.class, 
        MethodArgumentTypeMismatchException.class  
    })
    public ResponseEntity<ProblemDetail> handleBadRequest(Exception ex, HttpServletRequest req) {
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Bad Request");
        
        if (ex instanceof HttpMessageNotReadableException) {
            pd.setDetail("Le corps de la requête (JSON) est mal formé ou illisible.");
        } else if (ex instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException msrpe = (MissingServletRequestParameterException) ex;
            pd.setDetail("Le paramètre de requête '" + msrpe.getParameterName() + "' est manquant.");
        } else if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException matme = (MethodArgumentTypeMismatchException) ex;
            pd.setDetail("Le paramètre de requête '" + matme.getName() + "' a une valeur invalide : " + matme.getValue());
        } else {
            pd.setDetail(ex.getMessage());
        }
        
        pd.setInstance(URI.create(req.getRequestURI()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest req) {
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Validation Failed");
        pd.setDetail("Un ou plusieurs champs sont invalides.");
        pd.setInstance(URI.create(req.getRequestURI()));

        var errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                    fieldError -> fieldError.getField(), 
                    fieldError -> fieldError.getDefaultMessage(),
                    (existing, replacement) -> existing 
                ));

        pd.setProperty("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleAllUncaughtException(Exception ex, HttpServletRequest req) {
        var pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Internal Server Error");
        pd.setDetail("Une erreur inattendue est survenue.");
        return ResponseEntity.status(500).body(pd);
    }
}
