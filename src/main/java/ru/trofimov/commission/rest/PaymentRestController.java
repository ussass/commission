package ru.trofimov.commission.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.trofimov.commission.exception.InvalidRequestBodyException;
import ru.trofimov.commission.model.Payment;
import ru.trofimov.commission.model.PaymentResponse;
import ru.trofimov.commission.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/payments/")
public class PaymentRestController {

    private final PaymentService paymentService;

    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("{number}")
    public ResponseEntity<PaymentResponse> getPayment (@PathVariable long number,
                                                       @RequestParam(defaultValue = "-1") int month,
                                                       @RequestParam(defaultValue = "-1") int year) {

        Date date = new Date();
        if (month == -1){
            month = date.getMonth();
        }
        if (year == -1){
            year = date.getYear();
        }
        System.out.println("month = " + month);
        System.out.println("year = " + year);

        PaymentResponse paymentResponse = paymentService.calculationOfCommissionForMonth(number, month, year);

        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Payment> savePayment (HttpServletRequest request, @RequestBody Payment payment) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (payment.getPhoneNumber() == 0) {
            throw new InvalidRequestBodyException("invalid request body");
        }
        payment.setDate(new Date());
        paymentService.save(payment);
        httpHeaders.add("Location", request.getRequestURL().toString() + payment.getPhoneNumber());

        return new ResponseEntity<>(payment, httpHeaders, HttpStatus.CREATED);
    }
}
