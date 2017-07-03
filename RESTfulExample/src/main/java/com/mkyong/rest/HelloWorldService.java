package com.mkyong.rest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




 
@Path("/items")
public class HelloWorldService {
 
	@GET
	@Path("/{param}")
	public Response selectitem(@PathParam("param") String msg) {
 
	
 
		return Response.status(200).entity(Rest.SelectItem(msg).toString()).build();
 
	}
	
	@GET
	@Path("/all")
	public Response selectitems() {
		
	

		return Response.status(200).entity(Rest.SelectItem().toString()).build();
 
	}

	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response additem(String data) throws ParseException { 
    	JSONParser parser = new JSONParser();
    	JSONObject json = (JSONObject) parser.parse(data);
        String result = "Added item: "+data;
        Rest.AddItem(json);
        return Response.status(201).entity(result).build(); 
    }
    
    @PUT
    @Path("/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateitem(@PathParam("param") String msg, String data) throws ParseException { 
    	JSONParser parser = new JSONParser();
    	JSONObject json = (JSONObject) parser.parse(data);
        String result = "Updated item: "+data;
        Rest.UpdateItem(msg,json);
        return Response.status(201).entity(result).build(); 
        //sdasd
    }
    
    @DELETE
    @Path("/{param}")
    public Response deleteitem(@PathParam("param") String data) { 
  
        String result = "Deleted item: "+Rest.SelectItem(data);
        Rest.DeleteItem(data);
        return Response.status(201).entity(result).build(); 
    }
}