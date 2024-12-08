package AE01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SimulacionMT implements Runnable {
	private int tipoProteina; // Atributo para almacenar el tipo de estructura de la proteína
	private int cantidadProteinas;

	public SimulacionMT(int tipoProteina, int cantidadProteinas) {
		this.tipoProteina = tipoProteina;
		this.cantidadProteinas = cantidadProteinas;
	}

	// Método run() que es llamado cuando el hilo comienza a ejecutarse
	@Override
	public void run() {
		for (int i = 0; i < cantidadProteinas; i++) {
			long startTime = System.currentTimeMillis();
			double result = simulation(tipoProteina);
			long endTime = System.currentTimeMillis();
			creacionArchivo(tipoProteina, Thread.currentThread().getId(), result, startTime, endTime);
		}
	}

	// Método que realiza la simulación
	private double simulation(int tipoProteina) {
		double calc = 0.0;
		double simulationTime = Math.pow(5, tipoProteina);
		double startTime = System.currentTimeMillis();
		double endTime = startTime + simulationTime;
		while (System.currentTimeMillis() < endTime) {
			calc = Math.sin(Math.pow(Math.random(), 2));
		}
		return calc;
	}
	// Método que crea el archivo
	public static void creacionArchivo(int prot, long process, double result, long startTime, long endTime) {
		try {
			String formattedFecha = fecha();
			String trascuredTime = getSimulationTookTime(startTime, endTime);
			String finalitazionTime = getFinalitazionTime(startTime, endTime);

			File archivo = new File(
					"data" + File.separatorChar + "PROT_MT_" + prot + "_n" + process + "_ " + formattedFecha + ".sim");

			File directorio = archivo.getParentFile();
			if (directorio != null && !directorio.exists()) {
				directorio.mkdirs();
			}

			FileWriter writer = new FileWriter(archivo);

			if (archivo.length() == 0) {
				writer.append(formattedFecha);
				writer.append("\n");
				writer.append(finalitazionTime);
				writer.append("\n");
				writer.append(trascuredTime);
				writer.append("\n");
				writer.append(String.valueOf(result));
				writer.append("\n");
			}

			writer.flush();
			writer.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static String getSimulationTookTime(long startTime, long endTime) {
		double durationInSeconds = (endTime - startTime) / 1000.0;
		int segundos = (int) durationInSeconds;
		int centesimas = (int) ((durationInSeconds - segundos) * 100);
		String transcuredTime = segundos + "_" + centesimas;
		return (transcuredTime);
	}

	public static String getFinalitazionTime(long startTime, long endTime) {
		Date startTimeDate = new Date(startTime);
		Date endTimeDate = new Date(endTime);

		long differenceInMillis = endTimeDate.getTime() - startTimeDate.getTime();

		// Crear una nueva fecha con la diferencia (restando la diferencia)
		Date resultDate = new Date(startTimeDate.getTime() + differenceInMillis);

		String trascuredTimeFormatted = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(resultDate);
		return (trascuredTimeFormatted);
	}

	public static String fecha() {
		// Obtén la fecha y hora actual
		LocalDateTime fechaHoraActual = LocalDateTime.now();

		// Define el formato deseado
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

		// Formatea la fecha y hora actual
		String fechaHoraFormateada = fechaHoraActual.format(formato);

		// Muestra el resultado
		return (fechaHoraFormateada);
	}
}
