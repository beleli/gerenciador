package com.gerenciador.erro;

public class LeituraException extends Exception {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LeituraException() { super("Operação cancelada: Número de tentativas excedido."); }
}