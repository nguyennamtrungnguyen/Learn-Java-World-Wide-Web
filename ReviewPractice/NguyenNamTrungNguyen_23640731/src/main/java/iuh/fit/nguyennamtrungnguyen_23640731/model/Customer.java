package iuh.fit.nguyennamtrungnguyen_23640731.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/14/2026
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    private int id;
    private String name;
    private String email;
    private String address;
}
