package org.enigma.model;

import jakarta.persistence.*;

@Entity
@Table(name = "trx_ticket")
@NamedQueries({
        @NamedQuery(name = "find all trx_ticket", query = "select trx from Ticket trx order by trx.ticketId desc"),
        @NamedQuery(name = "find by id trx_ticket", query = "select trx from Ticket trx where trx.ticketId = :id")
})
public class Ticket {
    @Id
    @SequenceGenerator(name="webuser_idwebuser_seq",
            sequenceName="webuser_idwebuser_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="webuser_idwebuser_seq")
    @Column(name = "id", updatable=false)
    private int ticketId;

    @ManyToOne()
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne()
    @MapsId("seatId")
    @JoinColumn(name = "seat_id")
    private Seat seat;

    public Ticket() {
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", customer=" + customer +
                ", seat=" + seat +
                '}';
    }
}
