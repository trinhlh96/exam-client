package com.example.examclient.myService;

import com.example.examclient.service.ProductService;
import com.example.examclient.service.ProductServiceService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    @Bean
    public ProductService productService (){
        ProductServiceService productServiceService = new ProductServiceService();
        ProductService service = productServiceService.getProductServicePort();
        return service;
    }
}
