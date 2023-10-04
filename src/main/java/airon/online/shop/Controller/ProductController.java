package airon.online.shop.Controller;

import airon.online.shop.Entity.Product;
import airon.online.shop.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dev")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PostMapping("/add-all-products")
    public ResponseEntity<List<Product>> addAllProducts(@RequestBody List<Product> products) {
        return productService.saveAllProducts(products);
    }

    @GetMapping("/get-product/{prodId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long prodId) {
        return productService.getProduct(prodId);
    }

    @GetMapping("/get-product-by-name/{prodName}")
    public ResponseEntity<Product> getProductByName(@PathVariable String prodName) {
        return productService.getProductByName(prodName);
    }

    @GetMapping("/get-all-products")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @PutMapping("/update-product")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete-product/{prodId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long prodId) {
        return productService.deleteProduct(prodId);
    }
}
