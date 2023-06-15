package muha.shop.controllerPractice;

import jakarta.persistence.EntityManager;
import muha.shop.entity.Category;
import muha.shop.entity.Product;
import muha.shop.repository.CategoryRepository;
import muha.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/data_controller")
public class DataController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;


    @GetMapping(path = "/first_resource")
    public Category firstResource() {
        Category category = categoryRepository.findById(2L).orElseThrow();
        return category;
    }


    @GetMapping(path = "/second_resource")
    public List<String> secondResource() {
        Sort sort = Sort.by(
                Sort.Order.asc("price"));
        List<Product> products = productRepository.findAll(sort);

        return mapProductToString(products);
    }

    private static final int PAGE_SIZE = 2;

    @GetMapping(path = "/third_resource")
    public List<String> thirdResource(
            @RequestParam(required = false) Integer page
    ) {
        Sort sort = Sort.by(
                Sort.Order.asc("price"));


        if (page == null) {
            return mapProductToString(productRepository.findAll(sort));
        }

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE, sort);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> products = productPage.getContent();

        return mapProductToString(products);

    }

    @GetMapping(path = "/fourth_resource")
    public List<String> fourthResource() {
        List<Product> products = productRepository.findAllByCategoryAndPrice("Смартфоны",100_000, 200_000);
        return mapProductToString(products);
    }

    //--------------------------------------------------------------------
//Подсказка по содание товара и изменение
    @GetMapping(path = "/create_new_entity")
    public void createNewEntity() {
        Category category = new Category();
        category.setName("Монитор");
        categoryRepository.save(category);
    }

    @GetMapping(path = "update_entity")
    public void updateNewEntity(
            @RequestParam long categoryId,
            @RequestParam String categoryName
    ) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        category.setName(categoryName);
        categoryRepository.save(category);
    }

    @GetMapping(path = "/delete_entity")
    public void deleteEntity(
            @RequestParam long categoryId
    ) {
        categoryRepository.deleteById(categoryId);
    }

    @GetMapping(path = "/increase_product_price")
    public void increaseProductPrice(
            @RequestParam int percent,
            @RequestParam long categoryId
    ) {
        productRepository.updateProductPriceByCategory(percent, categoryId);
    }

    private List<String> mapProductToString(List<Product> products) {
        return products
                .stream()
                .map(product -> product.getCategory().getName() + ":" + " (" + product.getName() + ")"
                        + " PRICE:(" + product.getPrice() + ")")
                .toList();
    }
}
