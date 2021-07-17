package com.example.examclient.controller;

import com.example.examclient.service.Product;
import com.example.examclient.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("product", productService.getAllProducts());
        return "admin/product/index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product/form";
    }
    @RequestMapping( method = RequestMethod.POST)
    public String store(Product product) {
        productService.addProduct(product);
        return "redirect:/product";
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "admin/product/update";
    }
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public String updateQuantity(@PathVariable Long id, Product product,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "admin/product/update";
        }
        productService.sellProduct(id,product.getQuantity());
        return "redirect:/product";
    }
}
