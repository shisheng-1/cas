package org.apereo.cas.services;

import org.apereo.cas.web.CasYamlHttpMessageConverter;

import lombok.val;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.mock.http.MockHttpOutputMessage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This is {@link CasYamlHttpMessageConverterTests}.
 *
 * @author Misagh Moayyed
 * @since 6.2.0
 */
@Tag("RegisteredService")
public class CasYamlHttpMessageConverterTests {
    private static AbstractRegisteredService getService() {
        val svc = new RegexRegisteredService();
        svc.setServiceId("Testing");
        svc.setName("Test");
        return svc;
    }

    @Test
    public void verifyOperation() throws Exception {
        val input = new CasYamlHttpMessageConverter();
        assertThrows(NotImplementedException.class,
            () -> input.read(RegisteredService.class, mock(HttpInputMessage.class)));
        val outputMessage = new MockHttpOutputMessage();
        input.write(getService(), MediaType.APPLICATION_JSON, outputMessage);
        assertNotNull(outputMessage.getBodyAsString());

        input.write(List.of(getService()), MediaType.APPLICATION_JSON, outputMessage);
        assertNotNull(outputMessage.getBodyAsString());
    }
}