package controlador;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    // Nombre de usuario
    private static final String username = "root";

    // Clave de usuario
    private static final String password = "2556229";

    // Ruta de nuestra base de datos
    private static final String url = "jdbc:mysql://localhost:3306/bd_registrocalificado";
    

    public static Connection getConnection() {
        Connection conn = null;
        
        try {
        	// Cargar el driver JDBC de MYSQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer conexion
            conn =DriverManager.getConnection(url, username, password);
            
            System.out.println("Conexion exitosa a la BD");
			
		} catch (ClassNotFoundException | SQLException e) {

			System.out.println("Error: en la conexion a la base de datos" + e.getMessage());
		}
        return conn;
        
   }
    
    public static Statement createStament() {
		return null;
		
	}
    
    
}
