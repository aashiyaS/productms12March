package com.infy.Product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.infy.Product.Dto.ProductDTO;
import com.infy.Product.Dto.SubscribedproductDTO;
import com.infy.Product.entity.Product;
import com.infy.Product.entity.Subscribedproduct;
import com.infy.Product.repository.ProductRepository;
import com.infy.Product.repository.SubscribedproductRepository;



@Service
public class ProductServiceImpl implements ProductService{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SubscribedproductRepository subrepo;
	//Get the entire product list
	public List<ProductDTO> getAllProducts() throws ProductMSException{
		System.out.println("In service");
		List<Product>products = productRepository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		for(Product p:products) {
			ProductDTO productDTO = ProductDTO.valueOf(p);
			productDTOs.add(productDTO);
		}
		logger.info("Product details : {}", productDTOs);
		return productDTOs;
	}

	@Override
	public List<ProductDTO> getProductByCategory(@PathVariable String category) throws ProductMSException {
		// TODO Auto-generated method stub
		List<Product> product = productRepository.findByCategory(category);
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		for(Product p:product) {
			productDTOs.add(ProductDTO.valueOf(p));
		}
		logger.info("Product details according to category : {}", productDTOs);
		
		return productDTOs;
	}

	@Override
	public List<ProductDTO> getProductByName(String productname) throws ProductMSException {
		// TODO Auto-generated method stub
		
		List<Product> products = productRepository.findByProductname(productname);
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		for(Product p:products) {
			productDTOs.add(ProductDTO.valueOf(p));
		}
		logger.info("Product details according to product name : {}", productDTOs);
		return productDTOs;
	}
	public ProductDTO getProductById(Integer prodid) {
		// TODO Auto-generated method stub
		logger.info("Product details according to product id : {}", prodid);
		ProductDTO productDTO = null;
		Optional<Product> product = productRepository.findById(prodid);
		
		if(product.isPresent()) {
			Product p = product.get();
			productDTO = ProductDTO.valueOf(p);
		}
		return productDTO;
		
		
		
		
	}
	
	public void addProduct(int sellerid,ProductDTO productdto ) {
		//productdto.setSellerid(sellerid);
		Product product = productdto.createEntity();
		productRepository.save(product);
		
	}
	
	public void updateStock(Integer prodid,Integer stock) {
	logger.info("Update request for Product with productID {}", prodid);
	Optional<Product> product = productRepository.findById(prodid);
			
	if(product.isPresent()) {
		Product p = product.get();
		ProductDTO pd = ProductDTO.valueOf(p);
		pd.setStock(stock);
		Product newproduct = pd.createEntity();
		productRepository.save(newproduct);
		logger.info("Update of stock successfull {}", stock);
		
	}
	else {
		logger.info("product with product id  {} does not exist ", prodid);
	}
}
	@Override
	public void  addSubscriptions(int subid, SubscribedproductDTO subscribedproductDTO) {
		// TODO Auto-generated method stub
		subscribedproductDTO.setSubid(subid);
		Subscribedproduct product = subscribedproductDTO.createEntity();
        subrepo.save(product);
	
	}
	
	@Override
	public  SubscribedproductDTO getDetailsBysubId(Integer subid) throws ProductMSException {
		// TODO Auto-generated method stub
		logger.info("=====Subscription Details {}======", subid);
		SubscribedproductDTO subscribedproductDTO = null;
		Optional<Subscribedproduct> product = subrepo.findById(subid);
		if(product.isPresent()) {
			Subscribedproduct p = product.get();
			subscribedproductDTO = SubscribedproductDTO.valueOf(p);
		}
		return subscribedproductDTO;
	}
	
	
}
