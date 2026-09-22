package vn.edu.store.service;

import vn.edu.store.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAllAccounts();

    Optional<Account> getAccountById(int id);

    boolean createAccount(Account account);

    boolean updateAccount(Account account);

    boolean deleteAccount(int id);
}
