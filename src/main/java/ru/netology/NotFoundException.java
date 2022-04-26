package ru.netology;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("Ticket with id: " + id + " not found.");
    }
    
    public NotFoundException(String from, String to) {
        super("Ticket from " + from + " to " + to + " not found.");
    }
}
