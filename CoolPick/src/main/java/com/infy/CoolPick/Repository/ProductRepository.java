package com.infy.CoolPick.Repository;

import com.infy.CoolPick.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


    List<Product> findAllByProductName(String productName);

    List<Product> findAllByProductNameAndProductVendor(String productName,String productVendor);
}
