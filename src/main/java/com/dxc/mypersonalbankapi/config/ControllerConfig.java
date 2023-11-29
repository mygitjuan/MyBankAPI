package com.dxc.mypersonalbankapi.config;

import com.dxc.mypersonalbankapi.controladores.ClientesController;
import com.dxc.mypersonalbankapi.persistencia.IClientesRepo;
import com.dxc.mypersonalbankapi.persistencia.ICuentasRepo;
import com.dxc.mypersonalbankapi.persistencia.IPrestamosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class ControllerConfig {
     @Autowired
     private IClientesRepo clientesControl;

     @Autowired
     private static ICuentasRepo cuentasControl;
     @Autowired
     private static IPrestamosRepo prestamosControl;

     @Bean
     public ClientesController MostrarLista() {

        return (ClientesController) clientesControl;
     }
}

