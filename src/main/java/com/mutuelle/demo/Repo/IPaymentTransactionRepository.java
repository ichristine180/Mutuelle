package com.mutuelle.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.Model.HealthFacility;
import com.mutuelle.demo.Model.PaymentTransaction;


/**
 * The interface Payment transaction repository.
 */
@Repository
public interface IPaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long>
{
    @Override
    List<PaymentTransaction> findAll();

    /**
     * Find by health facility list.
     *
     * @param healthFacility the health facility
     * @return the list
     */
    List<PaymentTransaction> findByHealthFacility(final HealthFacility healthFacility);
}
