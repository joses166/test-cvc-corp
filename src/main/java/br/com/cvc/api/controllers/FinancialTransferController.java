package br.com.cvc.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cvc.api.entities.FinancialTransfer;
import br.com.cvc.api.responses.Response;

@RestController
@RequestMapping("/api/financial")
public class FinancialTransferController {

	@PostMapping("scheduling")
	public ResponseEntity<Response<FinancialTransfer>> schedulingFinancial(@RequestBody FinancialTransfer transfer) {
		return ResponseEntity.ok(null);
	}
	
}
