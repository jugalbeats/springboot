package com.example.jugalbeats.dao;

import com.example.jugalbeats.models.PreferncesModel;
import com.example.jugalbeats.models.UsersModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PreferencesDao extends CrudRepository<PreferncesModel, Long> {

    public PreferncesModel findByUsersModel(UsersModel model);


}
