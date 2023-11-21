package test;

import datos.Conexion;
import datos.IPersonaDao;
import datos.PersonaDAO;
import domain.PersonaDTO;
import java.sql.*;
import java.util.List;

public class TestManejoPersonas {
    public static void main(String[] args) {
        
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            IPersonaDao personaDao = new PersonaDAO(conexion);
//            Persona personaModificada = new Persona();
//            personaModificada.setIdPersona(2);
//            personaModificada.setNombre("Karla Ivonne");
//            personaModificada.setApellido("Lara");
//            personaModificada.setEmail("klara@mail.com");
//            personaModificada.setTelefono("5559664321");
//            personaDao.actualizar(personaModificada);
            
//            Persona nuevaPersona = new Persona("Hector", "Romero seeeeeeeeeeeeeeeeeeeeffffffffffffffffffffffgggggggggggggggggggggggggggggggggggggggggggggggggggrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww", "hromero@mail.com","5598147635");
//            Persona nuevaPersona = new Persona("Hector", "Romero", "hromero@mail.com","5598147635");
//            personaDao.insertar(nuevaPersona);

//            Persona personaEliminar = new Persona(6);
//            personaDao.eliminar(personaEliminar);
//            
//            conexion.commit();
//            System.out.println("Se ha echo commit de la transaccion");
            
            //Listar registro en pantalla de personas
            List<PersonaDTO> personas = personaDao.seleccionar();
        
            personas.forEach(persona -> {
                System.out.println("Persona: " + persona);
            });
           
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        }
    }
}
