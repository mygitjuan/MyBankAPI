package com.dxc.mypersonalbankapi.persistencia;

import com.dxc.mypersonalbankapi.modelos.clientes.Cliente;
import com.dxc.mypersonalbankapi.modelos.clientes.Empresa;
import com.dxc.mypersonalbankapi.modelos.clientes.Personal;
import com.dxc.mypersonalbankapi.properties.PropertyValues;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Repository


public class ClienteRepositoryJPA implements IClientesRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Cliente> getAll() throws Exception {
        return null;
    }

    @Override
    public Cliente getClientById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Cliente addClient(Cliente cliente) throws Exception {
        return null;
    }

    @Override
    public boolean deleteClient(Cliente cliente) throws Exception {
        return false;
    }

    @Override
    public Cliente updateClient(Cliente cliente) throws Exception {
        return null;
    }

    @Override
    public String getdb_url() {
        return null;
    }
}