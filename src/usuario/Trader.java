package usuario;

public class Trader extends Usuario{
	private int nroCuentaBancaria;
	private float saldoActual;
	private String nombreBanco;
	public Trader(String nombre, int nroCuentaBancaria ,
			String nombreBanco, float saldoActual) {
		super(nombre);
		this.nombreBanco = nombreBanco;
		this.nroCuentaBancaria = nroCuentaBancaria;
		this.saldoActual = saldoActual;
		// TODO Auto-generated constructor stub
	}

}
