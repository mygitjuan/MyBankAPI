package com.dxc.mypersonalbankapi.config;

import com.dxc.mypersonalbankapi.controladores.ClientesController;
import com.dxc.mypersonalbankapi.controladores.CuentasController;
import com.dxc.mypersonalbankapi.controladores.PrestamosController;
import com.dxc.mypersonalbankapi.persistencia.IClientesRepo;
import com.dxc.mypersonalbankapi.persistencia.ICuentasRepo;
import com.dxc.mypersonalbankapi.persistencia.IPrestamosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class ControllerConfig {

     @Autowired
     private IClientesRepo clientesRepo;

     @Autowired
     private ICuentasRepo cuentasRepo;
     @Autowired
     private IPrestamosRepo prestamosRepo;

     @Bean
     @Lazy
     public ClientesController crearCliController() throws Exception {
          System.out.println("clientesRepo: " + clientesRepo);
          ClientesController clic = new ClientesController();
          clic.setClientesRepo(clientesRepo);
          return clic;
     }
     @Bean
     @Lazy
     public CuentasController crearCtaController() throws Exception {
          System.out.println("cuentasRepo: " + cuentasRepo);
          CuentasController ctac = new CuentasController();
          ctac.setCuentasRepo(cuentasRepo);
          return ctac;
     }
     @Bean
     @Lazy
     public PrestamosController crearPreController() throws Exception {
          System.out.println("prestamosRepo: " + prestamosRepo);
          PrestamosController prec = new PrestamosController();
          prec.setPrestamosRepo(prestamosRepo);
          return prec;
     }

}

