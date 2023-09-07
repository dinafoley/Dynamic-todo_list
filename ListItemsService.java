package kz.edu.nu.cs.se.hw;


import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/items")
public class ListItemsService {
    
    private List<String> list = new CopyOnWriteArrayList<String>();
    
    public ListItemsService() {
        for (int i = 0; i < 20; i++) {
            list.add("Entry " + String.valueOf(i));
        }
        
        Collections.reverse(list);
    }
    
    @GET
    public Response getList() {
        Gson gson = new Gson();
        
        return Response.ok(gson.toJson(list)).build();
    }
    
    @GET
    @Path("{id: [0-9]+}")
    public Response getListItem(@PathParam("id") String id) {
        int i = Integer.parseInt(id);
        
        return Response.ok(list.get(i)).build();
    }
    
    @POST
    public Response postListItem(@FormParam("newEntry") String entry) {
    	
    	Date timestamp = new Date();
    	list.add(0, entry + " Created at:" + timestamp);
        	
    	return Response.ok().build();
    	 
    }
    @POST
    @Path("/delete/{id: [0-9]+}")
    public Response postListDelete(@PathParam("id") String id) {
    	int i = Integer.parseInt(id);
        list.remove(i);
        return Response.ok().build();
    }
    @POST
    @Path("/clear")
    public Response postListClear() {
        list.clear();
        
        return Response.ok().build();
    }
}
