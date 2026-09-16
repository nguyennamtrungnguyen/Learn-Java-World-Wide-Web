package iuh.fit.nguyennamtrungnguyen.repository;

import iuh.fit.nguyennamtrungnguyen.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/16/2026
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
