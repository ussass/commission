package ru.trofimov.commission.repositories;

import ru.trofimov.commission.model.Payment;

import java.util.List;

public interface PaymentRepository {

    Payment findById(Long id);

    List<Payment> findAll();

    Payment save(Payment category);

    List<Payment> findByPhoneNumberAndDateBetween(long phoneNumber, long startTime, long dateTime);
}
