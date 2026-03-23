package com.org.ecom.service;

import com.org.ecom.model.Payment;
import com.org.ecom.model.PaymentRequest;
import com.org.ecom.notification.NotificationProducer;
import com.org.ecom.notification.PaymentNotificationRequest;
import com.org.ecom.repository.PaymentRepository;
import com.org.ecom.utils.PaymentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;


    /**
     *
     * @param request
     * @return
     */
    public Payment createPayment(@Valid PaymentRequest request) {
        var payment = paymentRepository.save(mapper.toPayment(request));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );
        return payment;
    }
}
