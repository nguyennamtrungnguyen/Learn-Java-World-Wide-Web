package iuh.fit.eshopapi.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity,
        String imageUrl,
        Integer categoryId,
        String categoryName,
        LocalDateTime createdAt
) {}
