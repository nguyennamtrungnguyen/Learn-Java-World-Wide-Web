package iuh.fit.nguyennamtrungnguyen_23640731_onlan3.service;

import iuh.fit.nguyennamtrungnguyen_23640731_onlan3.model.Customer;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/15/2026
 */

@ApplicationScoped
public class CustomerList {

    private final List<Customer> customers = new ArrayList<>();
    public CustomerList(){
        customers.add(new Customer(1, "Nguyen", "nguyen@gmail.com", "Dak Lak"));
        customers.add(new Customer(2, "Nguyen", "nguyen@gmail.com", "Dak Lak"));
        customers.add(new Customer(3, "Nguyen", "nguyen@gmail.com", "Dak Lak"));
    }


    public List<Customer> getCustomers(){
        return customers;
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }
}
