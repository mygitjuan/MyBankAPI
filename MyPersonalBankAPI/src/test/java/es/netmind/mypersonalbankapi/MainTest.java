package es.netmind.mypersonalbankapi;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.persistencia.ClientesInMemoryRepo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MainTest {

    List<Cliente> lista_clientes_real =null;
    List<Cliente> lista_clientes_esperada = null;
    Cliente cliente_real = null;
    Cliente cliente_esperado = null;
    private static IClientesRepo clientesRepo = ClientesInMemoryRepo.getInstance();
    int id_num = 0;
    String id_txt = "";

    @BeforeEach
    public void definicionesPrevias() {
        lista_clientes_esperada =clientesRepo.getAll();
    }

    //CRITERIOS ACEPTACION -- Tarea: "Como usuario del sistema, quiero poder ver nuestra lista de clientes para tener una vision general de los mismos."
    //Escenario 1
    @DisplayName("Escenario 1: Usuario accede al sistema y ve lista de clientes.")
    @ParameterizedTest
    @ValueSource(strings = {"clients list"})
    public void usuarioAccedeSistemaVeListaClientes(String texto) {
        //GIVEN
        // List<Cliente> lista_clientes_real =null;
        // List<Cliente> lista_clientes_esperada =clientesRepo.getAll();

        System.out.println("Escenario 1:"+texto);

        //WHEN
        if (texto.equals("clients list")) {
            //THEN
            lista_clientes_real = clientesRepo.getAll();

        }
        assertThat(lista_clientes_esperada,is(lista_clientes_real));
    }

    //Escenario 2
    @DisplayName("Escenario 2: Usuario no accede al sistema por usar paramétros incorrectos")
    @ParameterizedTest
    @ValueSource(strings = {"ver lista clientes"})
    public void usuarioNoAccedeSistemaParametrosincorrectos(String texto) {
        //GIVEN
        // List<Cliente> lista_clientes_real =null;
        List <Cliente> lista_clientes_nula = null;

        System.out.println("Escenario 4:"+texto);

        //WHEN
        if (texto.equals("clients list")) {
            //THEN
            lista_clientes_real = clientesRepo.getAll();

        }

        assertThat(lista_clientes_nula,is(lista_clientes_real));

    }

    //CRITERIOS ACEPTACION -- Tarea: "Como usuario del sistema, quiero poder ver el detalle de un clientes para entender su perfil."

    //Escenario 3
    @DisplayName("Escenario 3: Usuario accede al sistema consulta un cliente específico.")
    @ParameterizedTest
    @ValueSource(strings = {"clients 1","clients 2","clients 3"})
    public void usuarioConsultaUnCliente(String texto) throws Exception {

        //GIVEN
        // Cliente cliente_real = null;
        // List<Cliente> lista_clientes_esperada =clientesRepo.getAll();

        id_txt = texto.substring(8,9);
        id_num = Integer.valueOf(id_txt);

        System.out.println("Escenario 2:"+texto);

        //WHEN
        cliente_real = clientesRepo.getClientById(id_num);

        for (Cliente c : lista_clientes_esperada) {
            if (c.getId().equals(cliente_real.getId())) {
                cliente_esperado = c;
                break;
            }
        }

        //THEN
        assertThat(cliente_esperado,is(cliente_real));
    }

    //Escenario 4
    @DisplayName("Escenario 4: Usuario accede al sistema con cliente id inexistente.")
    @ParameterizedTest
    @ValueSource(strings = {"clients 4"})
    public void consultaListaCLientesNOK(String texto) throws Exception {

        //GIVEN
        // Cliente cliente_real = null;
        id_txt = texto.substring(8, 9);
        id_num = Integer.valueOf(id_txt);

        System.out.println("Escenario 3:"+texto);

        int finalId_num = id_num;

        //THEN
        assertThrows(ClienteException.class, () -> {
            //WHEN
            cliente_real = clientesRepo.getClientById(finalId_num);
        });
    }



}