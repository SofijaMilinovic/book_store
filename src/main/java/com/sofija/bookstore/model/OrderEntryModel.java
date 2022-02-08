package com.sofija.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_entries")
public class OrderEntryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    private OrderModel orderModel;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookModel bookModel;

    @Column(name = "quantity")
    private int quantity;

    public OrderEntryModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    public BookModel getBookModel() {
        return bookModel;
    }

    public void setBookModel(BookModel bookModel) {
        this.bookModel = bookModel;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntryModel that = (OrderEntryModel) o;
        return id == that.id && orderModel.equals(that.orderModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderModel);
    }
}
