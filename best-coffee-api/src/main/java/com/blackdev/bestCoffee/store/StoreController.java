package com.blackdev.bestCoffee.store;

import com.blackdev.bestCoffee.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PreAuthorize("hasAnyRole()")
    @GetMapping()
    public ResponseEntity<Collection<Store>> getAll(){
        Collection<Store> data = this.storeService.getStores();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Store>> getById(@PathVariable("id") Long id){
        Optional<Store> data = this.storeService.getById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/new-product")
    public ResponseEntity<Product> createProduct(@PathVariable("id") long id, @RequestBody Product product){
        Product newProduct = this.storeService.createProduct(id, product);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{storeId}/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("storeId") long storeId, @PathVariable("productId") long productId){
        boolean data = this.storeService.deleteProduct(storeId, productId);
        return (data)?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
