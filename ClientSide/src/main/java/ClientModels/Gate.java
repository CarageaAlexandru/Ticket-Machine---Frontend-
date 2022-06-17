/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientModels;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author caragea
 */
public class Gate {

    private int zone;

    
//    Method for enter 
    public TicketValidator enter(String xml) {
//        JAXB code to Unmarshall

        JAXBContext context;
        
        TicketValidator validateTicket = new TicketValidator();

        try {
            context = JAXBContext.newInstance(Ticket.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            Ticket ticket = (Ticket) unmarshaller.unmarshal(reader);

            if (ticket.isHasEntered() == false && new Date().before(ticket.getToDate())) {
                ticket.setHasEntered(true);
                validateTicket.setIsValid(true);
            }

            try {
                context = JAXBContext.newInstance(Ticket.class);
                Marshaller marshall = context.createMarshaller();
                StringWriter writer = new StringWriter();
                marshall.marshal(ticket, writer);
                String re_marshalledXml = writer.toString();
                validateTicket.setXML(re_marshalledXml);
                return validateTicket;

            } catch (JAXBException error) {
                System.out.println(error);
            }

        } catch (JAXBException error) {
            System.out.println(error);
        }
        return null;

    }

//    Method for exit
    
    public boolean exit(String xml) {
//        JAXB to Marshall

        JAXBContext context;

        try {
            context = JAXBContext.newInstance(Ticket.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            Ticket ticket = (Ticket) unmarshaller.unmarshal(reader);

            if (ticket.isHasExited() == false &&
                    new Date().before(ticket.getToDate()) &&
                    Integer.parseInt(ticket.getToZone()) == zone) {
                ticket.setHasExited(true);
                return true;
            }

        } catch (Exception error) {
            System.out.println(error);
        }
        return false;

    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public Gate(int zone) {
        this.zone = zone;
    }

}
