package com.example.jugalbeats.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jugalbeats.models.Connections;

public interface ConnectionsRepository  extends JpaRepository<Connections, Long>{

	@Query(value = "select DISTINCT u.* from  connections u where sender = :sender and receiver = :receiver  ", nativeQuery = true)
	public Connections findBySenderAndReceiver(@Param("sender")String sender, @Param("receiver")String receiver);
	
	@Query(value = "select DISTINCT u.* from  connections u where sender = :sender and receiver = :receiver and status = :status ", nativeQuery = true)
	public Connections findBySenderAndReceiverAndStatus(@Param("sender")String sender, @Param("receiver")String receiver, @Param("status")int status);
	
	
	@Query(value = "select DISTINCT u.* from  connections u where ((sender = :sender and receiver = :receiver) or (sender = :receiver and receiver = :sender)) ", nativeQuery = true)
	public Connections findBySenderAndReceiverBoth(@Param("sender")String sender, @Param("receiver")String receiver);

	
	@Query(value = "select DISTINCT u.* from  connections u where ((sender = :username and status = :status) or (receiver = :username and status = :status)) ", nativeQuery = true)
	public List<Connections> findRequestByUsernameAndStatus(@Param("username")String username,@Param("status") int status);
	
	@Query(value = "select DISTINCT u.* from  connections u where sender = :sender and status = :status", nativeQuery = true)
	public List<Connections> findRequestBySenderAndStatus(@Param("sender")String username,@Param("status") int status);
	
	@Query(value = "select DISTINCT u.* from  connections u where receiver = :receiver and status = :status", nativeQuery = true)
	public List<Connections> findRequestByReceiverAndStatus(@Param("receiver")String receiver,@Param("status") int status);
	
}

