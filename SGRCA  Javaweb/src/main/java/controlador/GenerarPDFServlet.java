package controlador;

import java.util.List;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
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
    
    /* En el servlet GenerarPDFServlet dentro del metodo doGet se da una respueta a la peticion de un formulario para generar 
     * un archivo pdf, se le asigna un nombre al pdf despues instanciamos un objeto de la clase Document de la libreria itexpdf,
     * se crean dos try uno para verificar que no haya error al ejecutarse el codigo y el otro para verificar si hay error en 
     * el documento creado.
     * 
     * Luego se abre el objeto instanciadp (document) para introducirle contenido al archivo, se le asigna un titulo y se alinea
     * al centro para agregarlo al objeto (document) luego intanciamos un objeto de la clase DocumentoDao (dao) y con el llamamos
     * al metodo ReadAll dentro de un ArrayList llamado (lista).
     * 
     * Continuando se crea un objeto de la clase PdfTable donde se crea la tabla y se le asigna el numero de columnas que tendra,
     * se hacen los encabezados de cada columna y se hace un for donde se crea un objeto de la clase PdfCell donde se le agregan 
     * los encabezados y se alinean todas las celdas al centro, luego se hace otro for para llenar las celdas con los datos que 
     * contiene el ArrayList (lista) creado anterirormente despues se agrega esa tabla al documento y se cierra en caso de error 
     * en el documento o en la ejecucion sale un mensaje en consola */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=Reporte.pdf");
		Document document = new Document();
		
		try {
			
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
				
			} catch (DocumentException e) {
				System.out.println("Error en el documento: " + e.getMessage());
			}
			
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
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
