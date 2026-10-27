package iuh.fit.eshopapi.request;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "Tên danh mục không được để trống")
        @Size(max = 150, message = "Tên danh mục tối đa 150 ký tự")
        String name,

        @Size(max = 500, message = "Mô tả tối đa 500 ký tự")
        String description
) {}

