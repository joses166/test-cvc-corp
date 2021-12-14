package br.com.cvc.api.services.impl;

import org.springframework.stereotype.Service;

import br.com.cvc.api.entities.FinancialTransfer;
import br.com.cvc.api.services.CalculateService;
import br.com.cvc.api.services.Tax;

@Service
public class CalculateServiceImpl implements CalculateService {

	public Float calculate(FinancialTransfer transfer, Tax tax) {
		return tax.calculatingTax(transfer);
	}
	
}
