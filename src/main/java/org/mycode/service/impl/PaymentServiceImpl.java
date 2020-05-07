package org.mycode.service.impl;

import org.mycode.model.PaymentType;
import org.mycode.repository.PaymentTypeRepository;
import org.mycode.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentTypeRepository paymentTypeRepository;

    @Autowired
    public PaymentServiceImpl(PaymentTypeRepository paymentTypeRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
    }

    @Override
    public List<String> getAllPaymentTypes() {
        return StreamSupport.stream(paymentTypeRepository.findAll().spliterator(), false)
                .map(PaymentType::getName).collect(Collectors.toList());
    }
}
