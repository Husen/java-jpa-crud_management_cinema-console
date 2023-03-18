package org.enigma.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "m_customer")
@NamedQueries({
        @NamedQuery(name = "find all customer", query = "select c from Customer c order by c.customerId desc"),
        @NamedQuery(name = "find by id customer", query = "select c from Customer c where c.customerId = :id")
})
public class Customer {
    @Id
    @SequenceGenerator(name="webuser_idwebuser_seq",
            sequenceName="webuser_idwebuser_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="webuser_idwebuser_seq")
    @Column(name = "id", updatable=false)
    private int customerId;

    @Column(name = "name", nullable = false)
    private String nameCustomer;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "customer")
    private List<Ticket> tickets;

    public Customer() {
    }

    // for create customer with constructor


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", nameCustomer='" + nameCustomer + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
