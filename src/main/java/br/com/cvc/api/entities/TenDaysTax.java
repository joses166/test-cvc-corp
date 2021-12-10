package br.com.cvc.api.entities;

import br.com.cvc.api.utils.Utils;

public class TenDaysTax implements Tax {

	@Override
	public Float calculatingTax(FinancialTransfer transfer) {
		int days = (int) ((Utils.calculateDate(10).getTime() - transfer.getTransferDate().getTime())
				/ (1000 * 60 * 60 * 24));
		return (float) 12 * days;
	}

}
