package com.example.jugalbeats.dao;

import com.example.jugalbeats.models.Calendar;
import com.example.jugalbeats.models.UsersModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CalendarRepository extends CrudRepository<Calendar, Long>{

    List<Calendar> findByUserNameArtist(UsersModel usersModel);

    Calendar findByUserNameArtistAndDates(UsersModel usersModel, String dates);

    @Query(value = "DELETE FROM calendar where user_name_artist = ?1 and date = ?2", nativeQuery = true)
    void deleteByIdForDate(UsersModel usersModel, String date);

}
