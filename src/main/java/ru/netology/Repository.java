package ru.netology;

public class Repository {
    private Ticket[] tickets = new Ticket[0];

    public void removeById(int id) {
        if (findById(id) == null){
            throw new NotFoundException(id);
        }
        Ticket[] tmp = new Ticket [tickets.length - 1];
        int index = 0;
        for (Ticket product:tickets) {
            if (product.getId() != id) {
                tmp[index] =product;
                index++;
            }
        }
        tickets= tmp;
    }
    public Ticket findById(int id){
        for (Ticket ticket: tickets){
            if (ticket.getId() == id){
                return ticket;
            }
        }
        return null;
    }
    public void save(Ticket newTicket) {
        if (findById(newTicket.getId()) != null){
           throw new AlreadyExistsException(newTicket.getId());
        }
        Ticket[] tmp = new Ticket[tickets.length + 1];
        System.arraycopy(tickets,0,tmp,0,tickets.length);
        tmp[tmp.length - 1] = newTicket;
        this.tickets = tmp;
    }
    public Ticket[] findAll() {
        return tickets;
    }
}

