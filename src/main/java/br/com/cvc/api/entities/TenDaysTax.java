package br.com.cvc.api.entities;

import br.com.cvc.api.utils.Utils;

public class TenDaysTax implements Tax {

	@Override
	public Float calculatingTax(FinancialTransfer transfer) {
		Long transferDay = Utils.gettingDayToDate(transfer.getTransferDate());
		Long schedulingDay = Utils.gettingDayToDate(transfer.getSchedulingDate());
		Long days = transferDay - schedulingDay;
		
		return (float) 12 * days;
	}

}
