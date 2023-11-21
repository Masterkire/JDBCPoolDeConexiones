package test;

import datos.Conexion;
import datos.IUsuarioDao;
import datos.UsuarioDAO;
import domain.UsuarioDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestManejoUsuarios {
    public static void main(String[] args) {
        
        Connection conexion = null;
        
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            IUsuarioDao usuarioDao = new UsuarioDAO();
            
        //Insertando un nuevo objeto de tipo usuario
//        Usuario nuevoUsuario = new Usuario("Berrys", "bbpichon");
//        int usuariosNuevos = usuarioDao.insertar(nuevoUsuario);
        
        //Actualizar registro de usuario existente
//        Usuario usuarioModificado = new Usuario(4,"BBerrys", "superbbpichon");
//        int usuariosModificados = usuarioDao.actualizar(usuarioModificado);

        //Eliminar usuarios
//        UsuarioDTO usuarioEliminado = new UsuarioDTO(4);
//        int usuariosEliminados = usuarioDao.eliminar(usuarioEliminado);
        
        //Listar registro en pantalla de usuarios
        List<UsuarioDTO> usuarios = usuarioDao.seleccionar();
        
        usuarios.forEach(usuario -> {
            System.out.println("Usuario: " + usuario);
        });
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        }
        
        
    }
}
