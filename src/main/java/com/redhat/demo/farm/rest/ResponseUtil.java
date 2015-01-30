package com.redhat.demo.farm.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.demo.farm.model.OperationResponse;


public class ResponseUtil {
	private static final Logger LOG = LoggerFactory.getLogger(ResponseUtil.class);
	private static final String SUCCESS = "success";
	private static final String INFO = "info";
	private static final String WARNING = "warning";
	private static final String FAILURE = "danger";
	
	public static boolean failed(Response response) {
		if (response == null) {
			return false;
		}
		
		if (response.getEntity() == null) {
			return false;
		}
		
		if (!(response.getEntity() instanceof OperationResponse)) {
			return false;
		}
		
		OperationResponse operationResponse = (OperationResponse) response.getEntity();
		
		if (operationResponse.getStatus() == null) {
			return false;
		}
		
		return operationResponse.getStatus().equals(FAILURE);
	}
	
	public static Response ok(String status, String message) {
		OperationResponse operationResponse = new OperationResponse();
		operationResponse.setMessage(message);
		operationResponse.setStatus(status);
		return Response.ok(operationResponse).build();
	}
	
	public static Response error(Response.Status reponseStatus, String message) {
		OperationResponse operationResponse = new OperationResponse();
		operationResponse.setMessage(message);
		operationResponse.setStatus(FAILURE);
		return Response.status(reponseStatus).entity(operationResponse).build();
	}
	
	public static Response success(String message) {
		return ResponseUtil.ok(SUCCESS, message);
	}
	
	public static Response ok(Object object) {
		return Response.ok(object).build();
	}
	
	public static Response unauthorized() {
		return Response.status(Status.UNAUTHORIZED).build();
	}
}
