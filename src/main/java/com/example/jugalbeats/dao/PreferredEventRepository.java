package com.example.jugalbeats.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jugalbeats.models.PreferredEvents;

public interface PreferredEventRepository extends JpaRepository<PreferredEvents, Long>{

}
