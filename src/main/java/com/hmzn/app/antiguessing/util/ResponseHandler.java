/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.util;

import javax.ws.rs.core.Response;

/**
 * Response generator for service class
 * 
 * @author hamizan
 */
public class ResponseHandler {
    
    /**
     * Create response
     * @param flag true for success, false for fail
     * @param message to pass as entity in response
     * @return response 200 or 400
     */
    public static Response throwResponse(boolean flag, String message) {
        if(flag){
            return Response.status(Response.Status.OK).entity(message).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
        }
    }
    
}
