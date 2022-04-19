package ru.netology;

import java.util.Arrays;
import java.util.Comparator;

import static java.util.Arrays.*;

public class Manager {
    Repository repo = new Repository();
    TicketByTravelTimeAscComparator comparator = new TicketByTravelTimeAscComparator();
    public Manager() {
    }

    public void add(Ticket newTicket) {
        repo.save(newTicket);
    }

    public void removeById(int id) {
        repo.removeById(id);
    }

    public Ticket[] findAll(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] tickets = searchBy(from, to);
        if (tickets == null){throw new NotFoundException(from,to);}
        Arrays.sort(tickets,comparator);
        return tickets;
    }

    public Ticket[] searchBy(String from, String to) {
        Ticket[] tmpResults = new Ticket[0];
        for (Ticket ticket : repo.findAll()) {
            if (ticket.getDepartureAirport().equalsIgnoreCase(from)) {
                Ticket[] tmp = new Ticket[tmpResults.length + 1];
                System.arraycopy(tmpResults, 0, tmp, 0, tmpResults.length);
                tmp[tmp.length - 1] = ticket;
                tmpResults = tmp;
            }
        }
        if (tmpResults.length == 0) {
            return null;
        }
        Ticket[] finalResults = new Ticket[0];
        for (Ticket ticket : tmpResults) {
            if (ticket.getArrivalAirport().equalsIgnoreCase(to)) {
                Ticket[] tmp = new Ticket[finalResults.length + 1];
                System.arraycopy(finalResults, 0, tmp, 0, finalResults.length);
                tmp[tmp.length - 1] = ticket;
                finalResults = tmp;
            }
        }
        if (finalResults.length == 0) {
            return null;
        }
        return finalResults;
    }
}