package ru.trofimov.commission.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.trofimov.commission.model.Payment;
import ru.trofimov.commission.model.PaymentResponse;
import ru.trofimov.commission.service.PaymentService;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PaymentRestController.class)
class PaymentRestControllerTest {

    @MockBean
    private PaymentService paymentService;

    @Autowired
    private MockMvc mockMvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Test
    void shouldGetPayment() throws Exception {
        String url = "/api/v1/payments/123";
        PaymentResponse paymentResponse = new PaymentResponse(123L, 30000, 9, 121);
        when(paymentService.calculationOfCommissionForMonth(123L, 9, 121)).thenReturn(paymentResponse);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"phoneNumber\":123,\"monthlyPayments\":30000,\"commission\":900,\"date\":\"October 2021\"}")));

    }

    @Test
    void shouldSavePayment() throws Exception {
        String url = "/api/v1/payments/";

        Payment payment = new Payment();
        payment.setPhoneNumber(123L);
        payment.setPaymentAmount(30000);
        payment.setComment("test");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(payment);

        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetException() throws Exception {
        String url = "/api/v1/payments/";

        Payment payment = new Payment();
        payment.setPhoneNumber(0);
        payment.setPaymentAmount(30000);
        payment.setComment("test");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(payment);

        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isForbidden());
    }
}