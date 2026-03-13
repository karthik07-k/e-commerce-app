package com.org.ecom.service;

import com.org.ecom.exception.ProductNotFoundException;
import com.org.ecom.exception.ProductPurchaseException;
import com.org.ecom.model.ProductPurchaseRequest;
import com.org.ecom.model.ProductPurchaseResponse;
import com.org.ecom.model.ProductRequest;
import com.org.ecom.model.ProductResponse;
import com.org.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    public ProductResponse createProduct(ProductRequest request) {
        var product = mapper.toProduct(request);
        return mapper.toProductResponse(repository.save(product));
    }

    /**
     *  Description: This method processes a list of product purchase requests.
     *  It first retrieves the products from the repository based on the provided product IDs.
     *  If any product is not found, it throws a ProductPurchaseException.
     *  Then, it checks if the available quantity of each product is sufficient for the requested purchase quantity.
     *  If not, it throws another ProductPurchaseException indicating insufficient quantity.
     *  If all checks pass, it updates the available quantity of each product and saves the changes to the repository.
     *  Finally, it returns a list of ProductPurchaseResponse objects containing the details of the purchased products.
     * @param request
     * @return
     */
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if (storedProducts.size() != productIds.size()) {
            throw new ProductPurchaseException("One or more products not found for purchase");
        }
        var storesRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest =  storesRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient quantity for product with id " + product.getId());
            }
            var newAvailableQuantity = productRequest.quantity() - product.getAvailableQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public List<ProductResponse> getAllProducts() {
        return repository.findAll().stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + productId + " not found"));
    }
}
