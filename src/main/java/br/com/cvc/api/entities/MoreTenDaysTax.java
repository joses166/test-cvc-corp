package br.com.cvc.api.entities;

import java.util.Date;

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
		Date transferDate = transfer.getTransferDate();
		
		if (transferDate == Utils.calculateDate(20) && Utils.calculatingDate(transferDate, 20))
			return this.calculateService.calcular(transfer, new TwentyDaysTax());
		else if (transferDate == Utils.calculateDate(30) && Utils.calculatingDate(transferDate, 30))
			return this.calculateService.calcular(transfer, new ThirtyDaysTax());
		else if (transferDate == Utils.calculateDate(40) && Utils.calculatingDate(transferDate, 40))
			return this.calculateService.calcular(transfer, new FortyDaysTax());
		else if (transferDate.after(Utils.calculateDate(40)) && transfer.getTransferValue() > 100000)
			return this.calculateService.calcular(transfer, new MoreFortyDaysTax());
		else
			throw new RuntimeException("Invalid value");
	}

}
