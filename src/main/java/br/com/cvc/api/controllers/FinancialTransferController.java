package br.com.cvc.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cvc.api.dto.FinancialTransferDTO;
import br.com.cvc.api.entities.FinancialTransfer;
import br.com.cvc.api.responses.Response;
import br.com.cvc.api.services.FinancialTransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/financial")
@Api("Financial Transfer API")
public class FinancialTransferController {

	private final FinancialTransferService service;

	@ApiOperation("Endpoint to save an financial transfer.")
	@PostMapping("scheduling")
	public ResponseEntity<Response<FinancialTransfer>> schedulingFinancial(@RequestBody @Valid FinancialTransferDTO dto,
			BindingResult result) {
		Response<FinancialTransfer> response = new Response<>();
		try {
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}

			FinancialTransfer savingSchedule = this.service.savingSchedule(dto);
			response.setData(savingSchedule);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiOperation("Endpoint to list all scheduled financial transfers.")
	@GetMapping
	public ResponseEntity<Response<List<FinancialTransfer>>> listAllRecords() {
		Response<List<FinancialTransfer>> response = new Response<>();
		try {
			List<FinancialTransfer> list = this.service.listAllRecords();
			response.setData(list);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

}
