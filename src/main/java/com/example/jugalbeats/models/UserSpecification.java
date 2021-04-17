package com.example.jugalbeats.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.example.jugalbeats.pojo.model.PriceInfo;
import com.example.jugalbeats.pojo.model.UserSongs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * dhruv
 * */
@Entity
@Table(name = "user_specification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSpecification extends BaseModel{

	
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "user_name_specification", referencedColumnName = "user_name")
	    private UsersModel usersModel;
	    
	    @Column(nullable = true, name = "price_list")
	    private String priceList;
	    
	    @Column(nullable = true, name = "song_list")
	    private String songList;
	    
		public List<PriceInfo>  getPriceList(){
			if(!StringUtils.isNotEmpty(priceList)) {
				return new ArrayList();
			}
			Gson gson =new Gson();
			return gson.fromJson(priceList, new TypeToken<ArrayList<PriceInfo>>(){}.getType());
			
		}
		
		public List<UserSongs>  getSongList(){
			if(!StringUtils.isNotEmpty(songList)) {
				return new ArrayList();
			}
			Gson gson =new Gson();
			return gson.fromJson(songList, new TypeToken<ArrayList<UserSongs>>(){}.getType());
			
		}
}
