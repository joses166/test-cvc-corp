package br.com.cvc.api.services.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.cvc.api.dto.FinancialTransferDTO;
import br.com.cvc.api.entities.FinancialTransfer;
import br.com.cvc.api.entities.MoreTenDaysTax;
import br.com.cvc.api.entities.TenDaysTax;
import br.com.cvc.api.entities.TodayTax;
import br.com.cvc.api.repositories.FinancialTransferRepository;
import br.com.cvc.api.services.CalculateService;
import br.com.cvc.api.services.FinancialTransferService;
import br.com.cvc.api.utils.Utils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FinancialTransferServiceImpl implements FinancialTransferService {

	private final FinancialTransferRepository repository;
	private final CalculateService calculateService;
	
	@Override
	public FinancialTransfer savingSchedule(FinancialTransferDTO dto) {
		FinancialTransfer financialTransfer = new FinancialTransfer();
		financialTransfer.setSchedulingDate(new Date());
		financialTransfer.setTax(this.calculate(financialTransfer));
		return this.repository.save(financialTransfer);
	}

	private Float calculate(FinancialTransfer transfer) {
		try {
			if (transfer.getTransferDate().before(transfer.getSchedulingDate())) 
				throw new RuntimeException("Please inform a valid date.");
			else if (transfer.getTransferDate() == transfer.getSchedulingDate())
				return this.calculateService.calcular(transfer, new TodayTax());
			else if (transfer.getTransferDate() == Utils.calculateDate(10) && transfer.getTransferDate().before(Utils.calculateDate(10)))
				return this.calculateService.calcular(transfer, new TenDaysTax());
			else if (transfer.getTransferDate().after(Utils.calculateDate(10)))
				return this.calculateService.calcular(transfer, new MoreTenDaysTax(new CalculateServiceImpl()));
			else
				throw new RuntimeException("");
		} catch (Exception e) {
			throw new RuntimeException("");
		}
	}

}