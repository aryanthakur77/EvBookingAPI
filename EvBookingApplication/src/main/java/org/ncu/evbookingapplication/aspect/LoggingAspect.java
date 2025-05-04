package org.ncu.evbookingapplication.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.ncu.evbookingapplication.entity.Booking;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @AfterReturning("execution(* org.ncu.evbookingapplication.service.BookingService.createBooking(..))")
    public void logBookingCreation(JoinPoint jp) {
        Object[] args = jp.getArgs();
        log.info("Booking created for station ID: {}, Time: {}", args[0], ((Booking) args[1]).getBookingTime());
    }

    @Before("execution(* org.ncu.evbookingapplication.service.BookingService.cancelBooking(..))")
    public void logBookingCancellation(JoinPoint jp) {
        log.info("Booking cancellation initiated for ID: {}", jp.getArgs()[0]);
    }
}

