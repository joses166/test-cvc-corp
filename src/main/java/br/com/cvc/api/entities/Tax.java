package br.com.cvc.api.entities;

public interface Tax {

	Float calculatingTax(FinancialTransfer transfer);
	
}
