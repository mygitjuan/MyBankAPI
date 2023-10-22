package es.netmind.mypersonalbankapi;

import es.netmind.mypersonalbankapi.controladores.ClientesController;
import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import es.netmind.mypersonalbankapi.persistencia.ClientesInMemoryRepo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepo;
import es.netmind.mypersonalbankapi.util.TestMostarInstruccionesMain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    String code_real = "";

    @BeforeEach
    public void definicionesPrevias() {
        lista_clientes_esperada =clientesRepo.getAll();
    }

    //CRITERIOS ACEPTACION -- Tarea: "Como usuario del sistema, quiero poder ver nuestra lista de clientes
    // para tener una vision general de los mismos."
    //Escenario 1
    @DisplayName("Escenario 1: Usuario accede al sistema y ve lista de clientes.")
    @ParameterizedTest
    @ValueSource(strings = {"clients list"})
    public void usuarioAccedeSistemaVeListaClientes(String texto) {
        //GIVEN
        // List<Cliente> lista_clientes_real =null;
        // List<Cliente> lista_clientes_esperada =clientesRepo.getAll();

        String[] args = texto.split(" ");
        String arg0 = args[0].toLowerCase();
        String arg1 = args[1].toLowerCase();
        String arg2 = null;

        System.out.println("Escenario 1:"+args[0]+ " " +args[1]);

        //WHEN
        if (!arg0.equals("clients")) {
            System.out.println("Llamada a mostrarInstrucciones()");
            TestMostarInstruccionesMain.mostrarInstrucciones();
        } else if (arg2 != null && arg2.equals("accounts")) {
            System.out.println("Llamada a procesarArgumentosCuentas(args)");
        } else if (arg2 != null && arg2.equals("loans")) {
            System.out.println("Llamada a procesarArgumentosPrestamos(args)");
        } else if (arg2 != null && arg2.equals("loan-evaluation")) {
            System.out.println("Llamada a procesarArgumentosEvaluacionPrestamo(args)");
        } else {

            if (arg1.equals("list")) {
                //THEN
                ClientesController.mostrarLista();
                lista_clientes_real = clientesRepo.getAll();
            }

            assertThat(lista_clientes_esperada, is(lista_clientes_real));
        }
    }

    //Escenario 2
    @DisplayName("Escenario 2: Usuario no accede al sistema por usar paramétros incorrectos")
    @ParameterizedTest
    @ValueSource(strings = {"ver lista clientes"})
    public void usuarioNoAccedeSistemaParametrosincorrectos(String texto) {
        //GIVEN
        // List<Cliente> lista_clientes_real =null;
        List <Cliente> lista_clientes_nula = null;

        String[] args = texto.split(" ");
        String arg0 = args[0].toLowerCase();
        String arg1 = args[1].toLowerCase();
        String arg2 = args[2].toLowerCase();

        System.out.println("Escenario 2:" + args[0] + " " + args[1] + " " +args[2]);

        //WHEN
        if (!arg0.equals("clients")) {
            System.out.println("Llamada a mostrarInstrucciones()");
            TestMostarInstruccionesMain.mostrarInstrucciones();
        } else if (arg2 != null && arg2.equals("accounts")) {
            System.out.println("Llamada a procesarArgumentosCuentas(args)");
        } else if (arg2 != null && arg2.equals("loans")) {
            System.out.println("Llamada a procesarArgumentosPrestamos(args)");
        } else if (arg2 != null && arg2.equals("loan-evaluation")) {
            System.out.println("Llamada a procesarArgumentosEvaluacionPrestamo(args)");
        } else {

            if (arg1.equals("list")) {
                //THEN
                ClientesController.mostrarLista();
                lista_clientes_real = clientesRepo.getAll();
            }

            assertThat(lista_clientes_esperada, is(lista_clientes_real));
        }

        assertThat(lista_clientes_nula,is(lista_clientes_real));

    }

    //CRITERIOS ACEPTACION -- Tarea: "Como usuario del sistema, quiero poder ver el detalle de un clientes
    // para entender su perfil."

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

        System.out.println("Escenario 3:"+texto);

        //WHEN
        ClientesController.mostrarDetalle(Integer.valueOf(id_txt));
        cliente_real = clientesRepo.getClientById(id_num);

        for (Cliente c : lista_clientes_esperada) {
            if (c.getId().equals(cliente_real.getId())) {
                cliente_esperado = c;
                break;
            }
        }

        System.out.println("Cliente esperado:" +cliente_esperado);
        System.out.println("Cliente real:" +cliente_real);

        //THEN
        assertThat(cliente_esperado,is(cliente_real));
    }

    //Escenario 4
    @DisplayName("Escenario 4: Usuario accede al sistema con cliente id inexistente.")
    @ParameterizedTest
    @ValueSource(strings = {"clients 9"})
    public void consultaListaCLientesNOK(String texto) throws Exception {

        //GIVEN
        // Cliente cliente_real = null;
        id_txt = texto.substring(8, 9);
        id_num = Integer.valueOf(id_txt);

        System.out.println("Escenario 4:"+texto);

        int finalId_num = id_num;

        //THEN
        ClientesController.mostrarDetalle(Integer.valueOf(id_txt));
        assertThrows(ClienteException.class, () -> {
            //WHEN
            //Como ClientesController.mostrarDetalle devuelve void, vamos a ejecutar su comando interno
            cliente_real = clientesRepo.getClientById(finalId_num);
        });
    }

    //CRITERIOS ACEPTACION -- Tarea: "Como usuario del sistema, quiero poder registrar nuevos clientes
    // para poder incrementar nuestra base de datos."
    //Escenario 5
    @DisplayName("Escenario 5: registrar el detalle de nuevos clientes en base de datos.")
    @ParameterizedTest
    @ValueSource(strings = {"clients,add,personal,Pedro,pedro@gmail.com,Casa de Pedro 1,2023-10-22,30528228Y",
            "clients,add,empresa,Gaming de Pablo,pablo@gmail.com,Casa de Pablo 1,2023-10-22,A17825340"})
    public void registrarNuevosClientes(String argu)
            throws Exception {
        //GIVEN
        String nombre = "";
        String email = "";
        String dir = "";
        String dni_cif = "";
        int uid = 0;

        String[] args = argu.split(",");
        int argsLength = args.length;
        String arg1 = args[1].toLowerCase();
        String arg2 = args[2].toLowerCase();
        String arg_nombre = args[3];
        String arg_email = args[4];
        String arg_dir = args[5];
        String arg_dni_cif = args[7];

        System.out.println("Escenario 5:"+argu);

        //WHEN
        //Añadimos Persona
        if (arg1.equals("add"))
            //THEN
            ClientesController.add(Arrays.copyOfRange(args, 2, argsLength));

        //Comprobamos: extraemos repo y lo comparamos con los parámetros dde entrada
        lista_clientes_esperada =clientesRepo.getAll();


            for (Cliente c : lista_clientes_esperada) {

                nombre = c.getNombre();
                email = c.getEmail();
                dir = c.getDireccion();

                if (nombre.equals(arg_nombre) &&
                        email.equals(arg_email) &&
                        dir.equals(arg_dir)) {
                    uid = c.getId();
                    break;
                }
            }

            if (arg2.equals("personal")) {
                    cliente_real = clientesRepo.getClientById(uid);
                    Personal p = (Personal) cliente_real;
                    dni_cif = p.getDni();
            } else {
                    cliente_real = clientesRepo.getClientById(uid);
                    Empresa e = (Empresa) cliente_real;
                    dni_cif = e.getCif();
            }
            //THEN
            // Después de insertar a la persona, se busca s el NIF/CIF existen en el repositorio
            assertThat(arg_dni_cif,is(dni_cif));
    }

    //Escenario 6
    @DisplayName("Escenario 6: intenta registrar un nuevo cliente el cliente, " +
            "pero el nombre es inválido por ser menor a 3 carácteres")
    @ParameterizedTest
    @ValueSource(strings = {"clients,add,personal,Li,li@gmail.com,Casa de Li 1,1980-01-01,30528228Y",
            "clients,add,empresa,SA,sa@gmail.com,Casa de Pablo 1,1970-01-01,A17825340"})
    public void registrarNuevosClientesNOK(String argu)
            throws Exception {
        //GIVEN
        String nombre = "";
        String email = "";
        String dir = "";
        String dni_cif = "";
        String dni_cif_noEncontrado = "No encontrado";
        int uid = 0;
        int noEncontrado = -1;

        String[] args = argu.split(",");
        int argsLength = args.length;
        String arg1 = args[1].toLowerCase();
        String arg2 = args[2].toLowerCase();
        String arg_nombre = args[3];
        String arg_email = args[4];
        String arg_dir = args[5];
        String arg_dni_cif = args[7];

        System.out.println("Escenario 6:"+argu);

        //WHEN
        if (arg1.equals("add"))
            ClientesController.add(Arrays.copyOfRange(args, 2, argsLength));

        lista_clientes_esperada =clientesRepo.getAll();


        for (Cliente c : lista_clientes_esperada) {

            nombre = c.getNombre();
            email = c.getEmail();
            dir = c.getDireccion();

            if (nombre.equals(arg_nombre) &&
                    email.equals(arg_email) &&
                    dir.equals(arg_dir)) {
                uid = c.getId();
                break;
            } else uid = noEncontrado;
        }

        if (uid == noEncontrado)
            dni_cif = dni_cif_noEncontrado;
        else {
            if (arg2.equals("personal")) {
                cliente_real = clientesRepo.getClientById(uid);
                Personal p = (Personal) cliente_real;
                dni_cif = p.getDni();
            } else {
                cliente_real = clientesRepo.getClientById(uid);
                Empresa e = (Empresa) cliente_real;
                dni_cif = e.getCif();
            }
        }
        //THEN
        //Después de fallar el añadir de la persona, se busca el NIF/CIF para comprobar que no existen en el repositorio
        assertThat(dni_cif_noEncontrado,is(dni_cif));
    }
