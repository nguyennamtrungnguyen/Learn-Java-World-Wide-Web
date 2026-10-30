package iuh.fit.eshopapi.exception;
import iuh.fit.eshopapi.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse err(
            HttpStatus status,
            String message,
            HttpServletRequest request,
            Map<String, String> errors
    ) {
        return new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI(),
                errors
        );
    }

    // 404 - Không tìm thấy dữ liệu
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(
            NotFoundException e,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(404)
                .body(err(
                        HttpStatus.NOT_FOUND,
                        e.getMessage(),
                        request,
                        null
                ));
    }

    // 409 - Xung đột dữ liệu
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> conflict(
            ConflictException e,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(409)
                .body(err(
                        HttpStatus.CONFLICT,
                        e.getMessage(),
                        request,
                        null
                ));
    }

    // 400 - Lỗi Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validation(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new LinkedHashMap<>();

        e.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return ResponseEntity.badRequest()
                .body(err(
                        HttpStatus.BAD_REQUEST,
                        "Dữ liệu không hợp lệ",
                        request,
                        errors
                ));
    }

    // 400 - JSON sai định dạng hoặc sai kiểu dữ liệu
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> unreadable(
            HttpMessageNotReadableException e,
            HttpServletRequest request
    ) {
        return ResponseEntity.badRequest()
                .body(err(
                        HttpStatus.BAD_REQUEST,
                        "JSON không hợp lệ hoặc sai kiểu dữ liệu",
                        request,
                        null
                ));
    }

    // 400 - Sai kiểu tham số URL
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> mismatch(
            MethodArgumentTypeMismatchException e,
            HttpServletRequest request
    ) {
        return ResponseEntity.badRequest()
                .body(err(
                        HttpStatus.BAD_REQUEST,
                        "Tham số không đúng kiểu: " + e.getName(),
                        request,
                        null
                ));
    }

    // 409 - Vi phạm ràng buộc database
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> integrity(
            DataIntegrityViolationException e,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(409)
                .body(err(
                        HttpStatus.CONFLICT,
                        "Dữ liệu vi phạm ràng buộc trong cơ sở dữ liệu",
                        request,
                        null
                ));
    }

    // 400 - Tham số nghiệp vụ không hợp lệ
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> bad(
            IllegalArgumentException e,
            HttpServletRequest request
    ) {
        return ResponseEntity.badRequest()
                .body(err(
                        HttpStatus.BAD_REQUEST,
                        e.getMessage(),
                        request,
                        null
                ));
    }

    // 500 - Lỗi hệ thống
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> other(
            Exception e,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(500)
                .body(err(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Lỗi máy chủ nội bộ",
                        request,
                        null
                ));
    }
}

