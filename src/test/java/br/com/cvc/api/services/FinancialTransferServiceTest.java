package br.com.cvc.api.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.cvc.api.dto.FinancialTransferDTO;
import br.com.cvc.api.entities.FinancialTransfer;
import br.com.cvc.api.repositories.FinancialTransferRepository;
import br.com.cvc.api.services.impl.FinancialTransferServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class FinancialTransferServiceTest {

	FinancialTransferService service;

    @MockBean
	FinancialTransferRepository repository;

    @MockBean
	CalculateService calculateService;
    
    @BeforeEach
    public void setUp() {
        this.service = new FinancialTransferServiceImpl(repository, calculateService);
    }

    @Test
    @DisplayName("In this test, i can create a financial transfer.")
    public void createAFinancialTransfer() {
        // Cenário
    	FinancialTransferDTO transfer = FinancialTransferDTO
				.builder()
				.destinyAccount("090909")
				.originAccount("090901")
				.transferDate(new Date())
				.transferValue(1500.0f)
				.build();
    	FinancialTransfer savedTransfer = FinancialTransfer
				.builder()
				.id(1L)
				.destinyAccount("090909")
				.originAccount("090901")
				.schedulingDate(new Date())
				.tax(10.0f)
				.transferDate(new Date())
				.transferValue(1500.0f)
				.build();
    
        Mockito.when( repository.save( Mockito.any() ) ).thenReturn( savedTransfer );
        Mockito.when( calculateService.calculate( Mockito.any(), Mockito.any() ) ).thenReturn( savedTransfer.getTax() );
        
        // Execução
        savedTransfer = this.service.savingSchedule( transfer );
        // Verificação
        assertThat(savedTransfer.getId()).isNotNull();
        assertThat(savedTransfer.getDestinyAccount()).isEqualTo(transfer.getDestinyAccount());
    }
    
    @Test
    @DisplayName("In this test, i have an error to create a financial transfer.")
    public void errorWhenCreateAFinancialTransfer() {
        // Cenário
    	FinancialTransferDTO transfer = FinancialTransferDTO
				.builder()
				.destinyAccount("090909")
				.originAccount("090901")
				.transferDate(new Date())
				.transferValue(1500.0f)
				.build();
    	FinancialTransfer savedTransfer = FinancialTransfer
				.builder()
				.id(1L)
				.destinyAccount("090909")
				.originAccount("090901")
				.schedulingDate(new Date())
				.tax(10.0f)
				.transferDate(new Date())
				.transferValue(1500.0f)
				.build();
    
        Mockito.when( repository.save( Mockito.any() ) ).thenReturn( savedTransfer );
        Mockito.when( calculateService.calculate( Mockito.any(), Mockito.any() ) ).thenThrow(RuntimeException.class);
        
        // Execução
        Throwable throwable = Assertions.catchThrowable(() -> this.service.savingSchedule( transfer ));
        
        // Verificação
        assertThat( throwable ).isInstanceOf( RuntimeException.class );
    }
    
    @Test
    @DisplayName("In this test, i need it to have a correct return, but to go through the conditional rate of the day")
    public void todayTaxTest() {
        // Cenário
    	FinancialTransferDTO transfer = FinancialTransferDTO
				.builder()
				.destinyAccount("090909")
				.originAccount("090901")
				.transferDate(new Date())
				.transferValue(1500.0f)
				.build();
    	FinancialTransfer savedTransfer = FinancialTransfer
				.builder()
				.id(1L)
				.destinyAccount("090909")
				.originAccount("090901")
				.schedulingDate(new Date())
				.tax(48.0f)
				.transferDate(new Date())
				.transferValue(1500.0f)
				.build();
    
        Mockito.when( repository.save( Mockito.any() ) ).thenReturn( savedTransfer );
        
        // Execução
        savedTransfer = this.service.savingSchedule( transfer );
        // Verificação
        assertThat(savedTransfer.getId()).isNotNull();
        assertThat(savedTransfer.getDestinyAccount()).isEqualTo(transfer.getDestinyAccount());
    }
    
    @Test
    @DisplayName("In this test, i need it to have a correct return, but to pass the rate conditional of up to 10 days")
    public void tenDaysTaxTest() {
        // Cenário
    	FinancialTransferDTO transfer = FinancialTransferDTO
				.builder()
				.destinyAccount("090909")
				.originAccount("090901")
				.transferDate(this.calculateDate(5))
				.transferValue(1500.0f)
				.build();
    	FinancialTransfer savedTransfer = FinancialTransfer
				.builder()
				.id(1L)
				.destinyAccount("090909")
				.originAccount("090901")
				.schedulingDate(new Date())
				.tax(60.0f)
				.transferDate(this.calculateDate(5))
				.transferValue(1500.0f)
				.build();
    
        Mockito.when( repository.save( Mockito.any() ) ).thenReturn( savedTransfer );
        
        // Execução
        savedTransfer = this.service.savingSchedule( transfer );
        // Verificação
        assertThat(savedTransfer.getId()).isNotNull();
        assertThat(savedTransfer.getDestinyAccount()).isEqualTo(transfer.getDestinyAccount());
    }
    
    @Test
    @DisplayName("In this test, i need it to have a correct return, but to pass the rate conditional of up to 20 days")
    public void twentyDaysTaxTest() {
        // Cenário
    	FinancialTransferDTO transfer = FinancialTransferDTO
				.builder()
				.destinyAccount("090909")
				.originAccount("090901")
				.transferDate(this.calculateDate(15))
				.transferValue(1500.0f)
				.build();
    	FinancialTransfer savedTransfer = FinancialTransfer
				.builder()
				.id(1L)
				.destinyAccount("090909")
				.originAccount("090901")
				.schedulingDate(new Date())
				.tax(120.0f)
				.transferDate(this.calculateDate(15))
				.transferValue(1500.0f)
				.build();
    
        Mockito.when( repository.save( Mockito.any() ) ).thenReturn( savedTransfer );
        
        // Execução
        savedTransfer = this.service.savingSchedule( transfer );
        // Verificação
        assertThat(savedTransfer.getId()).isNotNull();
        assertThat(savedTransfer.getDestinyAccount()).isEqualTo(transfer.getDestinyAccount());
    }
    
    @Test
    @DisplayName("In this test, i need it to have a correct return, but to pass the rate conditional of up to 30 days")
    public void thirtyDaysTaxTest() {
        // Cenário
    	FinancialTransferDTO transfer = FinancialTransferDTO
				.builder()
				.destinyAccount("090909")
				.originAccount("090901")
				.transferDate(this.calculateDate(25))
				.transferValue(1500.0f)
				.build();
    	FinancialTransfer savedTransfer = FinancialTransfer
				.builder()
				.id(1L)
				.destinyAccount("090909")
				.originAccount("090901")
				.schedulingDate(new Date())
				.tax(90.0f)
				.transferDate(this.calculateDate(25))
				.transferValue(1500.0f)
				.build();
    
        Mockito.when( repository.save( Mockito.any() ) ).thenReturn( savedTransfer );
        
        // Execução
        savedTransfer = this.service.savingSchedule( transfer );
        // Verificação
        assertThat(savedTransfer.getId()).isNotNull();
        assertThat(savedTransfer.getDestinyAccount()).isEqualTo(transfer.getDestinyAccount());
    }
    
    @Test
    @DisplayName("In this test, i need it to have a correct return, but to pass the rate conditional of up to 40 days")
    public void fortyDaysTaxTest() {
        // Cenário
    	FinancialTransferDTO transfer = FinancialTransferDTO
				.builder()
				.destinyAccount("090909")
				.originAccount("090901")
				.transferDate(this.calculateDate(35))
				.transferValue(1500.0f)
				.build();
    	FinancialTransfer savedTransfer = FinancialTransfer
				.builder()
				.id(1L)
				.destinyAccount("090909")
				.originAccount("090901")
				.schedulingDate(new Date())
				.tax(60.0f)
				.transferDate(this.calculateDate(35))
				.transferValue(1500.0f)
				.build();
    
        Mockito.when( repository.save( Mockito.any() ) ).thenReturn( savedTransfer );
        
        // Execução
        savedTransfer = this.service.savingSchedule( transfer );
        // Verificação
        assertThat(savedTransfer.getId()).isNotNull();
        assertThat(savedTransfer.getDestinyAccount()).isEqualTo(transfer.getDestinyAccount());
    }
    
    @Test
    @DisplayName("In this test, i need it to have a correct return, but to pass the rate conditional longer than 40 days")
    public void moreFortyDaysTaxTest() {
        // Cenário
    	FinancialTransferDTO transfer = FinancialTransferDTO
				.builder()
				.destinyAccount("090909")
				.originAccount("090901")
				.transferDate(this.calculateDate(45))
				.transferValue(105000.0f)
				.build();
    	FinancialTransfer savedTransfer = FinancialTransfer
				.builder()
				.id(1L)
				.destinyAccount("090909")
				.originAccount("090901")
				.schedulingDate(new Date())
				.tax(2100.0f)
				.transferDate(this.calculateDate(45))
				.transferValue(105000.0f)
				.build();
    
        Mockito.when( repository.save( Mockito.any() ) ).thenReturn( savedTransfer );
        
        // Execução
        savedTransfer = this.service.savingSchedule( transfer );
        // Verificação
        assertThat(savedTransfer.getId()).isNotNull();
        assertThat(savedTransfer.getDestinyAccount()).isEqualTo(transfer.getDestinyAccount());
    }
    
    @Test
    @DisplayName("In this test, i need it to have a incorrect return, because the current date is greater than the transfer date")
    public void errorDateTest() {
        // Cenário
    	FinancialTransferDTO transfer = FinancialTransferDTO
				.builder()
				.destinyAccount("090909")
				.originAccount("090901")
				.transferDate(this.subtractionDate(5))
				.transferValue(105.0f)
				.build();
    	FinancialTransfer savedTransfer = FinancialTransfer
				.builder()
				.id(1L)
				.destinyAccount("090909")
				.originAccount("090901")
				.schedulingDate(new Date())
				.tax(10.0f)
				.transferDate(this.subtractionDate(5))
				.transferValue(105.0f)
				.build();
    
        Mockito.when( repository.save( Mockito.any() ) ).thenReturn( savedTransfer );
        
        // Execução
        Throwable throwable = Assertions.catchThrowable(() -> this.service.savingSchedule( transfer ));
        
        // Verificação
        assertThat( throwable ).isInstanceOf( RuntimeException.class ).hasMessage("Error: Please inform a valid date.");
    }
    
    public Date subtractionDate(Integer days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -days);
		return cal.getTime();
	}
    
    public Date calculateDate(Integer days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
}
