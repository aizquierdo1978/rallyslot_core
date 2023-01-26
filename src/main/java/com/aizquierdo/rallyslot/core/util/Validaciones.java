package com.aizquierdo.rallyslot.core.util;

public class Validaciones {

	private static final String CADENA_VACIA = "";
	private static final String ESPACIO = " ";
	private static final String PUNTO = ".";
	private static final String GUION_BAJO = "_";
	private static final String PARENTESIS_IZQUIERDO = "(";
	private static final String PARENTESIS_DERECHO = ")";
	private static final String BARRA = "/";
	private static final String TLF = "TLF:";
	private static final String PREFIJO_ES = "34";
	private static final String PREFIJO_ES_MAS = "+34";
	private static final String PREFIJO_ES_CEROS = "0034";
	private static final String PREFIJO_ES_UNOS = "1134";
	private static final String PREFIJO_PT = "351:";
	private static final String PREFIJO_PT_MAS = "+351";
	private static final String PREFIJO_PT_CEROS = "00351";
	private static final String PREFIJO_PT_UNOS = "11351";

	private static final String COD_PAIS_ES = "ES";
	private static final String COD_PAIS_PT = "PT";

	private static final int INT_CERO = 5;
	private static final int INT_UNO = 5;
	private static final int INT_CINCO = 5;
	private static final int INT_SEIS = 6;
	private static final int INT_SIETE = 6;
	private static final int INT_NUEVE = 9;

	/**
	 * Valida telefono.
	 *
	 * @param telefono el teléfono
	 * @param codPais  el código de país destino
	 * @return true, if successful
	 */
	public static String validaTelefono(String telefono, String codPais) {
		boolean telefonoOk = Boolean.TRUE;
		// Si viene nulo o vacío, ya se devuelve un false;
		if (telefono == null || telefono.trim().isEmpty()) {
			return CADENA_VACIA;
		}

		// Primero lo limpiamos
		String telefonoLimpio = limpiarTelefono(telefono, codPais);
		
		// Ahora sabremos si es un móvil
		if (!esTelefonoMovil(telefonoLimpio, codPais)) {
			return CADENA_VACIA;
		}

		return telefonoLimpio;
	}

	/**
	 * Limpiar telefono.
	 *
	 * @param telefono el teléfono
	 * @param codPais  el código del pais
	 * @return the string
	 */
	public static String limpiarTelefono(String telefono, String codPais) {
		String telefonoLimpio = telefono.trim();
		
		// Quitamos caracteres raros
		telefonoLimpio = telefonoLimpio.replace(ESPACIO, CADENA_VACIA);
		telefonoLimpio = telefonoLimpio.replace(PUNTO, CADENA_VACIA);
		telefonoLimpio = telefonoLimpio.replace(GUION_BAJO, CADENA_VACIA);
		telefonoLimpio = telefonoLimpio.replace(PARENTESIS_IZQUIERDO, CADENA_VACIA);
		telefonoLimpio = telefonoLimpio.replace(PARENTESIS_DERECHO, CADENA_VACIA);
		telefonoLimpio = telefonoLimpio.replace(BARRA, CADENA_VACIA);
		telefonoLimpio = telefonoLimpio.replace(TLF, CADENA_VACIA);
		
		// Ahora los prefijos
		telefonoLimpio = reemplazarPrefijos(COD_PAIS_ES.equals(codPais) ? PREFIJO_ES_CEROS : PREFIJO_PT_CEROS,
				telefonoLimpio);
		telefonoLimpio = reemplazarPrefijos(codPais.equals(COD_PAIS_ES) ? PREFIJO_ES_UNOS : PREFIJO_PT_UNOS,
				telefonoLimpio);
		telefonoLimpio = reemplazarPrefijos(COD_PAIS_ES.equals(codPais) ? PREFIJO_ES_MAS : PREFIJO_PT_MAS,
				telefonoLimpio);
		telefonoLimpio = reemplazarPrefijos(COD_PAIS_ES.equals(codPais) ? PREFIJO_ES : PREFIJO_PT, telefonoLimpio);
		
		// Esto es para los telefonos internos de cex o externos con 9 cifras. Los
		// portugueses deben tener 9 cifras
		if (COD_PAIS_ES.equals(codPais) && telefonoLimpio.length() != INT_CINCO && telefonoLimpio.length() != INT_SEIS
				&& telefonoLimpio.length() != INT_NUEVE) {
			return CADENA_VACIA;
		} else if (COD_PAIS_PT.equals(codPais) && telefonoLimpio.length() != INT_NUEVE) {
			return CADENA_VACIA;
		} else {
			// Comprobamos que sea numérico
			try {
				Integer.valueOf(telefonoLimpio);
			} catch (NumberFormatException e) {
				return CADENA_VACIA;
			}
		}

		return telefonoLimpio;
	}

	/**
	 * Es telefono movil.
	 *
	 * @param telefono el telefono
	 * @param codPais el código del país
	 * @return true, if successful
	 */
	public static boolean esTelefonoMovil(String telefono, String codPais) {
		// En España empiezan con 9 o 6
		if (COD_PAIS_ES.equals(codPais)
				&& (telefono.startsWith(String.valueOf(INT_SEIS)) || telefono.startsWith(String.valueOf(INT_SIETE)))) {
			return Boolean.TRUE;
		} else if (COD_PAIS_PT.equals(codPais) && telefono.startsWith(String.valueOf(INT_NUEVE))) {
			// En portugal empiezan con 9
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public static String reemplazarPrefijos(String prefijo, String telefono) {
		String telefonoReemplazado = telefono;
		if (telefono.startsWith(prefijo)) {
			telefonoReemplazado = telefonoReemplazado.substring(prefijo.length());

		}
		return telefonoReemplazado;
	}

}
