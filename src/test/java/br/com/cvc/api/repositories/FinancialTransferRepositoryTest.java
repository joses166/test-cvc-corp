package br.com.cvc.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.cvc.api.entities.FinancialTransfer;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class FinancialTransferRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    private FinancialTransferRepository repository;

    @Test
    @DisplayName("In this test, i need to save a financial transfer.")
    public void createAFinancialTransferTest() {
        // Cenário
    	FinancialTransfer transfer = FinancialTransfer
    									.builder()
    									.destinyAccount("090909")
    									.originAccount("090901")
    									.schedulingDate(new Date())
    									.tax(10.0f)
    									.transferDate(new Date())
    									.transferValue(1500.0f)
    									.build();
        // Execução
    	FinancialTransfer savedTransfer = this.repository.save(transfer);
        // Verificações
        assertThat( savedTransfer.getId() ).isNotNull();
        assertThat( savedTransfer.getDestinyAccount() ).isEqualTo( transfer.getDestinyAccount() );
        assertThat( savedTransfer.getOriginAccount() ).isEqualTo( transfer.getOriginAccount() );
        assertThat( savedTransfer.getSchedulingDate() ).isEqualTo( transfer.getSchedulingDate() );
        assertThat( savedTransfer.getTax() ).isEqualTo( transfer.getTax() );
        assertThat( savedTransfer.getTransferDate() ).isEqualTo( transfer.getTransferDate() );
        assertThat( savedTransfer.getTransferValue() ).isEqualTo( transfer.getTransferValue() );
    }

    // Test list all
    
    
}
