package com.codeacademy.diningreview.repositories;

import com.codeacademy.diningreview.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findByDisplayName(String displayName);

}
