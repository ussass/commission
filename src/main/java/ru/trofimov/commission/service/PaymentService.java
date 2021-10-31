package ru.trofimov.commission.service;

import ru.trofimov.commission.model.Payment;
import ru.trofimov.commission.model.PaymentResponse;

import java.util.List;

public interface PaymentService {

    PaymentResponse calculationOfCommissionForMonth(Long id, int month, int year);

    Payment findById(Long id);

    List<Payment> findAll();

    Payment save(Payment payment);
}
