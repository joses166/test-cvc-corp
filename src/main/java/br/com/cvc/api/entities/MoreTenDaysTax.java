package br.com.cvc.api.entities;

import br.com.cvc.api.services.CalculateService;
import br.com.cvc.api.utils.Utils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MoreTenDaysTax implements Tax {
	
	private final CalculateService calculateService;
	
	@Override
	public Float calculatingTax(FinancialTransfer transfer) {
		return calcularDataTransferencia(transfer);
	}
	
	private Float calcularDataTransferencia(FinancialTransfer transfer) {
		Long transferDay = Utils.gettingDayToDate(transfer.getTransferDate());
		
		if (transferDay <= Utils.gettingDayToDate(20))
			return this.calculateService.calcular(transfer, new TwentyDaysTax());
		else if (transferDay <= Utils.gettingDayToDate(30))
			return this.calculateService.calcular(transfer, new ThirtyDaysTax());
		else if (transferDay <= Utils.gettingDayToDate(40))
			return this.calculateService.calcular(transfer, new FortyDaysTax());
		else if (transferDay > Utils.gettingDayToDate(40) && transfer.getTransferValue() > 100000)
			return this.calculateService.calcular(transfer, new MoreFortyDaysTax());
		else
			throw new RuntimeException("Invalid value");
	}

}
