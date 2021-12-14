package br.com.cvc.api.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

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
        Mockito.when( calculateService.calcular( Mockito.any(), Mockito.any() ) ).thenReturn( savedTransfer.getTax() );
        
        // Execução
        savedTransfer = this.service.savingSchedule( transfer );
        // Verificação
        assertThat(savedTransfer.getId()).isNotNull();
        assertThat(savedTransfer.getDestinyAccount()).isEqualTo(transfer.getDestinyAccount());
    }
    
    // Criar método no service test para erro em processo de cálculo
	
}
