package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestConfigRequest {

    private ConfigRequest conf;

    @BeforeEach
    public void createConfigurationForTestCases() {
        conf = new ConfigRequest();
        conf.buildResponse();
    }

    @Test
    @DisplayName("davematt: Request type is \"config\"")
    public void testType() {
        String type = conf.getRequestType();
        assertEquals("config", type);
    }

    @Test
    @DisplayName("davematt: Features includes \"config\"")
    public void testFeatures(){
        assertTrue(conf.validFeature("config"));
    }

    @Test
    @DisplayName("davematt: Team name is correct")
    public void testServerName() {
        String name = conf.getServerName();
        assertEquals("t12 The Power Coders", name);
    }

    @Test
    @DisplayName("leegina: Formulae list is correct")
    public void testFormulae() {
        assertTrue(conf.validFormulae("vincenty"));
        assertTrue(conf.validFormulae("haversine"));
        assertTrue(conf.validFormulae("cosines"));
    }
}
