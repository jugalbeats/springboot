package com.example.jugalbeats.dao;

import com.example.jugalbeats.models.Calendar;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CalendarRepository extends CrudRepository<Calendar, Long>{

    List<Calendar> findByUserNameArtist(String userName);

}
