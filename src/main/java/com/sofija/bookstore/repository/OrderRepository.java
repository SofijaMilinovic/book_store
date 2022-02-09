package com.sofija.bookstore.repository;

import com.sofija.bookstore.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Integer> {

    @Query("SELECT o FROM OrderModel o WHERE o.userModel.id = ?1")
    List<OrderModel> findAllByUserId(int userId);
}
