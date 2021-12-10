package br.com.cvc.api.entities;

public class FortyDaysTax implements Tax {

	@Override
	public Float calculatingTax(FinancialTransfer transfer) {
		return transfer.getTransferValue() * 4 / 100;
	}

}
