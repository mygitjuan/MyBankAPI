package es.netmind.mypersonalbankapi;

/*import junit.framework.TestCase;*/

import static org.junit.jupiter.api.Assertions.*;

import es.netmind.mypersonalbankapi.controladores.ClientesController;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.persistencia.ClientesInMemoryRepo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepo;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class MainTest {

    List<Cliente> clientes_actual=null;
    List<Cliente> clientes_expected= null;

    List <Cliente> cliente_nulo = null;
    private static IClientesRepo clientesRepo = ClientesInMemoryRepo.getInstance();

    @BeforeEach
    public void definicionesPrevias() {
        clientes_expected=clientesRepo.getAll();
    }

    @DisplayName("Usuario accede al sistema y ve lista de clientes.")
    @ParameterizedTest
    @ValueSource(strings = {"clients list"})
    public void usuarioAccedeSistemaVeListaClientes(String texto) {
        //GIVEN
        // List<Cliente> clientes_actual=null;
        // List<Cliente> clientes_expected=clientesRepo.getAll();

        System.out.println(texto);

        //WHEN
        if (texto.equals("clients list")) {
            //THEN
            clientes_actual= clientesRepo.getAll();

        }
        assertThat(clientes_expected,is(clientes_actual));
    }

    @DisplayName("Usuario no accede al sistema por usar paramétros incorrectos")
    @ParameterizedTest
    @ValueSource(strings = {"ver lista clientes"})
    public void usuarioNoAccedeSistemaParametrosincorrectos(String texto) {
        //GIVEN
        // List<Cliente> clientes=null;
        // List <Cliente> cliente_nulo = null;
        System.out.println(texto);

        //WHEN
        if (texto.equals("clients list")) {
            //THEN
            clientes_actual = clientesRepo.getAll();

        }

        assertThat(cliente_nulo,is(clientes_actual));


    }

}