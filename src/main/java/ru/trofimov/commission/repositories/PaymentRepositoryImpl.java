package ru.trofimov.commission.repositories;

import org.springframework.stereotype.Repository;
import ru.trofimov.commission.model.Payment;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository{

    private static PaymentRepositoryImpl instance;

    private final List<Payment> payments;

    private PaymentRepositoryImpl(){
        payments = new ArrayList<>();
    }

    public static PaymentRepositoryImpl getInstance(){
        if(instance == null){
            instance = new PaymentRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Payment findById(Long id) {
        return payments.get(Math.toIntExact(id));
    }

    @Override
    public List<Payment> findAll() {
        return payments;
    }

    @Override
    public Payment save(Payment payment) {
        payments.add(payment);
        payment.setId(payments.size() - 1);
        return payment;
    }

    @Override
    public List<Payment> findByPhoneNumberAndDateBetween(long phoneNumber, long dateStart, long dateEnd) {
        return payments.stream()
                .filter(payment -> payment.getPhoneNumber() == phoneNumber)
                .filter(payment -> payment.getDate().getTime() >= dateStart)
                .filter(payment -> payment.getDate().getTime() <= dateEnd)
                .collect(Collectors.toList());
    }
}