//CRITERIOS ACEPTACION -- Tarea: "Como usuario del sistema, quiero poder registrar nuevos clientes
// para poder incrementar nuestra base de datos."
@DisplayName("Escenario 7: Como usuario del sistema, quiero poder modificar los datos de un cliente " +
        "para mantenerlos actualizados.")
@ParameterizedTest
@ValueSource(strings = {"clients,update,1,Juan Juanez Juan,juanj@j.com,Calle Juan J 1,2023-10-22,true,false,12345678J",
                        "clients,update,2,Luisa Perez Luisa,lpl@l.com,Calle Luisa P 2,2023-10-22,true,false,12345678L",
                        "clients,update,3,Los ServiciosInformáticos SL," +
                                "lsi@s.com,Calle Los SI 3,2023-10-22,true,false,J12345678"})
public void modificarNuevosClientes(String argu)
        throws Exception {
    //GIVEN

    int uid = -1;

    String[] args = argu.split(",");
    int argsLength = args.length;
    String arg1 = args[1].toLowerCase();
    int arg_uid = Integer.valueOf(args[2]);
    String arg_nombre = args[3];
    String arg_email = args[4];
    String arg_dir = args[5];
    String arg_alta = args[6];

    String arg_dni_cif = args[9];

    System.out.println("\nEscenario 7: "+args[0]+"; "+args[1]+"; "+args[2]+"; "+args[3]+"; "+args[4]+"; "+args [5]+"; "+
                                       args[6]+"; "+args[7]+"; "+args[8]+"; "+args[9]    );

    //WHEN
    //Añadimos Persona
    if (arg1.equals("update"))
        //THEN
        ClientesController.actualizar(Integer.valueOf(args[2]), Arrays.copyOfRange(args, 3, argsLength));


    //Comprobamos: extraemos repo y lo comparamos con los parámetros de entrada
    lista_clientes_esperada =clientesRepo.getAll();

    for (Cliente c : lista_clientes_esperada) {

        String nombre = c.getNombre();
        String email = c.getEmail();
        String dir = c.getDireccion();
        LocalDate alta = c.getAlta();
        String alta_str =  alta.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String className = c.getClass().getName();
        String dni_cif = null;

        if (className.contains("modelos.clientes.Personal")) {
            Personal p = (Personal) c;
            dni_cif = p.getDni();
        } else {
            Empresa e = (Empresa) c;
            dni_cif = e.getCif();
        }

        System.out.println("\nParámetro: "+nombre+"; "+ email+"; " + dir+"; " + alta_str+"; " + dni_cif);
        System.out.println("Grabada: "+arg_nombre+"; " + arg_email+"; " + arg_dir+"; " + arg_alta+"; " +arg_dni_cif);

        if (nombre.equals(arg_nombre) &&
            email.equals(arg_email) &&
            dir.equals(arg_dir) &&
            alta_str.equals(arg_alta) &&
            dni_cif.equals(arg_dni_cif)) {

            uid = c.getId();
            break;

        }
    }

    clientesRepo.getClientById(arg_uid);
    System.out.println("Cliente id esperado:"+arg_uid);
    System.out.println("Cliente id real:"+uid);
    //THEN
    // Después de insertar a la persona
    assertThat(arg_uid,is(uid));
}

    @DisplayName("Escenario 8: Como usuario del sistema, quiero poder modificar los datos de un cliente " +
            "para mantenerlos actualizados. Pero la fecha de alta es invalida")
    @ParameterizedTest
    @ValueSource(strings = {"clients,update,1,Juan Juanez Juan,juanj@j.com,Calle Juan J 1,2024-10-22,true,false,12345678J",
            "clients,update,2,Luisa Perez Luisa,lpl@l.com,Calle Luisa P 2,2024-10-22,true,false,12345678L",
            "clients,update,3,Los ServiciosInformáticos SL," +
                    "lsi@s.com,Calle Los SI 3,2024-10-22,true,false,J12345678"})
    public void modificarNuevosClientesFechaInvalida(String argu)
            throws Exception {
        //GIVEN

        String[] args = argu.split(",");
        int argsLength = args.length;
        String arg1 = args[1].toLowerCase();

        System.out.println("\nEscenario 8: "+args[0]+"; "+args[1]+"; "+args[2]+"; "+args[3]+"; "+args[4]+"; "
                +args [5]+"; "+ args[6]+"; "+args[7]+"; "+args[8]+"; "+args[9]    );



        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        //WHEN
        if (arg1.equals("update"))
            //THEN
            ClientesController.actualizar(Integer.valueOf(args[2]), Arrays.copyOfRange(args, 3, argsLength));

        System.setOut(originalOut);
        System.setErr(originalErr);

        //Como el método devuelve VOID mandamos el mensaje de salida a un String y verificamos el código esperado.
        String code_INVALIDCLIENT = "";
        final String code_esperado = "INVALIDCLIENT";

        if (outContent.toString().contains("Code: INVALIDCLIENT"))
            code_INVALIDCLIENT = "INVALIDCLIENT";

        System.out.println("Mensaje devuelto:" + outContent.toString());

        assertThat(code_esperado, is(code_INVALIDCLIENT));

    }

    //CRITERIOS ACEPTACION -- Tarea: "Como usuario del sistema, quiero poder evaluar una
    // solicitud de préstamo de cliente, para decidir si lo concedemos o no."
    @DisplayName("Escenario 9: Como usuario del sistema, quiero poder modificar los datos de un cliente " +
            "para mantenerlos actualizados.")
    @ParameterizedTest
    @ValueSource(strings = {"clients 1 loan-evaluation 100",
                            "clients 2 loan-evaluation 1000",
                            "clients 3 loan-evaluation 100"})
    public void evaluacionPrestamoValidoConFondos(String argu)
            throws Exception {
        //GIVEN

        String[] args = argu.split(" ");
        System.out.println("\nEscenario 9: "+args[0]+"; "+args[1]+"; "+args[2]+"; "+args[3]);

        final String code_esperado = "SÍ se puede conceder";
        int argsLength = args.length;
        int uid = Integer.valueOf(args[1]);
        Double cantidad = argsLength >= 4 ? Double.valueOf(args[3]) : 0;

        //WHEN
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        if (cantidad > 0) {
            ClientesController.evaluarPrestamo(uid, cantidad);
        } else TestMostarInstruccionesMain.mostrarInstrucciones();

        System.setOut(originalOut);
        System.setErr(originalErr);

        //Como el método devuelve VOID mandamos el mensaje de salida a un String y verificamos el código esperado.
        if (outContent.toString().contains(code_esperado))
            code_real = code_esperado;

        System.out.println("Mensaje devuelto:" + outContent.toString());

        assertThat(code_esperado, is(code_real));

    }
    @DisplayName("Escenario 10: Como usuario del sistema, quiero poder modificar los datos de un cliente " +
            "para mantenerlos actualizados.")
    @ParameterizedTest
    @ValueSource(strings = {"clients 1 loan-evaluation 1000",
            "clients 2 loan-evaluation 100000",
            "clients 3 loan-evaluation 1100"})
    public void evaluacionPrestamoSinFondos(String argu)
            throws Exception {
        //GIVEN

        String[] args = argu.split(" ");
        System.out.println("\nEscenario 9: "+args[0]+"; "+args[1]+"; "+args[2]+"; "+args[3]);

        final String code_esperado = "Saldo insuficiente";
        int argsLength = args.length;
        int uid = Integer.valueOf(args[1]);
        Double cantidad = argsLength >= 4 ? Double.valueOf(args[3]) : 0;

        //WHEN
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        if (cantidad > 0) {
            ClientesController.evaluarPrestamo(uid, cantidad);
        } else TestMostarInstruccionesMain.mostrarInstrucciones();

        System.setOut(originalOut);
        System.setErr(originalErr);

        //Como el método devuelve VOID mandamos el mensaje de salida a un String y verificamos el código esperado.
        if (outContent.toString().contains(code_esperado))
            code_real = code_esperado;

        System.out.println("Mensaje devuelto:" + outContent.toString());

        assertThat(code_esperado, is(code_real));

    }
}