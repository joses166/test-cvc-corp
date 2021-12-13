package br.com.cvc.api.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

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

	@NotBlank(message = "Please inform a valid value for origin account.")
	@Size(min = 6, max = 6, message = "Inform six characters for account origin.")
	private String originAccount;
	
	@NotBlank(message = "Please inform a valid value for destiny account.")
	@Size(min = 6, max = 6, message = "Inform six characters for account destiny.")
	private String destinyAccount;

	@NotNull(message = "Please inform a value for transfer value.")
	@Positive(message = "Please inform a value greater than zero.")
	private Float transferValue;
	
	@NotNull(message = "Please inform a value for transfer date.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern= "dd/MM/yyyy", timezone = "America/Sao_Paulo", locale = "pt_BR")
	private Date transferDate;
	
}