package br.com.cvc.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cvc.api.entities.FinancialTransfer;

@Repository
public interface FinancialTransferRepository extends JpaRepository<FinancialTransfer, Long> {

}