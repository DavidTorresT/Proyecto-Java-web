package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controlador.Conexion;
import modelo.DocumentoMaestro;

public class DocumentoDao {

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
		}
	}

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