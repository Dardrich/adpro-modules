package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;

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

        List<Product> listProduct = productService.findAll();

        assertEquals(product1, listProduct.get(0));
        assertEquals(product2, listProduct.get(1));
    }
}
