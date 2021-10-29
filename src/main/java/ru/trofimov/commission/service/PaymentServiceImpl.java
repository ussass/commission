package ru.trofimov.commission.service;

import org.springframework.stereotype.Service;
import ru.trofimov.commission.model.Payment;
import ru.trofimov.commission.model.PaymentResponse;
import ru.trofimov.commission.repositories.PaymentRepository;

import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentResponse calculationOfCommissionForMonth(Long phoneNumber, int month, int year) {
        Date startDate = new Date(year, month, 1);
        int nextYear = year;
        int nextMonth = month + 1;
        if (nextMonth > 11){
            nextMonth = 0;
            nextYear++;
        }
        Date nextDate = new Date(nextYear, nextMonth, 1);
        List<Payment> payments = paymentRepository
                .findByPhoneNumberAndDateBetween(phoneNumber, startDate.getTime(), nextDate.getTime() - 1);

        long monthlyPayments = payments.stream().mapToLong(Payment::getPaymentAmount).sum();

        return new PaymentResponse(phoneNumber, monthlyPayments, month, year);
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}
