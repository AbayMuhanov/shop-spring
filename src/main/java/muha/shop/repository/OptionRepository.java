package muha.shop.repository;

import muha.shop.entity.Option;
import muha.shop.entity.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {

}
