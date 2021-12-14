package br.com.cvc.api.services;

import br.com.cvc.api.entities.FinancialTransfer;

public interface CalculateService {

	Float calculate(FinancialTransfer transfer, Tax tax);
	
}
