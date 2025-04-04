package com.backend.ecommerce_backend.service;

import com.backend.ecommerce_backend.entity.User;
import com.backend.ecommerce_backend.exception.UserException;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;

}
