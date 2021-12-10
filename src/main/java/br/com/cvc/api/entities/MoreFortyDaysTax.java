package br.com.cvc.api.entities;

public class MoreFortyDaysTax implements Tax {

	@Override
	public Float calculatingTax(FinancialTransfer transfer) {
		return transfer.getTransferValue() * 2 / 100;
	}

}
