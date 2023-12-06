// package com.dogstore.dogstore.controllers;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.ResponseBody;

// import com.dogstore.dogstore.models.Manufacturer;
// import com.dogstore.dogstore.models.Product;
// import com.dogstore.dogstore.repository.ManufacturerRepository;
// import com.dogstore.dogstore.repository.ProductRepository;

// @Controller
// public class RestController {

// @Autowired
// private ProductRepository productRepository;
// @Autowired
// private ManufacturerRepository manufacturerRepository;

// // REST Products interface
// @RequestMapping(value = "/products", method = RequestMethod.GET)
// public @ResponseBody List<Product> productsListRest() {
// return (List<Product>) productRepository.findAll();
// }

// REST type search example:
// http://localhost:8080/api/products/search/findByType?type=Sadetakki

// // REST Manufacturer interface
// @RequestMapping(value = "/manufacturers", method = RequestMethod.GET)
// public @ResponseBody List<Manufacturer> manufacturersListRest() {
// return (List<Manufacturer>) manufacturerRepository.findAll();
// }

/*
 * @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
 * public @ResponseBody Optional<Product> findProductRest(@PathVariable("id")
 * Long id) { return productRepository.findById(id); } // REST PUT
 * 
 * @RequestMapping(value = "/editproducts", method = RequestMethod.PUT)
 * public @ResponseBody Product saveProductRest(Product product) { return
 * productRepository.save(product); }
 * 
 * // REST DELETE
 * 
 * @RequestMapping(value = "/deleteproducts", method = RequestMethod.DELETE)
 * public @ResponseBody String deleteProductRest(Long id) {
 * productRepository.deleteById(id); return "Deleted"; }
 * 
 * // REST POST
 * 
 * @RequestMapping(value = "/addproducts", method = RequestMethod.POST)
 * public @ResponseBody Product addProductRest(Product product) { return
 * productRepository.save(product); }
 */

// }
