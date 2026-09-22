package iuh.fit.nguyennamtrungnguyen_23640731_onlan2.service;

import iuh.fit.nguyennamtrungnguyen_23640731_onlan2.model.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/15/2026
 */

@Getter
@ApplicationScoped
public class CustomerList {

    private final List<Customer> customers = new ArrayList<>();

    public CustomerList() {
        customers.add(new Customer(1, null, "Nguyen Nam Trung Nguyen", "nguyen@gmail.com", "Dak Lak", new String[]{"Doc sach"}));
        customers.add(new Customer(2, null, "Nguyen Nam Trung Nguyen", "nguyen@gmail.com", "Dak Lak", new String[]{"Doc sach"}));
        customers.add(new Customer(3, null, "Nguyen Nam Trung Nguyen", "nguyen@gmail.com", "Dak Lak", new String[]{"Doc sach"}));
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }
}
