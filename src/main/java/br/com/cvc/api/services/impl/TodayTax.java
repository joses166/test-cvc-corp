package br.com.cvc.api.services.impl;

import br.com.cvc.api.entities.FinancialTransfer;
import br.com.cvc.api.services.Tax;

public class TodayTax implements Tax {

	@Override
	public Float calculatingTax(FinancialTransfer transfer) {
		return (((transfer.getTransferValue() * 3) / 100) + 3);
	}

}
