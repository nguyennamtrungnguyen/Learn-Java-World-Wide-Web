package vn.edu.store.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import vn.edu.store.model.Account;
import vn.edu.store.repository.AccountRepository;
import vn.edu.store.service.AccountService;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AccountServiceImpl implements AccountService {

    @Inject
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public boolean createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public boolean updateAccount(Account account) {
        return accountRepository.update(account);
    }

    @Override
    public boolean deleteAccount(int id) {
        return accountRepository.deleteById(id);
    }
}
