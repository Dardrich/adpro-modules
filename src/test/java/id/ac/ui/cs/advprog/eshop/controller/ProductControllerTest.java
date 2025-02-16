package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private ProductService service;

    @Autowired
    private JacksonTester<Product> jsonProduct;

    @Test
    void testHomePage() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("HomePage"));
    }

    @Test
    void testProductListPage() throws Exception{
        MockHttpServletResponse response = mvc.perform(get("/product/list")).andReturn().getResponse();

        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    void testCreateProductPage() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/product/create")).andReturn().getResponse();

        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Sabun");
        product.setProductQuantity(10);

        Mockito.when(service.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);

        MockHttpServletResponse response = mvc.perform(get("/product/edit/eb558e9f-1c39-460e-8860-71af6af63bd6")).andReturn().getResponse();

        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    public void testDeleteProductPostNotFound() throws Exception {
        mvc.perform(delete("/product/delete/{id}", "eb558e9f-1c39-460e-8860-71af6af63bd6"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    public void testEditProductPost() throws Exception {
        Product product = new Product();
        product.setProductName("Sampo Cap Sabun");
        product.setProductQuantity(10);

        String json = jsonProduct.write(product).getJson();
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        MockHttpServletResponse response = mvc.perform(
                post("/product/edit")
                        .contentType("application/json")
                        .content(json != null ? json : "")
        ).andReturn().getResponse();

        assertEquals(HttpStatus.SC_MOVED_TEMPORARILY, response.getStatus());
    }

    @Test
    public void testCreateProductPost() throws Exception {
        Product product = new Product();
        product.setProductName("Sampo Cap Sabun");
        product.setProductQuantity(10);

        String json = jsonProduct.write(product).getJson();
        MockHttpServletResponse response = mvc.perform(
                post("/product/create")
                        .contentType("application/json")
                        .content(json != null ? json : "")
        ).andReturn().getResponse();
        assertEquals(HttpStatus.SC_MOVED_TEMPORARILY, response.getStatus());
    }

}
