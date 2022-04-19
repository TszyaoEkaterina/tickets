package ru.netology;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    Manager manager = new Manager();
    TicketByTravelTimeAscComparator comparator = new TicketByTravelTimeAscComparator();
    Ticket first = new Ticket(1, 2500, "DME", "EGO",120);
    Ticket second = new Ticket(2, 1000, "SVO", "TOF",75);
    Ticket third = new Ticket(3, 1000, "DME", "EGO",150);
    Ticket forth = new Ticket(4, 3000, "SVX", "VKO",200);
    Ticket fifth = new Ticket(5, 2000, "VKO", "SVX",200);
    Ticket sixth = new Ticket(6, 2000, "DME", "EGO",140);
    @BeforeEach
    void addTickets() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(fifth);
    }
    @Test
    void shouldThrowExceptionWhenDepartureAirportNotFound() {
        //manager.findAll("SVX","EGO");
        assertThrows(NotFoundException.class, () -> manager.findAll("SVX", "EGO",comparator));
    }

    @Test
    void shouldThrowExceptionWhenArrivalAirportNotFound() {
        //manager.findAll("DME","SVO");
        assertThrows(NotFoundException.class, () -> manager.findAll("DME", "SVO",comparator));
    }
    @Test
    void shouldFindAllWithOneSuitableTicket() {
        Ticket[] actual = manager.findAll("SVX","VKO",comparator);
        Ticket[] expected = {forth};
        assertArrayEquals(expected,actual);
    }
    @Test
    void shouldFindAllWith2SuitableTicketsInTravelTimeOrder() {
        Ticket[] actual = manager.findAll("DME","EGO",comparator);
        Ticket[] expected = {first,third};
        assertArrayEquals(expected,actual);
    }
    @Test
    void shouldFindAllWithMoreSuitableTicketsInTravelTimeOrder() {
        Ticket[] actual = manager.findAll("DME","EGO",comparator);
        Ticket[] expected = {first,third};
        assertArrayEquals(expected,actual);
    }
    @Test
    void shouldFindAllIgnoringCaseInCorrectOrder() {
        manager.add(sixth);
        Ticket[] actual = manager.findAll("dme","EgO",comparator);
        Ticket[] expected = {first,sixth,third};
        assertArrayEquals(expected,actual);
    }
}