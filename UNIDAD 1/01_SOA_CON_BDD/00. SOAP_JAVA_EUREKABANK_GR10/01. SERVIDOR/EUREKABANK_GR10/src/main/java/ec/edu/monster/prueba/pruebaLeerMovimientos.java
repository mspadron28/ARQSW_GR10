/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.prueba;

import ec.edu.monster.modelo.Movimiento;
import ec.edu.monster.servicio.EurekaService;
import java.util.List;

/**
 *
 * @author MATIAS
 */
public class pruebaLeerMovimientos {
    public static void main(String[] args) {
        try {
            
            //DATO
            String cuenta = "00100001";
            //PROCESO
            EurekaService service = new EurekaService();
            List<Movimiento> lista = service.leerMovimientos(cuenta);
            
            //REPORTE
            for (Movimiento r : lista){
                System.out.println(r.getNroMov() + " - " + r.getAccion() + " - " + r.getImporte());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
