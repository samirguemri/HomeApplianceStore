package com.samir.has.api.service;

import com.samir.has.api.doa.IProductDao;
import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final IProductDao produitDao;

    @Autowired
    public ProductService(@Qualifier("fakeProductDao") IProductDao produitDao) {
        this.produitDao = produitDao;
    }

    public void addProduct(Product produit){
        this.produitDao.insertNewProduct(produit);
    }

    public Product getProduct(LocalUniqueId productRef){
        return this.produitDao.selectProduct(productRef);
    }

    public List<Product> selectProductsByBrand(String marque){
        return this.produitDao.selectProductsByBrand(marque);
    }


    public List<Product> selectAllProducts(){
        return this.produitDao.selectAllProducts();
    }

    public boolean updateProduct(LocalUniqueId productRef, Product produit){
        return this.produitDao.updateProduct(productRef,produit);
    }

    public boolean removeProduct(LocalUniqueId productRef){
        return this.produitDao.removeProduct(productRef);
    }
    public void addStock(LocalUniqueId productRef, int newStock){
        Product product = this.produitDao.selectProduct(productRef);
        product.setAvailableStock(newStock);
        this.produitDao.updateProduct(productRef,product);
    }

}
