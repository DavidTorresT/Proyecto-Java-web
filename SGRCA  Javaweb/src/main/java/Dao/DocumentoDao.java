package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controlador.Conexion;
import modelo.DocumentoMaestro;

public class DocumentoDao {

	/* En el metodo Create recibe como parametro un objeto de la clase DocumentoMaestro llamado (crear), se crea una variable
	 * que contenga codigo sql para insertar datos en la tabla (INTO tbl_documento_maestro) se habre un try instanciando
	 * la clase conexion con el objeto conn con su metodo getConnection y tambien se llama con el objeto ps el metodo 
	 * PreparedStatement con la variable (sql) como parametro.
	 * 
	 * Luego se configuran los parametros con los datos recibidos por el metodo con el objeto (crear) y retorna la 
	 * ejecucion con el metodo executeUpdate, en caso de haber error al insertar SQL muestra un mensaje en pantalla 
	 * y retorna false  */
	public boolean Create(DocumentoMaestro crear) {
		String sql = "INSERT INTO tbl_documento_maestro (codigo, nombre, tamanio, ruta, ext) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = Conexion.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, crear.getCodigo());
			ps.setString(2, crear.getNombre());
			ps.setString(3, crear.getTamanio());
			ps.setString(4, crear.getRuta());
			ps.setString(5, crear.getExt());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println("Error al agregar: " + e.getMessage());
			return false;
		}
	}

	/* En el metodo Update recibe como parametro un objeto de la clase DocumentoMaestro llamado (actualizar), se crea una 
	 * variable que contenga codigo sql para actualizar los datos que contiene la tabla (tbl_documento_maestro) se habre 
	 * un try instanciando la clase conexion con el objeto conn con su metodo getConnection y tambien llamamos con el 
	 * objeto ps el metodo PreparedStatement con la variable (sql) como parametro.
	 * 
	 * Luego se configuran los parametros con los datos recibidos por el metodo con el objeto (actualizar) y retorna 
	 * la ejecucion con el metodo executeUpdate, en caso de haber error al actualizar SQL muestra un mensaje en pantalla 
	 * y retorna false  */
	public boolean Update(DocumentoMaestro actualizar) throws SQLException {
		
		String sql = "UPDATE tbl_documento_maestro SET codigo=?, nombre=?, tamanio=?, ruta=?, ext=? WHERE id=?";
		
		try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, actualizar.getCodigo());
			ps.setString(2, actualizar.getNombre());
			ps.setString(3, actualizar.getTamanio());
			ps.setString(4, actualizar.getRuta());
			ps.setString(5, actualizar.getExt());
			ps.setInt(6, actualizar.getId());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error al actualizar: " + e.getMessage());
			return false;
		}
	}

	/* En el metodo Delete recibe como parametro una variable llamada (id), se crea una variable que contenga
	 * codigo sql para eliminar los datos que esten relacionados con el parametro recibido (id) en la tabla 
	 * (tbl_documento_maestro). 
	 * 
	 * Se habre un try instanciando la clase conexion con el objeto conn con su metodo getConnection y tambien 
	 * llamamos con el objeto ps el metodo PreparedStatement con la variable (sql) como parametro.
	 * 
	 * Luego se configura el parametro con el dato (id) recibido por el metodo y retorna la ejecucion con el metodo 
	 * executeUpdate, en caso de haber error al eliminar SQL muestra un mensaje en pantalla y retorna false  */
	public boolean Delete(String id) {
		String sql = "DELETE FROM tbl_documento_maestro WHERE id=?";
		try (Connection conn = Conexion.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, Integer.parseInt(id));
			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println("Error al eliminar: " + e.getMessage());
			return false;
		}
	}

	/* En el metodo Read recibe como parametro una variable llamada (id), se crea una variable que contenga
	 * codigo sql para realizar una consulta de los datos que esten relacionados con el parametro recibido (id)
	 * en la tabla (tbl_documento_maestro).
	 * 
	 * Se habre un try instanciando la clase conexion con el objeto conn con su metodo getConnection y tambien 
	 * llamamos con el objeto ps el metodo PreparedStatement con la variable (sql) como parametro.
	 * 
	 * Luego se configura el parametro con el dato (id) recibido por el metodo se habre otro try invocando el metodo
	 * ResultSet con el objeto rs asignandole como valor el resultado de la consulta y dentro de un if se crea un objeto
	 * llamdo doc de la clase DocumentoMaestro, se le agrega al objeto (doc) el resultado de la consulta y retornando el 
	 * objeto (doc). */
	public DocumentoMaestro Read(int id) throws SQLException {

		DocumentoMaestro doc = null;
		String sql = "SELECT * FROM tbl_documento_maestro WHERE id=?";

		try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					
					doc = new DocumentoMaestro();

					doc.setId(rs.getInt("id"));
					doc.setCodigo(rs.getString("codigo"));
					doc.setNombre(rs.getString("nombre"));
					doc.setTamanio(rs.getString("tamanio"));
					doc.setRuta(rs.getString("ruta"));
					doc.setExt(rs.getString("ext"));
				}
			}
			return doc;
		}
	}

	public List<DocumentoMaestro> ReadAll() throws SQLException {
		List<DocumentoMaestro> lista = new ArrayList<>();
		String sql = "SELECT * FROM tbl_documento_maestro";

		try (Connection conn = Conexion.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				DocumentoMaestro doc = new DocumentoMaestro();
				doc.setId(rs.getInt("id"));
				doc.setCodigo(rs.getString("codigo"));
				doc.setNombre(rs.getString("nombre"));
				doc.setTamanio(rs.getString("tamanio"));
				doc.setRuta(rs.getString("ruta"));
				doc.setExt(rs.getString("ext"));
				lista.add(doc);
			}
		}
		return lista;
	}
}