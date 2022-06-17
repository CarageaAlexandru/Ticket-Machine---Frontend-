/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientModels;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author caragea
 */

//JAXB

@XmlRootElement
public class Ticket {
    
    private String fromStationName;
    private String toStationName;
    private String fromZone,toZone;
    private boolean hasEntered, hasExited;
    private Date toDate, fromDate;

    public Ticket(String fromStationName, String toStationName,
            String fromZone, String toZone,
            boolean hasEntered, boolean hasExited,
            Date toDate, Date fromDate) {
        
        this.fromStationName = fromStationName;
        this.toStationName = toStationName;
        this.fromZone = fromZone;
        this.toZone = toZone;
        this.hasEntered = hasEntered;
        this.hasExited = hasExited;
        this.toDate = toDate;
        this.fromDate = fromDate;
    }

    public Ticket() {
    }

    public String getFromStationName() {
        return fromStationName;
    }

    public void setFromStationName(String fromStationName) {
        this.fromStationName = fromStationName;
    }

    public String getToStationName() {
        return toStationName;
    }

    public void setToStationName(String toStationName) {
        this.toStationName = toStationName;
    }

    public String getFromZone() {
        return fromZone;
    }

    public void setFromZone(String fromZone) {
        this.fromZone = fromZone;
    }

    public String getToZone() {
        return toZone;
    }

    public void setToZone(String toZone) {
        this.toZone = toZone;
    }

    public boolean isHasEntered() {
        return hasEntered;
    }

    public void setHasEntered(boolean hasEntered) {
        this.hasEntered = hasEntered;
    }

    public boolean isHasExited() {
        return hasExited;
    }

    public void setHasExited(boolean hasExited) {
        this.hasExited = hasExited;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public String generateTicket(Ticket myTicket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
