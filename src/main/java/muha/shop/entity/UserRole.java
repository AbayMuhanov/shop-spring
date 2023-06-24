package muha.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("admin", "Администратор"),
    USER("user", "Пользователь");

//    private final String serviceRole;
//    private final String role;

    private final String serviceName;
    private final String viewName;

//    UserRole(String serviceName, String viewName) {
//        this.serviceName = serviceName;
//        this.viewName = viewName;
//    }

    public String getServiceName() {
        return serviceName;
    }

    public String getViewName() {
        return viewName;
    }


}
