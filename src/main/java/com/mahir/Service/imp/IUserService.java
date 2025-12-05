package com.mahir.Service.imp;

import java.util.Optional;

import com.mahir.Entity.User;

public interface IUserService {
public Optional<User> findByUsername(String username);
}
