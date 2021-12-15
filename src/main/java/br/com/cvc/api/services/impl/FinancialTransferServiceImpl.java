package br.com.cvc.api.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cvc.api.dto.FinancialTransferDTO;
import br.com.cvc.api.entities.FinancialTransfer;
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
		
		financialTransfer.setDestinyAccount(dto.getDestinyAccount());
		financialTransfer.setOriginAccount(dto.getOriginAccount());
		financialTransfer.setTransferDate(dto.getTransferDate());
		financialTransfer.setTransferValue(dto.getTransferValue());
		financialTransfer.setSchedulingDate(new Date());
		financialTransfer.setTax(this.calculate(financialTransfer));

		return this.repository.save(financialTransfer);
	}

	@Override
	public List<FinancialTransfer> listAllRecords() {
		return this.repository.findAll();
	}

	private Float calculate(FinancialTransfer transfer) {
		Long transferDay = Utils.gettingDayToDate(transfer.getTransferDate());
		Long schedulingDay = Utils.gettingDayToDate(transfer.getSchedulingDate());
		
		try {
			if (transferDay < schedulingDay) 
				throw new RuntimeException("Please inform a valid date.");
			else if (transferDay.equals(schedulingDay))
				return this.calculateService.calculate(transfer, new TodayTax());
			else if (transferDay <= Utils.gettingDayToDate(10))
				return this.calculateService.calculate(transfer, new TenDaysTax());
			else if (transferDay > Utils.gettingDayToDate(10))
				return this.calculateService.calculate(transfer, new MoreTenDaysTax(new CalculateServiceImpl()));
			else
				throw new RuntimeException("The information is not valid.");
		} catch (Exception e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}
	
}