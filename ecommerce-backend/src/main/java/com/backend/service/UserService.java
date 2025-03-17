package com.backend.service;

import com.backend.entity.User;
import com.backend.exception.UserException;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;

}
