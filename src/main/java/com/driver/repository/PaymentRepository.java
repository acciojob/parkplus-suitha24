package com.driver.repository;

import com.driver.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

//    @Query(value = "select * from payment where id=:id",nativeQuery = true)
//    Payment findPaymentById(int id);
}
