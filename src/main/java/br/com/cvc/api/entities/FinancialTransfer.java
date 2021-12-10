package br.com.cvc.api.entities;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinancialTransfer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String originAccount;
	private String destinyAccount;
	private Float transferValue;
	private Float tax;
	private Date transferDate;
	private Date schedulingDate;

//	Conta de origem (padrão XXXXXX)
//	Conta de desƟno (padrão XXXXXX)
//	Valor da transferência
//	Taxa (a ser calculada)
//	Data da transferência (data que será realizada a operação)
//	Data de agendamento (hoje)
	
}