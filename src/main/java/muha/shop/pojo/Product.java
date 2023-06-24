package muha.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {

    private final String category;

    private final String name;

    private final int price;
}

