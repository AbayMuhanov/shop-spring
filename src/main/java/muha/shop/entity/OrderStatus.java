package muha.shop.entity;

import lombok.Getter;

@Getter
public enum OrderStatus {
    AVAILABLE("Доступен"),
    SEND("Отправлен"),
    SOLD("Продан"),
    CANCELED("Отменен");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
