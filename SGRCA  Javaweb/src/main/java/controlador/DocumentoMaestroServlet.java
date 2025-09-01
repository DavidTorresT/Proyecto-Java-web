package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.DocumentoMaestro;
import Dao.DocumentoDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DocumentoMaestroServlet", urlPatterns = "/DocumentoMaestroServlet")
public class DocumentoMaestroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DocumentoDao dao = new DocumentoDao();

    public DocumentoMaestroServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // No implementado aún
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        HttpSession session = request.getSession();

        try {
            switch (accion) {
                case "Registrar":
                    registrarDocumento(request, response, session, dao);
                    break;
                case "Actualizar":
                    actualizarDocumento(request, response, session, dao);
                    break;
                case "Eliminar":
                    eliminarDocumento(request, response, session, dao);
                    break;
                case "Consultar":
                    consultarDocumento(request, response, session, dao);
                    break;
                case "Ver todos":
                    verTodosDocumentos(request, response, session, dao);
                    break;
                default:
                    session.setAttribute("mensaje", "Acción no reconocida.");
                    session.setAttribute("tipo", "error");
                    response.sendRedirect("index.jsp");
           }
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("mensaje", "Error en base de datos: " + e.getMessage());
            session.setAttribute("tipo", "error");
            response.sendRedirect("index.jsp");
        }

        doGet(request, response);
    }

    // Metodos crud
    private void registrarDocumento(HttpServletRequest request, HttpServletResponse response, HttpSession session,DocumentoDao dao)
            throws IOException, SQLException {

        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String tamanio = request.getParameter("tamanio");
        String ruta = request.getParameter("ruta");
        String ext = request.getParameter("ext");

        if (codigo == null || codigo.isEmpty()) {
        	
        	session.setAttribute("mensaje", "El campo 'Código' es obligatorio.");
        	session.setAttribute("tipo", "error");
        	response.sendRedirect("index.jsp");
            return;
        } else if (nombre == null || nombre.isEmpty()) {
        	
        	session.setAttribute("mensaje","El campo 'Nombre' es obligatorio." );
        	session.setAttribute("tipo", "error");
        	response.sendRedirect("index.jsp");
            return;
        }  else if (tamanio == null || tamanio.isEmpty()) {
        	
        	session.setAttribute("mensaje","El campo 'Tamaño' es obligatorio." );
        	session.setAttribute("tipo", "error");
        	response.sendRedirect("index.jsp");
            return;
        }  else if (ruta == null || ruta.isEmpty()) {
        	
        	session.setAttribute("mensaje","El campo 'Ruta' es obligatorio." );
        	session.setAttribute("tipo", "error");
        	response.sendRedirect("index.jsp");
            return;
        } else if (ext == null || ext.isEmpty()) {
        	
        	session.setAttribute("mensaje","El campo 'Extensión' es obligatorio." );
        	session.setAttribute("tipo", "error");
        	response.sendRedirect("index.jsp");
            return;
        }
        	
        DocumentoMaestro crear = new DocumentoMaestro(codigo, nombre, tamanio, ruta, ext);
        boolean resultCreate = dao.Create(crear);

        if (resultCreate) {
            session.setAttribute("mensaje", "Documento agregado con éxito.");
            session.setAttribute("tipo", "exito");
        } else {
            session.setAttribute("mensaje", "Error al agregar el documento.");
            session.setAttribute("tipo", "error");
        }

        response.sendRedirect("index.jsp");
    }

    private void actualizarDocumento(HttpServletRequest request, HttpServletResponse response, HttpSession session, DocumentoDao dao)
            throws IOException, SQLException {

        String idUpdate = request.getParameter("id");

        if (idUpdate == null || idUpdate.isEmpty()) {
        	
            session.setAttribute("mensaje", "Debe ingresar un ID para actualizar");
            session.setAttribute("tipo", "error");
            
            response.sendRedirect("index.jsp");
            return;
        }

        DocumentoMaestro actualizar = new DocumentoMaestro(); // Debes setear atributos si es necesario
        
        actualizar.setId(Integer.parseInt(idUpdate));
        actualizar.setCodigo(request.getParameter("codigo"));
        actualizar.setNombre(request.getParameter("nombre"));
        actualizar.setTamanio(request.getParameter("tamanio"));
        actualizar.setRuta(request.getParameter("ruta"));
        actualizar.setExt(request.getParameter("ext"));
        
        boolean resultUpdate = dao.Update(actualizar);

        if (resultUpdate) {
            session.setAttribute("mensaje", "Documento actualizado correctamente.");
            session.setAttribute("tipo", "exito");
            
            session.removeAttribute("Documentos");
        } else {
            session.setAttribute("mensaje", "No se pudo actualizar el documento.");
            session.setAttribute("tipo", "error");
        }

        response.sendRedirect("index.jsp");
    }

    private void eliminarDocumento(HttpServletRequest request, HttpServletResponse response, HttpSession session, DocumentoDao dao)
            throws IOException, SQLException {

        String id = request.getParameter("id");

        if (id == null || id.isEmpty()) {
            session.setAttribute("mensaje", "El campo 'ID maestro' es obligatorio para eliminar.");
            session.setAttribute("tipo", "error");
            response.sendRedirect("index.jsp");
            return;
        }

        boolean resultDelete = dao.Delete(id);

        if (resultDelete) {
            session.setAttribute("mensaje", "Documento eliminado con éxito.");
            session.setAttribute("tipo", "exito");
        } else {
            session.setAttribute("mensaje", "Error al eliminar el documento.");
            session.setAttribute("tipo", "error");
        }

        response.sendRedirect("index.jsp");
    }

    private void consultarDocumento(HttpServletRequest request, HttpServletResponse response, HttpSession session, DocumentoDao dao)
            throws IOException, ServletException, SQLException {

    	
        String idRead = request.getParameter("id");

        if (idRead == null || idRead.isEmpty()) {
        	
            session.setAttribute("mensaje", "Debe ingresar un ID para consultar");
            session.setAttribute("tipo", "error");
            response.sendRedirect("index.jsp");
            return;
        }
        
        int id = Integer.parseInt(idRead);
        DocumentoMaestro consultar = dao.Read(id);

        if (consultar != null) {
            session.setAttribute("Documentos", consultar);
            session.setAttribute("mensaje", "Documento encontrado.");
            session.setAttribute("tipo", "exito");
        } else {
            session.setAttribute("mensaje", "No se encontró el documento con ID: " + id);
            session.setAttribute("tipo", "error");
        }

        response.sendRedirect("index.jsp");
    }

    private void verTodosDocumentos(HttpServletRequest request, HttpServletResponse response, HttpSession session, DocumentoDao dao)
            throws IOException, ServletException, SQLException {

        List<DocumentoMaestro> lista = dao.ReadAll();
        
        request.setAttribute("documentos", lista);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("tabla.jsp");
        dispatcher.forward(request, response);
    }
}
