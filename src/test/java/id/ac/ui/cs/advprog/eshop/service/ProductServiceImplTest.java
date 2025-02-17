package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @InjectMocks
//    @Mock
    private ProductServiceImpl productService;

//    @Mock
    @InjectMocks
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {}

    @Test
    public void testFindAll(){
        Product product1 = new Product();
        Product product2 = new Product();

        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product2.setProductId("keb558e9f-1c39-460e-8860-71af6af63bd6");

        product1.setProductName("Sampo Cap Sabun");
        product2.setProductName("Sabun Cap Sampo");

        product1.setProductQuantity(100);
        product2.setProductQuantity(10);

        productService.create(product1);
        productService.create(product2);

//        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2).iterator());

        List<Product> listProduct = productService.findAll();

//        assertEquals(2, listProduct.size());
        assertEquals(product1, listProduct.get(0));
        assertEquals(product2, listProduct.get(1));
    }

    @Test
    public void testCreateProduct(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Sabun");
        product.setProductQuantity(100);

        assertEquals(product, productService.create(product));
    }

    @Test
    public void testEditProduct(){
        Product product = new Product();
        product.setProductName("Sampo Cap Sabun");
        product.setProductQuantity(100);
        productService.create(product);
//        System.out.println(productRepository.findAll());
        Product editedProduct = new Product();
        editedProduct.setProductId(product.getProductId());
        editedProduct.setProductName("Sampo Cap Ek");
        editedProduct.setProductQuantity(10);
        productService.edit(editedProduct);

        Product updatedProduct = productService.findById(product.getProductId());
        List <Product> listProduct = productService.findAll();
//        listProduct.forEach((product1) -> System.out.println(product1.getProductId()));
//        System.out.println(productService.findById(createdProduct.getProductId()));
//        System.out.println(createdProduct.getProductId());
//        System.out.println(editedProduct.getProductId());
//        System.out.println(updatedProduct.getProductId());
        assertEquals("Sampo Cap Ek", updatedProduct.getProductName());
        assertEquals(10, updatedProduct.getProductQuantity());
    }

    @Test
    public void testDeleteProduct(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Sabun");
        product.setProductQuantity(100);
        productService.create(product);

        assertFalse(productService.delete("id-salah"));
    }

    @Test
    public void testGetFound(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Sabun");
        product.setProductQuantity(100);
//        productService.create(product);

        when(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6"))
                .thenReturn(product);

        Product foundProduct = productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertEquals(product, foundProduct);
    }

    @Test
    public void testGetNotFound(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Sabun");
        product.setProductQuantity(100);
        productService.create(product);

        assertNull(productService.findById("id-salah"));
    }
}

