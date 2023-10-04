package airon.online.shop.Service;

import airon.online.shop.Entity.Product;
import airon.online.shop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public ResponseEntity<Product> getProduct(Long prodId) {
        Product isExisting = repository.findById(prodId).orElse(null);
        if(repository.existsById(prodId)) {
            return ResponseEntity.status(HttpStatus.OK).body(isExisting);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(isExisting);
    }

    public ResponseEntity<Product> getProductByName(String prodName) {
        Product isExisting = repository.findByProdName(prodName).orElse(null);
        if(repository.existsByProdName(prodName)) {
            return ResponseEntity.status(HttpStatus.OK).body(isExisting);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(isExisting);
    }

    public ResponseEntity<Product> saveProduct(Product product) {
        Boolean isExisting = repository.existsByProdName(product.getProdName());
        if(isExisting) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(product);
        }
        repository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    public ResponseEntity<List<Product>> saveAllProducts(List<Product> products) {
        for(Product product: products) {
            Boolean isExisting = repository.existsByProdName(product.getProdName());
            if(isExisting) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(products);
            }
        }
        repository.saveAll(products);
        return ResponseEntity.status(HttpStatus.CREATED).body(products);
    }

    public ResponseEntity<String> deleteProduct(Long prodId) {
        if(repository.existsById(prodId)) {
            String productName = repository.findById(prodId).orElse(null).getProdName();
            String message = "Product " + productName + " with Product ID: " + prodId + " has been deleted!";
            repository.deleteById(prodId);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product ID doesn't exists");
    }

    public ResponseEntity<Product> updateProduct(Product product) {
        if(repository.existsById(product.getProdId())) {
            repository.save(product);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        //TODO: Use DTO for tomorrow
//        Product existingProduct = repository.findById(product.getProdId()).orElse(null);
//        existingProduct.setProdDescription(product.getProdDescription());
//        existingProduct.setProdName(product.getProdName());
//        existingProduct.setProdPrice(product.getProdPrice());
//        existingProduct.setProdQuantity(product.getProdQuantity());
//        existingProduct.setProdType(product.getProdType());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);

    }

}
