package controlador;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* En la clase Conexion se crea el metodo getConnection donde se instancia un objeto de la clase Connection 
 * con el cual se va establecer la conexion a la base de datos en este caso MYSQL con los parametros establecidos 
 * como username, password y url, se utiliza un try para verificar si se logro la conexion o de lo contrario
 * hubo algun error a la hora de establecer la conexion con la base de datos y nos arroja un mensaje en consola */
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
    
    
}
