package es.netmind.mypersonalbankapi;

/*import junit.framework.TestCase;*/

import static es.netmind.mypersonalbankapi.controladores.ClientesController.clientesRepo;
import static org.junit.jupiter.api.Assertions.*;

import es.netmind.mypersonalbankapi.controladores.ClientesController;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.persistencia.ClientesInMemoryRepo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class MainTest {
    private static IClientesRepo clientesRepo = ClientesInMemoryRepo.getInstance();
    @ParameterizedTest
    @ValueSource(strings = {"clients list"})
    public void usuarioAccedeSistemaVeListaClientes(String texto) {
        //GIVEN
        System.out.println(texto);
        //WHEN
        List<Cliente> clientes=null;

        if (texto.equals("clients list")) {
            //THEN
            clientes = clientesRepo.getAll();

        }
        assertThat(clientesRepo.getAll(),is(clientes));

    }
}