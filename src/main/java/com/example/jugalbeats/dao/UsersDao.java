package com.example.jugalbeats.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.jugalbeats.models.UsersModel;

public interface UsersDao extends CrudRepository<UsersModel, Long> {

     UsersModel findByEmail(String email);
    
     UsersModel getUsersDaoByEmailAndPassword(String email,String password);
    
     UsersModel getUsersDaoByUsernameAndPassword(String username, String password);

     UsersModel findByUsername(String userName);
     Page<UsersModel> findByCustomerType(String customerType, Pageable pageable);
    

}
