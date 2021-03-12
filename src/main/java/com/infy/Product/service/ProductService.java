package com.infy.Product.service;



import java.util.List;

import com.infy.Product.Dto.ProductDTO;
import com.infy.Product.Dto.SubscribedproductDTO;







public interface ProductService {
	public List<ProductDTO> getAllProducts() throws ProductMSException;
	public List<ProductDTO> getProductByCategory(String category)throws ProductMSException;
	public List<ProductDTO> getProductByName(String productname) throws ProductMSException;
	public void addProduct(int sellerid,ProductDTO productdto) throws ProductMSException;
	public ProductDTO getProductById(Integer prodid) throws ProductMSException;
	public void updateStock(Integer prodid,Integer stock) throws ProductMSException;
	public void addSubscriptions(int subid,SubscribedproductDTO subscribedproductDTO) throws ProductMSException;
	public SubscribedproductDTO getDetailsBysubId(Integer subid) throws ProductMSException;
}

