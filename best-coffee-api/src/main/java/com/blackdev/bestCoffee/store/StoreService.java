package com.blackdev.bestCoffee.store;

import com.blackdev.bestCoffee.product.Product;
import com.blackdev.bestCoffee.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    public Collection<Store> getStores(){
        return this.storeRepository.findAll();
    }

    public Optional<Store> getById(long id){
        return this.storeRepository.findById(id);
    }

    public Store create(Store store){
        return this.storeRepository.save(store);
    }

    public Product createProduct(long storeId, Product product){
        Product newProduct = this.productRepository.save(product);
        if( newProduct != null ){
            Optional<Store> store = this.getById(storeId);
            if(store.isPresent()){
                store.get().getProducts().add(newProduct);
                this.storeRepository.saveAndFlush(store.get());
            }
        }
        return newProduct;
    }

    public boolean deleteProduct(long storeId, long productId){
        Optional<Store> store = this.getById(storeId);
        Optional<Product> product = this.productRepository.findById(productId);
        if(store.isPresent() && product.isPresent() && store.get().getProducts().contains(product.get())){
            store.get().getProducts().remove(product.get());
            this.storeRepository.saveAndFlush(store.get());
            this.productRepository.deleteById(productId);
            return true;
        }
        return false;
    }

    public boolean deleteStore(long id){
        boolean res = this.storeRepository.existsById(id);
        this.storeRepository.deleteById(id);
        return res;
    }

}
