package airon.online.shop.Repository;

import airon.online.shop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProdName(String prodName);

    Boolean existsByProdName(String prodName);

}
