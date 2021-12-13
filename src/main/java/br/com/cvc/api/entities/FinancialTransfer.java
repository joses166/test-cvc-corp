package br.com.cvc.api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_financial_transfer")
public class FinancialTransfer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "origin_account")
	private String originAccount;
	
	@Column(name = "destiny_account")
	private String destinyAccount;
	
	@Column(name = "transfer_value")
	private Float transferValue;
	
	@Column(name = "tax")
	private Float tax;
	
	@Column(name = "transfer_date")
	private Date transferDate;
	
	@Column(name = "scheduling_date")
	private Date schedulingDate;

}