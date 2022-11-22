package com.infy.CoolPick.Controller;


import com.infy.CoolPick.DTO.ProductDTO;
import com.infy.CoolPick.Entity.Product;
import com.infy.CoolPick.Exceptions.ProductNotFoundException;
import com.infy.CoolPick.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/greet")
    public String greet(){
        return "Have a nice day.";
    }


    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO){

       return ResponseEntity.status(HttpStatus.CREATED).body(service.addProduct(productDTO));

    }

    @GetMapping(value = "/{productName}",params = "version=1")
    public ResponseEntity<List<ProductDTO>> getProduct(@PathVariable  String productName){
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> productList = service.getProducts(productName);
        for(Product p : productList){
            productDTOList.add(modelMapper.map(p,ProductDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(productDTOList);
    }

    @GetMapping(params="version=2")
    public ResponseEntity<List<ProductDTO>> getProductByNameAndVendor(@RequestParam("productName")  String productName,
                                                                   @RequestParam("productVendor") String productVendor)  {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> productList = service.getProductsByNameAndVendor(productName,productVendor);
        for(Product p : productList){
            productDTOList.add(modelMapper.map(p,ProductDTO.class));
        }
        return ResponseEntity.status(HttpStatus.OK).body(productDTOList);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("id") Integer id,ProductDTO productDTO) throws ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteProduct(id,productDTO));
    }



}
