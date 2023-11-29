package com.dxc.mypersonalbankapi.controladores;

import com.dxc.mypersonalbankapi.config.SpringConfig;
import com.dxc.mypersonalbankapi.persistencia.IClientesRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class ClientesControllerTest {

    @Autowired
    private IClientesRepo clientesControl;

    @Test
    void mostrarLista() {
        assertNotNull(clientesControl);
        System.out.println("Clientes: " + clientesControl.toString());
    }

    @Test
    void mostrarDetalle() {
    }

    @Test
    void add() {
    }

    @Test
    void eliminar() {
    }

    @Test
    void actualizar() {
    }

    @Test
    void evaluarPrestamo() {
    }
}