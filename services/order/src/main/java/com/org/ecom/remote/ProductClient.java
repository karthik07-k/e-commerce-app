package com.org.ecom.remote;

import com.org.ecom.model.PurchaseRequest;
import com.org.ecom.remote.model.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.rmi.RemoteException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url")
    private String productUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts (List<PurchaseRequest> requestBody) {
        try {
            //call the product ms to purchase the products
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<List<PurchaseRequest>> request = new HttpEntity<>(requestBody, headers);
            ParameterizedTypeReference<List<PurchaseResponse>> responseType =
                    new ParameterizedTypeReference<List<PurchaseResponse>>() {
                    };
            ResponseEntity<List<PurchaseResponse>> response = restTemplate.exchange(productUrl + "/purchase",
                    HttpMethod.POST,
                    request,
                    responseType);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to purchase products: " + e.getMessage(), e);
        }
    }
}
