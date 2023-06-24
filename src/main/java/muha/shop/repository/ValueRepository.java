package muha.shop.repository;

import muha.shop.entity.Option;
import muha.shop.entity.Value;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValueRepository extends JpaRepository<Value, Long> {
Value findValueByOptionAndProductId(Option option, long productId);

    Value findValuesByOptionAndProductId(Option option, Long productId);
}

