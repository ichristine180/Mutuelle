package com.mutuelle.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.model.HealthFacility;
import com.mutuelle.demo.model.PaymentLog;


/**
 * The interface Payment log repository.
 */
@Repository
public interface IPaymentLogRepository extends JpaRepository<PaymentLog, Long>
{
    @Override
    List<PaymentLog> findAll();

    /**
     * Find by health facility payment log.
     *
     * @param healthFacility the health facility
     * @return the payment log
     */
    PaymentLog findByHealthFacility(final HealthFacility healthFacility);
}
