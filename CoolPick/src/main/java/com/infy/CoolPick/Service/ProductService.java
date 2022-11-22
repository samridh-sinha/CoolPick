package com.infy.CoolPick.Service;

import com.infy.CoolPick.DTO.ProductDTO;
import com.infy.CoolPick.Entity.Product;
import com.infy.CoolPick.Exceptions.ProductNotFoundException;
import com.infy.CoolPick.Repository.ProductRepository;
import org.hibernate.cfg.Environment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:ValidationMessages.properties")
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    private Environment environment;

    public ProductDTO addProduct(ProductDTO productDTO){

        Product product = productRepository.saveAndFlush(modelMapper.map(productDTO,Product.class));
        return modelMapper.map(product,ProductDTO.class);


    }

    public List<Product> getProducts(String productName){

       List<Product> productList =  productRepository.findAllByProductName(productName);
       return productList;

    }

    public List<Product> getProductsByNameAndVendor(String productName,String productVendor)  {

        List<Product> productList = productRepository.findAllByProductNameAndProductVendor(productName,productVendor);
        return productList;
    }

    public ProductDTO deleteProduct(Integer id,ProductDTO productDTO) throws ProductNotFoundException{

        Optional<Product> productOptional = productRepository.findById(id);
        Product product = null;
        if(productOptional.isPresent()){
            product = productOptional.get();
            productRepository.delete(product);
        }
        else{
            throw new ProductNotFoundException("Product not found for the goven id");
        }

        return modelMapper.map(product,ProductDTO.class);
    }





}
