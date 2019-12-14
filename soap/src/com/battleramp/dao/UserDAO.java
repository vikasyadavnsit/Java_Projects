package com.battleramp.dao;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface UserDAO {
   public void setUserData(String userName, String email ,String OAuthProvider , JSONObject data );
}
