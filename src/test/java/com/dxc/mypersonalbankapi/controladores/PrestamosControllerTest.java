package com.dxc.mypersonalbankapi.controladores;

import com.dxc.mypersonalbankapi.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class PrestamosControllerTest {

    @Autowired
    private PrestamosController prestamosControl;
    @Test
    void mostrarLista() {
        assertNotNull(prestamosControl);
        System.out.println(prestamosControl.getPrestamosRepo());
    }
}