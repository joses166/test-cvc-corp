package br.com.cvc.api.services;

import java.util.List;

import br.com.cvc.api.dto.FinancialTransferDTO;
import br.com.cvc.api.entities.FinancialTransfer;

public interface FinancialTransferService {

	FinancialTransfer savingSchedule(FinancialTransferDTO dto);
	List<FinancialTransfer> listAllRecords();

}