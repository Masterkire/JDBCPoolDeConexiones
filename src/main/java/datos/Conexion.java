package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";
    private static BasicDataSource ds;
    
    public static DataSource getDataSource(){
        if(ds == null){
            ds = new BasicDataSource();
            ds.setUrl(JDBC_URL);
            ds.setUsername(JDBC_USER);
            ds.setPassword(JDBC_PASSWORD);
            //Definimos el tamaño inicial del pool de conexiones
            ds.setInitialSize(5);
        }
        return ds;
    }
    
    public static Connection getConnection() throws SQLException{
        //SIn usar el metodo getDataSource()
//        return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
        return getDataSource().getConnection();
    }
    
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public static void close(Statement smtm) throws SQLException{
        smtm.close();
    }
    
    public static void close(PreparedStatement smtm) throws SQLException{
        smtm.close();
    }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
}
