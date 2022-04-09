package com.example.jugalbeats.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.jugalbeats.dao.FollowersRepo;
import com.example.jugalbeats.models.Followers;
import com.example.jugalbeats.models.FollowersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.ConnectionsRepository;
import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.enums.RequestStatus;
import com.example.jugalbeats.models.Connections;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.utils.Constants;
/*
 * dhruv : 2021
 * */
@Service
public class ConnectionsService {

	@Autowired
	ConnectionsRepository connectionRepository;

	@Autowired
	FollowersRepo followersRepo;

	@Autowired
	UsersDao usersDao;

	public ApiResponse postAddFriend(String sender, String receiver) {
		UsersModel usender = usersDao.findByUsername(sender);
		if (Objects.isNull(usender)) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Invalid Sender");
		}
		UsersModel ureceiver = usersDao.findByUsername(receiver);
		if (Objects.isNull(ureceiver)) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Invalid Receiver");
		}
		Connections request = connectionRepository.findBySenderAndReceiver(sender, receiver);
		if (!Objects.isNull(request)) {
			if (request.getStatus() == RequestStatus.PENDING.getType())
				return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE,
						"Request already exists in pendings");
			else if (request.getStatus() == RequestStatus.ACCEPTED.getType())
				return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Already friends");
			else {
				request.setStatus(RequestStatus.PENDING.getType());
				connectionRepository.save(request);
				return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Request sent successfully");
			}
		}
		request = new Connections();
		request.setSender(usender);
		request.setReceiver(ureceiver);
		request.setStatus(RequestStatus.PENDING.getType());
		connectionRepository.save(request);
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Request sent successfully");

	}

	public ApiResponse postAcceptRequest(String sender, String receiver) {

		Connections request = connectionRepository.findBySenderAndReceiverAndStatus(sender, receiver,
				RequestStatus.PENDING.getType());
		if (Objects.isNull(request)) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Please send request first");
		}

		if (request.getStatus() != RequestStatus.PENDING.getType())
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE,
					"Request is not in pending state");

		request.setStatus(RequestStatus.ACCEPTED.getType());
		connectionRepository.save(request);

		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Request accepted successfully");

	}

	public ApiResponse postRejectRequest(String sender, String receiver) {

		Connections request = connectionRepository.findBySenderAndReceiverAndStatus(sender, receiver,
				RequestStatus.PENDING.getType());
		if (Objects.isNull(request)) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Please send request first");
		}

		if (request.getStatus() != RequestStatus.PENDING.getType())
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE,
					"Request is not in pending state");

		request.setStatus(RequestStatus.REJECTED.getType());
		connectionRepository.save(request);
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Request rejected successfully");

	}

	public ApiResponse removeFriendRequest(String sender, String receiver) {

		Connections request = connectionRepository.findBySenderAndReceiverAndStatus(sender, receiver,
				RequestStatus.PENDING.getType());
		if (Objects.isNull(request)) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Please send request first");
		}

		if (request.getStatus() != RequestStatus.PENDING.getType())
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE,
					"Request is not in pending state");

		connectionRepository.deleteById(request.getId());
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Request rejected successfully");
	}

	public ApiResponse removeFriend(String sender, String receiver) {
		Connections request = connectionRepository.findBySenderAndReceiverBoth(sender, receiver);
		if (Objects.isNull(request)) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Please send request first");
		}
		if (request.getStatus() != RequestStatus.ACCEPTED.getType()) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Users are not friends");
		}

		connectionRepository.deleteById(request.getId());
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,
				"User are not friends anymore ! Success.");
	}

	public ApiResponse getPendingRecievedRequest(String username) {
		List<String> response=new ArrayList<>();
		List<Connections> friends = new ArrayList<>();
		friends = connectionRepository.findRequestByReceiverAndStatus(username, RequestStatus.PENDING.getType());
		if(!friends.isEmpty()) {
			response=mapToConnectionResponse(friends,username);
		}
		
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, response);
	}

	public ApiResponse getPendingSentRequest(String username) {
		List<String> response=new ArrayList<>();
		List<Connections> friends = new ArrayList<>();
		friends = connectionRepository.findRequestBySenderAndStatus(username, RequestStatus.PENDING.getType());
		if(!friends.isEmpty()) {
			response=mapToConnectionResponse(friends,username);
		}
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, response);
	}

	public ApiResponse getFriendsList(String username) {
//		List<String> response=new ArrayList<>();
//		List<Connections> friends = new ArrayList<>();
//		friends = connectionRepository.findRequestByUsernameAndStatus(username, RequestStatus.ACCEPTED.getType());
//		if(!friends.isEmpty()) {
//			response=mapToConnectionResponse(friends,username);
//		}
//		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, response);
		List<String> followersModelList = followersRepo.getFollowersList(username);
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, followersModelList);
	}
	
	
	public List<String> mapToConnectionResponse(List<Connections> friends,String username)
	{
		List<String> response=new ArrayList<>();
		friends.stream().forEach(users->{
			if(users.getSender().getUsername()==username)
			response.add(users.getReceiver().getUsername());
			else
				response.add(users.getSender().getUsername());
		});
		return response;
	}

	public ApiResponse saveFollower(String follower, String following){
		FollowersModel followersModel = new FollowersModel();
		Followers followers = new Followers(follower, following);
		followersModel.setFollowers(followers);
		followersModel.setStatus("OK");
		followersRepo.save(followersModel);
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
	}

}
