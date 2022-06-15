/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientRestService;

import ClientModels.Ticket;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author caragea
 */
public class TicketGenerator {
    public static String generateTicket(Ticket ticket) {
        
        String ex;
        try {
            JAXBContext context = JAXBContext.newInstance(Ticket.class);
            Marshaller marshall = context.createMarshaller();
            
//          // printing xml formatted
            marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter = new StringWriter();
            marshall.marshal(ticket, stringWriter);
            return stringWriter.toString();
            
        } catch (JAXBException error) {
            System.out.println(error);
            ex = error.toString();
        }
        
        return ex;
    }
    
}
