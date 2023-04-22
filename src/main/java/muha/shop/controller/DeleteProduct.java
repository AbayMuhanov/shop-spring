package muha.shop.controller;

import muha.shop.entity.Product;
import muha.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "delete")
public class DeleteProduct {
    @Autowired
    private ProductRepository productRepository;

    // Удаление товаара
    @GetMapping(path = "/delete_product")
    public String deleteProduct(@RequestParam long productId) {
        productRepository.deleteById(productId);
        return "Товар удалён успешно";

    }
}
