/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ClientModels.Ticket;
import ClientRestService.TicketGenerator;
import java.util.Date;
import org.junit.Test;

/**
 *
 * @author caragea
 */
public class populateTicket {
    
    @Test
    
    public void populateTicketXmlTest() {
        
        Ticket myTicket = new Ticket();
        myTicket.setFromStationName("Waterloo");
        myTicket.setToStationName("London Bridge");
        myTicket.setFromDate(new Date());
        myTicket.setToDate(new Date());
        
        String xml = TicketGenerator.generateTicket(myTicket);
//        Ticket toTest = TicketGenerator.generateTicket();
        
//        assert(myTicket.toString().equals(toTest.toString()));
    }
    
}
