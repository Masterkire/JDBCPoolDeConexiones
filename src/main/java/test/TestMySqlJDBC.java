package test;

import java.sql.*;
import java.sql.DriverManager;

public class TestMySqlJDBC {
    public static void main(String[] args) {
        var url = "jdbc:mysql://localhost:3307/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        try {
            //La siguiente linea ayuda a conectarte cuando es una aplicacion web
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url,"root","admin");
            Statement instruccion = conexion.createStatement();
            var sql = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
            ResultSet resultado = instruccion.executeQuery(sql);
            while(resultado.next()){
                System.out.print(" Id Persona: " + resultado.getInt("id_persona"));
                System.out.print(" Nombre: " + resultado.getString("nombre"));
                System.out.println(" Apellido: " + resultado.getString("apellido"));
                System.out.println(" Email: " + resultado.getString("email"));
                System.out.println(" Telefono: " + resultado.getString("telefono"));
                System.out.println("");
            }
            resultado.close();
            instruccion.close();
            conexion.close();
        }  catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
