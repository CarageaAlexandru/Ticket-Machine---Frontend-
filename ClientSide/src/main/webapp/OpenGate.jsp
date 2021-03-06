<%-- 
    Document   : OpenGate
    Created on : 14 Jun 2022, 16:41:51
    Author     : caragea
--%>

<%@page import="ClientModels.Station"%>
<%@page import="ClientModels.TicketValidator"%>
<%@page import="java.io.StringReader"%>
<%@page import="javax.xml.bind.Unmarshaller"%>
<%@page import="javax.xml.bind.JAXBContext"%>
<%@page import="ClientModels.Ticket"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="ClientModels.Gate" %>
<%@page import="java.util.Date"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>







<%
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    Date currentTime = new Date();
    System.out.println(formatter.format(currentTime));

//    out.print("<h1>" + formatter.format(currentTime) + "</h1>");

    String ticktToUnmarshall = request.getParameter("ticktToUnmarshall");

    String travelledZones = request.getParameter("travelledZones");
    if (travelledZones == null || travelledZones.isEmpty()) {
        travelledZones = "2";
    }

    boolean openGate = false;

//    out.print("<h1>" + ticktToUnmarshall + "</h1>");


%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Open Gate</title>
    </head>
    <body>
        <h1>Enter ticket to open the gate</h1>



        <form action="OpenGate.jsp" method="post">
            <label>Current Time: </label>
            <input type="text" name="currentTime" value="<%= formatter.format(currentTime)%>">

            <br>

            <label>Travelled Zones: </label>
            <input type="text" name="travelledZones" value="<%= travelledZones%>">

            <br>

            <label>Enter ticket here: </label>
            <textarea name="ticktToUnmarshall" rows="15" cols="130"></textarea>

            <br>

            <button type="submit"> Open the Gate </button>
        </form>

        <%
            String ticketUnmarshall = null;
            Gate new_gate = null;
            Station station = new Station();
            //TicketValidator validator = new TicketValidator();

            if (request.getParameter("ticktToUnmarshall") != null) {
                ticketUnmarshall = request.getParameter("ticktToUnmarshall");

                JAXBContext context = JAXBContext.newInstance(Ticket.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                StringReader reader = new StringReader(ticketUnmarshall);
                Ticket ticketUnmarshalled = (Ticket) unmarshaller.unmarshal(reader);
                    

                out.print(ticketUnmarshalled.getToStationName());
                new_gate = new Gate(Integer.parseInt(ticketUnmarshalled.getFromZone()));
                
//                get the station name, station id and zone
                //String stationName = ticketUnmarshalled.getFromStationName();
                TicketValidator validation = new_gate.enter(ticketUnmarshall);
                
                if (validation.isIsValid()) {
                    openGate = true;
                }
            }

            

        %>
        <br>
        <% if (openGate) { %>
        <div style="color:green;">Gate has been opened</div>
        <%  } else {  %>
        <div style="color:red;">Gate is locked</div>
        <% }%>


    </body>
</html>
