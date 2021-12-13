package br.com.cvc.api.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinancialTransferDTO {

	@NotBlank(message = "Please inform a valid value for account origin.")
	@Size(min = 6, max = 6, message = "Inform six characters for account origin.")
	private String originAccount;
	
	@NotBlank(message = "Please inform a valid value for account destiny.")
	@Size(min = 6, max = 6, message = "Inform six characters for account destiny.")
	private String destinyAccount;
	
	private Float transferValue;
	
	@JsonFormat(pattern= "dd/MM/yyyy", timezone = "America/Sao_Paulo", locale = "pt_BR")
	private Date transferDate;
	
}