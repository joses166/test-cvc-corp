package br.com.cvc.api.services;

import br.com.cvc.api.entities.FinancialTransfer;

public interface Tax {

	Float calculatingTax(FinancialTransfer transfer);
	
}
