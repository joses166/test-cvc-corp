package br.com.cvc.api.services;

import br.com.cvc.api.entities.FinancialTransfer;
import br.com.cvc.api.entities.Tax;

public interface CalculateService {

	Float calcular(FinancialTransfer transfer, Tax tax);
	
}
