package com.dogstore.dogstore.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dogstore.dogstore.models.Manufacturer;
import com.dogstore.dogstore.models.Product;
import com.dogstore.dogstore.repository.ManufacturerRepository;
import com.dogstore.dogstore.repository.ProductRepository;

@Controller
public class RestController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    // REST Products interface
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public @ResponseBody List<Product> productsListRest() {
        return (List<Product>) productRepository.findAll();
    }
    /*
     * TODO REST Products by product type interface
     * 
     * @RequestMapping(value = "/products/jacket", method = RequestMethod.GET)
     * public @ResponseBody List<Product> getJacketProducts() {
     * return (List<Product>) productRepository.find;
     * }
     */

    // REST Manufacturer interface
    @RequestMapping(value = "/manufacturer", method = RequestMethod.GET)
    public @ResponseBody List<Manufacturer> manufacturersListRest() {
        return (List<Manufacturer>) manufacturerRepository.findAll();
    }

    /*
     * @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
     * public @ResponseBody Optional<Product> findProductRest(@PathVariable("id")
     * Long id) {
     * return productRepository.findById(id);
     * }
     * // REST PUT
     * 
     * @RequestMapping(value = "/editproducts", method = RequestMethod.PUT)
     * public @ResponseBody Product saveProductRest(Product product) {
     * return productRepository.save(product);
     * }
     * 
     * // REST DELETE
     * 
     * @RequestMapping(value = "/deleteproducts", method = RequestMethod.DELETE)
     * public @ResponseBody String deleteProductRest(Long id) {
     * productRepository.deleteById(id);
     * return "Deleted";
     * }
     * 
     * // REST POST
     * 
     * @RequestMapping(value = "/addproducts", method = RequestMethod.POST)
     * public @ResponseBody Product addProductRest(Product product) {
     * return productRepository.save(product);
     * }
     */

}
