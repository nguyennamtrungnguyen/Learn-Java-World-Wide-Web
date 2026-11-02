package iuh.fit.dto;

import iuh.fit.model.Category;
import iuh.fit.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 11/3/2026
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProductDTO {
    private Category category;
    private List<Product> products;
}
