package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();

    public Product findById(String productId) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

//    public boolean edit(Product editedProduct) {
//        for (int i = 0; i < productData.size(); i++) {
//            Product product = productData.get(i);
//            if (product.getProductId().equals(editedProduct.getProductId())) {
//                productData.set(i, editedProduct);
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean edit(Product productUpdate) {
//        for (Product product : productData){
//            if(product.getProductId().equals(productUpdate.getProductId())){
//                product.setProductName(productUpdate.getProductName());
//                product.setProductQuantity(productUpdate.getProductQuantity());
//                return true;
//            }
//        }
//        return false;
        Product existingProduct = findById(productUpdate.getProductId());
        if (existingProduct != null) {
            existingProduct.setProductName(productUpdate.getProductName());
            existingProduct.setProductQuantity(productUpdate.getProductQuantity());
            return true;
        }
        return false;
    }


    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public boolean delete(String deletedProductId) {
        return productData.removeIf(product -> product.getProductId().equals(deletedProductId));
    }
}
