package com.example.ProjectEAD.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "connecter_profits")
public class ConnecterProfit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "profit_id")
    private Profit profit;

    @ManyToOne
    @JoinColumn(name = "connecter_id")
    private User connecter;

    public ConnecterProfit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Profit getProfit() {
        return profit;
    }

    public void setProfit(Profit profit) {
        this.profit = profit;
    }

    public User getConnecter() {
        return connecter;
    }

    public void setConnecter(User connecter) {
        this.connecter = connecter;
    }
}

