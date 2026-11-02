package iuh.fit.repository;

import iuh.fit.dto.CategoryStats;
import iuh.fit.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 11/2/2026
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

    @Query("""
        SELECT new iuh.fit.dto.CategoryStats(
            c.id,
            c.name,
            COUNT(p),
            COALESCE(SUM(p.price * p.quantity), 0)
        )
        FROM Category c
        LEFT JOIN Product p ON p.category = c
        GROUP BY c.id, c.name
        ORDER BY c.name
    """)
    List<CategoryStats> getCategoryStats();
}
