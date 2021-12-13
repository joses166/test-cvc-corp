package br.com.cvc.api.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cvc.api.dto.FinancialTransferDTO;
import br.com.cvc.api.entities.FinancialTransfer;
import br.com.cvc.api.responses.Response;
import br.com.cvc.api.services.FinancialTransferService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/financial")
public class FinancialTransferController {

	private final FinancialTransferService service;
	
	@PostMapping("scheduling")
	public ResponseEntity<Response<FinancialTransfer>> schedulingFinancial(@Valid @RequestBody FinancialTransferDTO dto) {
		Response<FinancialTransfer> response = new Response<>();
		FinancialTransfer savingSchedule = this.service.savingSchedule(dto);
		response.setData(savingSchedule);
		return ResponseEntity.ok(response);
	}
	
}
