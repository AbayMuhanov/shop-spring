package muha.shop.service;

import muha.shop.entity.Category;
import muha.shop.entity.Option;
import muha.shop.entity.Product;
import muha.shop.entity.Value;
import muha.shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private ValueRepository valueRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public void addProductList(Long categoryId, String productName, Integer productPrice,
                               List<Long> optionsId, List<String> values
    ) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();

        Product product = new Product();
        product.setCategory(category);
        product.setName(productName);
        product.setPrice(productPrice);
        productRepository.save(product);

        for (int i = 0; i < optionsId.size(); i++) {
            Option option = optionRepository.findById(optionsId.get(i)).orElseThrow();
            Value value = new Value();
            value.setProduct(product);
            value.setOption(option);
            value.setValue(values.get(i));
            valueRepository.save(value);
        }
    }

    public void updateProduct(Long productId, String productName, Integer productPrice,
                              List<Long> optionsId, List<String> values
    ) {
        Product product = productRepository.findById(productId).orElseThrow();
        if (productName != null & productPrice != null) {
            product.setName(productName);
            product.setPrice(productPrice);
            productRepository.save(product);
        }
        for (int i = 0; i < optionsId.size(); i++) {
            List<Option> options = optionRepository.findAllById(optionsId);
            Value value = valueRepository.findValuesByOptionAndProductId(options.get(i), productId);
            value.setProduct(product);
            value.setOption(options.get(i));
            value.setValue(values.get(i));
            valueRepository.save(value);
        }
    }

//    public List<Product> findProductsByCategoryId(Long categoryId) {
//        return categoryRepository.findById(categoryId).orElseThrow().getProducts();
//    }
//    public List<Product> findAllProducts() {
//        Sort sort = Sort.by(Sort.Order.by("category"));
//        return productRepository.findAll(sort);
//    }
//    public List<Category> findAllCategories() {
//        return categoryRepository.findAll();
//    }
//    public Category findCategory(Long categoryId) {
//        return categoryRepository.findById(categoryId).orElseThrow();
//    }
//    public Product findProduct(Long productId) {
//        return productRepository.findById(productId).orElseThrow();
//    }
//    public List<Value> findValuesByProductId(Long productId) {
//        return productRepository.findById(productId).orElseThrow().getValues();
//    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        valueRepository.deleteAll(product.getValues());
        feedbackRepository.deleteAll(product.getFeedbacks());
        cartItemRepository.deleteById(productId);
        productRepository.deleteById(productId);
    }

}
