package com.infy.Product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infy.Product.Dto.ProductDTO;
import com.infy.Product.Dto.SubscribedproductDTO;
import com.infy.Product.service.ProductMSException;
import com.infy.Product.service.ProductService;



@RestController
@CrossOrigin
public class ProductController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductService productService;
	
	// Fetches all products
	@GetMapping(value = "/api/products",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getAllProducts() throws ProductMSException{
		logger.info("Fetching all products");
		System.out.println("In controller");
		return productService.getAllProducts();
	}
	//Fetch products according to id
	@GetMapping(value = "/api/products/{prodid}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO getProductsDetails(@PathVariable Integer prodid) throws ProductMSException{
		logger.info("fetching details of Product with product id {}", prodid);
		return productService.getProductById(prodid);
	}
	
	// Fetches products according to category
	@GetMapping(value = "/api/{category}/products",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductsByCategory(@PathVariable String category) throws ProductMSException{
		logger.info("Product details for category {}", category);
		return productService.getProductByCategory(category);
	}
	
	// Fetches products according to product name
	@GetMapping(value = "/api/product/{productname}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductsByName(@PathVariable String productname) throws ProductMSException{
		logger.info("Product details for product name {}", productname);
		return productService.getProductByName(productname);
	}
	@PostMapping(value= "/api/seller/add/{sellerid}" , consumes = MediaType.APPLICATION_JSON_VALUE)
	//public List<ProductDTO> addProduct(@PathVariable String category) throws ProductMSException{
	public void addProduct(@PathVariable int sellerid, @RequestBody ProductDTO productdto)   throws ProductMSException{
	
    productService.addProduct(sellerid, productdto);
    
	
}
	@PutMapping(value= "/api/products/{prodid}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateStock(@PathVariable Integer prodid, @RequestBody Integer stock)   throws ProductMSException{
		logger.info("Update request for product with product id {}", prodid);
    productService.updateStock(prodid, stock);
    
	
}
	
	@PostMapping(value= "/api/subscriptions/{subid}/add" , produces = MediaType.APPLICATION_JSON_VALUE)
	//public List<ProductDTO> addProduct(@PathVariable String category) throws ProductMSException{
	public void addProduct(@PathVariable int subid, @RequestBody SubscribedproductDTO subscribedproductDTO)   throws ProductMSException{
	
    productService.addSubscriptions(subid, subscribedproductDTO);
	}
    
	@GetMapping(value = "/api/subscriptions/{subid}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public SubscribedproductDTO getSubscriptionsDetails(@PathVariable Integer subid) throws ProductMSException{
		logger.info("fetching details of Product with product id {}", subid);
		return productService.getDetailsBysubId(subid);
	}
	
	
	
	
	
}