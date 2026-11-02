package iuh.fit.eshopapi.dto;

import java.math.BigDecimal;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 11/2/2026
 */

public record CategoryStats(
        Long categoryId,
        String categoryName,
        Long productCount,
        BigDecimal totalInventoryValue
) {
}