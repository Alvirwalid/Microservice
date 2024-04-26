package com.lcwd.user.service.userService;
import com.lcwd.user.service.constant.BaseConstant;
import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.utils.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends BaseConstant {

    ResponseEntity<BaseResponse>  saveUser(User user);
    ResponseEntity<BaseResponse> getAllUser();
    ResponseEntity<BaseResponse>  getUser(String id);

    ResponseEntity<BaseResponse>  updateUser(User userId);
    ResponseEntity<BaseResponse>   deleteUser(String userId);


}
