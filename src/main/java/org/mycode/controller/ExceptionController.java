package org.mycode.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.mycode.exceptions.CannotSignUpException;
import org.mycode.exceptions.NoSuchEntryException;
import org.mycode.exceptions.NotUniqueEntryException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionController {
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchEntryException.class)
    public ModelAndView handleNoSuchEntryException() {
        return new ModelAndView("errors/error404");
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotUniqueEntryException.class)
    public ModelAndView handleNotUniqueEntryException() {
        return new ModelAndView("errors/error400");
    }

    @ExceptionHandler({SQLException.class, JpaSystemException.class, GenericJDBCException.class})
    public ModelAndView handleRepoStorageException(Exception e) {
        if (e.getCause().getCause().getMessage().equals("This room is not free")) {
            return new ModelAndView("errors/error400", HttpStatus.BAD_REQUEST);
        }
        return new ModelAndView("errors/error500", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler({ConstraintViolationException.class, DataIntegrityViolationException.class})
    public ModelAndView handleConstraintException() {
        return new ModelAndView("errors/error409");
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(CannotSignUpException.class)
    public ModelAndView handleSignUpException() {
        return new ModelAndView("errors/failedsignup");
    }
}
