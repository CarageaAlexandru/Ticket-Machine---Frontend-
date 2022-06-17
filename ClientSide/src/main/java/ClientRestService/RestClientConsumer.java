
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientRestService;

import ClientModels.Station;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author caragea
 */
public class RestClientConsumer {
    
//    This will consume the end points from our back end
    
    public static List<Station> getStations() {
//        getting the stations from the model
        
//        First thing we need to consume a rest service we need a Client object from Jersey

        Client client = ClientBuilder.newBuilder().build();
//        Webtarget is used to execute the end point url.
                                          
        WebTarget target = client.target("http://localhost:8080/ManualWebService/resources/Facade.Stations");
        
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_XML);
        
        Response response = invocation.get();
        
//        get the data from response and convert it to Objects of station and return the list
//        to our jsp ( dropdown )
        
        List<Station> stationList;
        stationList = response.readEntity(new GenericType<List<Station>>(){});
        
        return stationList;
        
//        whoever call the method will get the stationList in return
        
    }
    
    public static void main(String[] aregs)
    {
        List<Station> stations = getStations();
        for(int i=0;i<stations.size();i++)
        {
            System.out.println(stations.get(i).getStationname());
        }
                
    }
    
}
