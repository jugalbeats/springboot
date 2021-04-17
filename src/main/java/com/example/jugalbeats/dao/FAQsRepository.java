package com.example.jugalbeats.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.jugalbeats.models.FAQs;

public interface FAQsRepository extends CrudRepository<FAQs, Long> {
	@Query(value = "select DISTINCT u.* from  faqs u where user_name_faqs = :username  ", nativeQuery = true)
	public List<FAQs> findByUserName(String username);

}
