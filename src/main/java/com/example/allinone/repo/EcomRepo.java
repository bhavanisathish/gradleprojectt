package com.example.allinone.repo;

import com.example.allinone.model.EcomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface EcomRepo extends JpaRepository<EcomModel,Integer> {
    @Transactional
    @Modifying
    @Query(value = "update ecom set name = :name , email =:email,mobile=:mobile where user_id = :user_id and customer_id = :customer_id ",nativeQuery = true)
    int update(@Param("user_id") int userId,
               @Param("customer_id") int customerId,
               @Param("name") String names,
               @Param("email") String emailId,
               @Param("mobile") String mobileNo);

    @Transactional
    @Modifying
    @Query(value = "Insert into ecom (user_id,customer_id,name,mobile,email) values(:user_id,:customer_id,:name,:mobile,:email) ",nativeQuery = true)
    int save(@Param("user_id") int userId,
               @Param("customer_id") int customerId,
               @Param("name") String names,
               @Param("email") String emailId,
               @Param("mobile") String mobileNo);
}
