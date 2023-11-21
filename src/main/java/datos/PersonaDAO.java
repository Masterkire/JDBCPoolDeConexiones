package datos;

import static datos.Conexion.*;
import domain.PersonaDTO;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.*;

public class PersonaDAO implements IPersonaDao{
    
    private Connection conexionTransaccional;
    
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona = ?";
    
    public PersonaDAO(){
    }
    
    public PersonaDAO(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    
    public List<PersonaDTO> seleccionar() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PersonaDTO persona = null;
        List<PersonaDTO> personas = new ArrayList<>();
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                
                persona = new PersonaDTO(idPersona, nombre, apellido, email, telefono);
                
                personas.add(persona);
            }
        } 
        finally{
            try {
                close(rs);
                close(stmt);
                if( this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return personas;
    }
    
    public int insertar(PersonaDTO persona) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            registros = stmt.executeUpdate();
            System.out.println("Registros insertados: " + registros);
        } 
        finally{
            try {
                close(stmt);
                if( this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        return registros;
    }
    
    public int actualizar(PersonaDTO persona) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());
            registros = stmt.executeUpdate();
            System.out.println("Registros actualizados: " + registros);
        } 
        finally{
            try {
                close(stmt);
                if( this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        return registros;
    }
    
    public int eliminar(PersonaDTO persona) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getIdPersona());
            
            registros = stmt.executeUpdate();
            System.out.println("Registros eliminados: " + registros);
        } 
        finally{
            try {
                close(stmt);
                if( this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        return registros;
    }
}
