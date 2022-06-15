

<%-- 
    Document   : home
    Created on : 6 Jun 2022, 12:09:09
    Author     : caragea
--%>

<%@page import="ClientRestService.TicketGenerator"%>
<%@page import="ClientModels.Ticket"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="ClientModels.Station"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket Dispenser</title>
    </head>
    <body>

        <form action="TicketDispenser.jsp" id="userForm">
            <label for="fromStation">From Station: </label>
            <!--Populating select dropdown-->
            <select name="fromStation" id="fromStation">
                <%            List<Station> stationListFrom = ClientRestService.RestClientConsumer.getStations();
                    for (int i = 0; i < stationListFrom.size(); i++) {
                        out.print("<option value=" + i + ">" + stationListFrom.get(i).getStationname() + "</option>");
                    }

                %>
            </select>
            <br>

            <label for="toStation">To Station: </label>
            <!--Populating toStation dropdown-->
            <select name="toStation" id="toStation">
                <%            List<Station> stationListTo = ClientRestService.RestClientConsumer.getStations();
                    for (int i = 0; i < stationListTo.size(); i++) {
                        out.print("<option value=" + i + ">" + stationListTo.get(i).getStationname() + "</option>");
                    }

                %>
            </select>
            <br>
            <label for="dateTo">Ticket Valid To</label>
            <input type="date" id="dateTo" name="dateTo">
            <br>
            <input type="submit" value="Generate Ticket">
        </form>
        <br>

        <%
            String xmlTicket = null;
            
            if (request.getParameter("fromStation") != null
                    && request.getParameter("toStation") != null
                    && request.getParameter("dateTo") != null) {

                int fromZone = Integer.parseInt(request.getParameter("fromStation"));
                int toZone = Integer.parseInt(request.getParameter("toStation"));
                
                //                                date formatter
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");            
                String dateTo = request.getParameter("dateTo");
                
                Date fromDate = new Date();
                Date parsedDateFrom = format.parse(dateTo);

                
                
                
                out.print("<h1>" + request.getParameter("fromStation") + "</h1>");
                out.print("<h1>" + request.getParameter("toStation") + "</h1>");
                out.print("<h1>" + request.getParameter("dateTo") + "</h1>");
                
                Ticket generatedTicket = new Ticket(stationListFrom.get(fromZone).getStationname(),
                                                    stationListTo.get(toZone).getStationname(),
                                                    String.valueOf(stationListFrom.get(fromZone).getZone()),
                                                    String.valueOf(stationListTo.get(toZone).getZone()),
                                                    false, false, parsedDateFrom, fromDate);
                xmlTicket = TicketGenerator.generateTicket(generatedTicket);
                
            }

        %>
        
        <br>
        <textarea rows="15" cols="130"><%out.print(xmlTicket);%></textarea>

    </body>

</html>