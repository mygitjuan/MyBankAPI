package com.dxc.mypersonalbankapi.controladores;

import com.dxc.mypersonalbankapi.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class CuentasControllerTest {

    @Autowired
    private CuentasController cuentasControl;
    @Test
    void mostrarLista() {
        assertNotNull(cuentasControl);
        System.out.println(cuentasControl.getCuentasRepo());
    }
}