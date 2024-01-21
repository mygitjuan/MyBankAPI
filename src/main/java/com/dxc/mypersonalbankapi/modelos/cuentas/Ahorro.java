package com.dxc.mypersonalbankapi.modelos.cuentas;

import javax.persistence.Entity;
import java.time.LocalDate;

//@Entity
public class Ahorro extends Cuenta {

    public Ahorro(Integer id, LocalDate fechaCreacion, Double saldo, Double interes, Double comision) {
        super(id, fechaCreacion, saldo, interes, comision);
    }

    public boolean validar() {
        return this.validarComun();
    }

    public Double calcularRentabilidad(){
        return Double.valueOf(0);
    }
}
