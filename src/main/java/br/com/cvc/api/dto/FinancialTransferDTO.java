package br.com.cvc.api.dto;

import java.util.Date;

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

	private String originAccount;
	private String destinyAccount;
	private Float transferValue;
	@JsonFormat(pattern= "dd/MM/yyyy", timezone = "America/Sao_Paulo", locale = "pt_BR")
	private Date transferDate;
	
//	Conta de origem (padrão XXXXXX)
//	Conta de desƟno (padrão XXXXXX)
//	Valor da transferência
//	Taxa (a ser calculada)
//	Data da transferência (data que será realizada a operação)
//	Data de agendamento (hoje)
	
}