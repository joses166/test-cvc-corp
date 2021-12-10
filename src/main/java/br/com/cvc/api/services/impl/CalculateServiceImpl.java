package br.com.cvc.api.services.impl;

import br.com.cvc.api.entities.FinancialTransfer;
import br.com.cvc.api.entities.Tax;
import br.com.cvc.api.services.CalculateService;

public class CalculateServiceImpl implements CalculateService {

	public Float calcular(FinancialTransfer transfer, Tax tax) {
		return tax.calculatingTax(transfer);
	}
	
}
