package com.b2b.ecommerce.repository;
import com.b2b.ecommerce.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.mobileNumber = :mobileNumber")
    Account findByMobileNumber(@Param("mobileNumber") String mobileNumber);
}
