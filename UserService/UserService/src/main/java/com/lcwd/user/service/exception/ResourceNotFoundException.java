package com.lcwd.user.service.exception;

import javax.management.relation.RelationNotFoundException;

public class ResourceNotFoundException extends RelationNotFoundException {

    public  ResourceNotFoundException(){
        super("Resource not found on servier !!");
    }
public  ResourceNotFoundException(String message){
    super(message);
}

}
