package com.dxc.mypersonalbankapi.config;

import com.dxc.mypersonalbankapi.controladores.ClientesController;
import com.dxc.mypersonalbankapi.persistencia.IClientesRepo;
import com.dxc.mypersonalbankapi.persistencia.ICuentasRepo;
import com.dxc.mypersonalbankapi.persistencia.IPrestamosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class ControllerConfig {

     @Value("${uid_cl}")
     Integer uid;
     @Bean
     public ClientesController mostrarListaClientes() throws Exception {
          ClientesController cc = new ClientesController();
          cc.mostrarLista();
          return cc;
     }

     /*@Bean
     public ClientesController mostrarDetalle() throws Exception {
          ClientesController cc = new ClientesController();
          cc.mostrarDetalle(uid);
          return cc;
     }*/
}

