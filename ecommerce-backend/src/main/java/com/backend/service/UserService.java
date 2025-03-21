package com.backend.service;

import com.backend.entity.User;
import com.backend.exception.UserException;

public interface UserService {
    User findUserById(Long userId) throws UserException;
    User findUserProfileByJwt(String jwt) throws UserException;
}