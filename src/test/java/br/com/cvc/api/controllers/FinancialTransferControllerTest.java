package br.com.cvc.api.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cvc.api.dto.FinancialTransferDTO;
import br.com.cvc.api.entities.FinancialTransfer;
import br.com.cvc.api.services.FinancialTransferService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = FinancialTransferController.class)
@AutoConfigureMockMvc
public class FinancialTransferControllerTest {

	private static final String FinancialTransfer_API = "/api/financial";

    @Autowired
    MockMvc mvc;

    @MockBean
    private FinancialTransferService service;

    @Test
    @DisplayName("In this test, the result is a correct call to api schedulling.")
    public void createFinancialTransferTest() throws Exception {
        // Cenário
        FinancialTransferDTO FinancialTransferDTO = createAnFinancialTransferDTO();
        String json = new ObjectMapper().writeValueAsString(FinancialTransferDTO);
        FinancialTransfer transfer = generateTransfer();
        BDDMockito.given( service.savingSchedule(Mockito.any(FinancialTransferDTO.class)) ).willReturn( transfer );

        // Execução
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(FinancialTransfer_API.concat("/scheduling"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // Verificações
        mvc.perform(request)
            .andExpect( status().isOk() );
    }
    
    @Test
    @DisplayName("In this test, the result is a incorrect call to api schedulling.")
    public void notCreateFinancialTransferTest() throws Exception {
    	// Cenário
        String json = new ObjectMapper().writeValueAsString(null);
        // Execução
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(FinancialTransfer_API.concat("/scheduling"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // Verificações
        mvc.perform(request)
            .andExpect( status().isBadRequest() );
    }
    
    @Test
    @DisplayName("In this test, the result is a correct call to list transfers.")
    public void getFinancialTransfersTest() throws Exception {
        // Cenário
        FinancialTransfer transfer = generateTransfer();
        BDDMockito.given( service.listAllRecords() ).willReturn( Arrays.asList(transfer) );

        // Execução
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(FinancialTransfer_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // Verificações
        mvc.perform(request)
            .andExpect( status().isOk() )
            .andExpect( jsonPath("data").isNotEmpty() );
    }
    
    private FinancialTransfer generateTransfer() {
		FinancialTransfer transfer = FinancialTransfer
        		.builder()
        		.id(1L)
				.schedulingDate(new Date())
				.tax(10.0f)
				.destinyAccount("AB02CD")
				.originAccount("AB01CD")
				.transferDate(new Date())
				.transferValue(1500f)
				.build();
		return transfer;
	}

	private FinancialTransferDTO createAnFinancialTransferDTO() {
		return FinancialTransferDTO
				.builder()
				.destinyAccount("AB02CD")
				.originAccount("AB01CD")
				.transferDate(new Date())
				.transferValue(1500f)
				.build();
	}
	
}
