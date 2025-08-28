package modelo;

public class DocumentoMaestro {

	private String codigo;
	private String nombre;
	private String tamanio;
	private String ruta;
	private String ext;
	private int id;
	
	
	
	
	public DocumentoMaestro(String codigo, String nombre, String tamanio, String ruta, String ext) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.tamanio = tamanio;
		this.ruta = ruta;
		this.ext = ext;
	}

	public DocumentoMaestro() {
		super();
		this.id = id;
	}



	public DocumentoMaestro(String codigo, String nombre, String tamanio, String ruta, String ext, String id) {
		// TODO Auto-generated constructor stub
	}

	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTamanio() {
		return tamanio;
	}


	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}


	public String getRuta() {
		return ruta;
	}


	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


	public String getExt() {
		return ext;
	}


	public void setExt(String ext) {
		this.ext = ext;
	}

	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
