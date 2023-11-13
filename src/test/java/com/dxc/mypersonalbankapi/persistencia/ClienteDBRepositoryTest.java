package com.dxc.mypersonalbankapi.persistencia;

import com.dxc.mypersonalbankapi.modelos.clientes.Cliente;
import com.dxc.mypersonalbankapi.modelos.clientes.Empresa;
import com.dxc.mypersonalbankapi.modelos.clientes.Personal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClienteDBRepositoryTest {

    private IClientesRepo repo;

    @BeforeEach
    void setUp() throws Exception {
//        repo = new UsuarioInMemoryRepository();
        repo = new ClienteDBRepository();


    }

    @Test
    void getAll() {
    }

    @Test
    void getClientById() {
    }

    @Test
    void addClient_personal() throws Exception {
        Cliente c = new Personal(null, "Juan Juanez", "jj@j.com", "Calle JJ 1", LocalDate.now(), true, false, "12345678J");
        c = repo.addClient(c);
        assertNotNull(c.getId());
    }
    @Test
    void addClient_empresa() throws Exception {
        Cliente c = new Empresa(null, "Servicios Informatico SL", "si@s.com", "Calle SI 3", LocalDate.now(), true, false, "J12345678", new String[]{"Dev", "Marketing"});
        c = repo.addClient(c);
        assertNotNull(c.getId());
    }

    @Test
    void deleteClient() {
    }

    @Test
    void updateClient() {
    }
}