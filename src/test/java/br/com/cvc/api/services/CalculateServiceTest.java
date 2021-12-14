package br.com.cvc.api.services;

import static org.assertj.core.api.Assertions.assertThat;

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
import br.com.cvc.api.services.impl.CalculateServiceImpl;
import br.com.cvc.api.services.impl.FinancialTransferServiceImpl;
import br.com.cvc.api.services.impl.FortyDaysTax;
import br.com.cvc.api.services.impl.MoreFortyDaysTax;
import br.com.cvc.api.services.impl.MoreTenDaysTax;
import br.com.cvc.api.services.impl.TenDaysTax;
import br.com.cvc.api.services.impl.ThirtyDaysTax;
import br.com.cvc.api.services.impl.TodayTax;
import br.com.cvc.api.services.impl.TwentyDaysTax;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CalculateServiceTest {

	CalculateService service;

    @BeforeEach
    public void setUp() {
        this.service = new CalculateServiceImpl();
    }

    @Test
    @DisplayName("")
    public void test() {
        // Cenário
    	FinancialTransfer transfer = FinancialTransfer
				.builder()
				.id(1L)
				.destinyAccount("090909")
				.originAccount("090901")
				.schedulingDate(new Date())
				.tax(10.0f)
				.transferDate(new Date())
				.transferValue(1500.0f)
				.build();
    	
    	new FortyDaysTax();
    	new MoreFortyDaysTax();
    	new MoreTenDaysTax(null);
    	new TenDaysTax();
    	new ThirtyDaysTax();
    	new TodayTax();
    	new TwentyDaysTax();
    	
        // Execução

    	// Verificação
//        assertThat(savedTransfer.getDestinyAccount()).isEqualTo(transfer.getDestinyAccount());
    }
    
}
