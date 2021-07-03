/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.util;

import javax.ws.rs.core.Response;

/**
 *
 * @author hamizan
 */
public class ErrorHandler {
    
    public static Response throwResponse(boolean flag, String message) {
        if(flag){
            return Response.status(Response.Status.OK).entity(message).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
        }
    }
    
}
