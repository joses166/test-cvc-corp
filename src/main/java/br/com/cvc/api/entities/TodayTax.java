package br.com.cvc.api.entities;

public class TodayTax implements Tax {

	@Override
	public Float calculatingTax(FinancialTransfer transfer) {
		return (((transfer.getTransferValue() * 3) / 100) + 3);
	}

}
