package muha.shop.repository;

import jakarta.transaction.Transactional;
import muha.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBefore(int value);

    List<Product> findAllByPriceAfter(int value);

    List<Product> findAllByPriceBetween(int from, int to);

    List<Product> findAllByCategoryName(String categoryName);

    @Query("select p from Product p where p.category.name = ?1 and p.price between ?2 and ?3")
    List<Product> findAllByCategoryAndPrice(String categoryName, int from, int to);

    @Modifying
    @Transactional
    @Query("update Product p set p.price = p.price + p.price * ?1 /100 where p.category.id = ?2")
    void updateProductPriceByCategory(int percent, long categoryId);

    // select p from Product p where p.category.id = ?1
    List<Product> findAllByCategoryId(long categoryId);

}
