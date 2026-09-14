package iuh.fit.nguyennamtrungnguyen_23640731.service;

import iuh.fit.nguyennamtrungnguyen_23640731.model.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.Part;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/14/2026
 */

@ApplicationScoped
public class CustomerList {
    private final List<Customer> customers = new ArrayList<>();

    public CustomerList(){
        customers.add(new Customer(1, null, "Nguyen Nam Trung Nguyen", "nguyennamtrungnguyen@gmail.com", "Dak lak", new String[]{"Đọc sách", "Nghe nhạc"}));
        customers.add(new Customer(2,null, "Bui Thi Kieu Trang", "buithikieutrang@gmail.com", "Dak lak", new String[]{"Đọc sách", "Nghe nhạc"}));
        customers.add(new Customer(3, null,"Nguyen Thanh Long", "nguyenthanhlong@gmail.com", "Binh Thuan", new String[]{"Đọc sách", "Nghe nhạc"}));
    }

    public List<Customer> getCustomers(){
        return customers;
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }
}
