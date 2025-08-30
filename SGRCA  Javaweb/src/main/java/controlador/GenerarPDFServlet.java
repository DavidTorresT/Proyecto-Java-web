package controlador;

import java.util.List;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Dao.DocumentoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.DocumentoMaestro;

/**
 * Servlet implementation class GenerarPDFServlet
 */
@WebServlet("/GenerarPDFServlet")
public class GenerarPDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerarPDFServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/pdf");
		
		response.setHeader("Content-Disposition", "attachment; filename=documento.pdf");
		
		Document document = new Document();
		
		try {
			
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			
			// Titulo del pdf
			Paragraph titulo = new Paragraph("Reporte Documentos De Los Maestros\n\n");
			
			titulo.setAlignment(Element.ALIGN_CENTER);
			document.add(titulo);
			
			// Obtener datos desde el dao
			DocumentoDao dao = new DocumentoDao();
			
			List<DocumentoMaestro> lista = dao.ReadAll();
			
			// Crear tabla con 6 columnas
			PdfPTable tabla = new PdfPTable(6);
			tabla.setWidthPercentage(100);
			
			// Encabezados
			String [] headers = {"ID", "Codigo", "Nombre", "Tamaño", "Ruta", "Extension"};
			
			for (String h : headers) {
				PdfPCell celda = new PdfPCell(new Paragraph(h));
				
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				tabla.addCell(celda);
			}
			
			// llenar la tabla con los datos
			for (DocumentoMaestro doc : lista) {
				
				tabla.addCell(String.valueOf(doc.getId()));
				tabla.addCell(doc.getCodigo());
				tabla.addCell(doc.getNombre());
				tabla.addCell(doc.getTamanio());
				tabla.addCell(doc.getRuta());
				tabla.addCell(doc.getExt());
			}
			
			document.add(tabla);
			document.close();
			
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
