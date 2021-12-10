package br.com.cvc.api.entities;

public class ThirtyDaysTax implements Tax {

	@Override
	public Float calculatingTax(FinancialTransfer transfer) {
		return transfer.getTransferValue() * 6 / 100;
	}

}
