package muha.shop.service;

import muha.shop.entity.Category;
import muha.shop.entity.Option;
import muha.shop.entity.Product;
import muha.shop.entity.Value;
import muha.shop.repository.CategoryRepository;
import muha.shop.repository.OptionRepository;
import muha.shop.repository.ProductRepository;
import muha.shop.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    public void addProductList(Long categoryId, String productName, Integer productPrice
//                               List<Long> optionsId, List<String> valuesName
    ) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();

        Product product = new Product();
        product.setCategory(category);
        product.setName(productName);
        product.setPrice(productPrice);
        productRepository.save(product);

//        for (int i = 0; i < optionsId.size(); i++) {
//            Option option = optionRepository.findById(optionsId.get(i)).orElseThrow();
//            Value value = new Value();
//            value.setProduct(product);
//            value.setOption(option);
//            value.setValue(valuesName.get(i));
//            valueRepository.save(value);
//        }
    }

    public void updateProduct(Long productId, String productName, Integer productPrice
//                              List<Long> optionsId, List<String> valuesName
    ) {
        Product product = productRepository.findById(productId).orElseThrow();

        if (productName != null & productPrice != null) {
            product.setName(productName);
            product.setPrice(productPrice);
            productRepository.save(product);
        }
//        for (int i = 0; i < optionsId.size(); i++) {
//            List<Option> options = optionRepository.findAllById(optionsId);
//            Value value = valueRepository.findValuesByOptionAndProductId(options.get(i), productId);
//            value.setProduct(product);
//            value.setOption(options.get(i));
//            value.setValue(values.get(i));
//            valueRepository.save(value);
//        }
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

}
