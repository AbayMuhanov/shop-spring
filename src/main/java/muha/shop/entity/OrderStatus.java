package muha.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum OrderStatus {

    AVAILABLE("Доступен"),
    SEND("Отправлен"),
    SOLD("Продан"),
    CANCELED("Отменен");

    private final String status;

}
