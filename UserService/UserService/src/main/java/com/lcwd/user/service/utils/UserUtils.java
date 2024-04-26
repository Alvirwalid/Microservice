package com.lcwd.user.service.utils;

import com.lcwd.user.service.constant.BaseConstant;
import com.lcwd.user.service.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Data
@Component
@AllArgsConstructor
public class UserUtils implements BaseConstant {




    public  BaseResponse generateSuccessResponse(Object object , String ...message){
        BaseResponse res= new BaseResponse();

        res.setData(object);
        res.setStatus(true);

        System.out.println(message[0]);

        if(message.length >1 && message[0] !=null){
            res.setMessage(message[0]);
            res.setMessageBn(message[1]);
        }

        return  res;
    }

    public  BaseResponse generateErrorResponse(Exception e){
        System.out.println("generateErrorResponse");
        BaseResponse res= new BaseResponse();
        res.setStatus(false);
        String messageType = getMasseageType(e.getMessage());

        if(messageType.equals("uk") || messageType.equals("re")){
            res.setMessage(DATA_ALRADY_EXISTS_MESSAGE);
            res.setMessageBn(DATA_ALRADY_EXISTS_MESSAGE_BN);
        }else  if (messageType.equals("fk")){
            res.setMessage(CHILD_RECORD_FOUND);
            res.setMessageBn(CHILD_RECORD_FOUND_BN);

        }
        else {
            res.setMessage(e.getMessage());
        }

        return  res;


    }


    public  static ResponseEntity<String>getResponseEntity(String message, HttpStatus httpStatus){
        return new  ResponseEntity<String>("{\"message\":\""+message+"\"}",httpStatus);
    }


    private String getMasseageType(String message){
        if(message !=null && message.length() >55){
            return  message.substring(52,54);
        }
        return "";
    }

    public  static  String getUUID(){
        Date date=new Date();
        long time=date.getTime();
        return "BILL"+time;
    }

    private  boolean validatedProductMap(User user){


        if(!user.getName().isEmpty()){

            if(!user.getEmail().isEmpty() ){
                return  true;
            }else return false;
        }
        return  false;
    }


}
