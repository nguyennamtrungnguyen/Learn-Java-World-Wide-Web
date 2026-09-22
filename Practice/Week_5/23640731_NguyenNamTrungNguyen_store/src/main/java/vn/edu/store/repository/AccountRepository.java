package vn.edu.store.repository;

import vn.edu.store.model.Account;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    List<Account> findAll();

    Optional<Account> findById(int id);

    boolean save(Account account);

    boolean update(Account account);

    boolean deleteById(int id);

    default Account mapResultSetToAccount(ResultSet rs) throws Exception {
        Account account = new Account();

        account.setId(
                rs.getInt("ID")
        );

        account.setFirstName(
                rs.getString("FIRSTNAME")
        );

        account.setLastName(
                rs.getString("LASTNAME")
        );

        account.setEmail(
                rs.getString("EMAIL")
        );

        account.setPassword(
                rs.getString("PASSWORD")
        );

        if (rs.getDate("DATEOFBIRTH") != null) {
            account.setDateOfBirth(
                    rs.getDate("DATEOFBIRTH").toLocalDate()
            );
        }

        return account;
    }
}
