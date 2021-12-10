package br.com.cvc.api.entities;

public class TwentyDaysTax implements Tax {

	@Override
	public Float calculatingTax(FinancialTransfer transfer) {
		return transfer.getTransferValue() * 8 / 100;
	}

}
