package com.module;

import org.springframework.stereotype.Repository;

import com.module.user.entity.User;

@Repository
public class LoginDao extends BaseDao<User, Long> {

}
