package iuh.fit.eshopapi.response;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 10/30/2026
 */
import java.math.BigDecimal;

public record CategoryStatsResponse(
        Long categoryId,
        String categoryName,
        Long productCount,
        BigDecimal inventoryValue
) {}