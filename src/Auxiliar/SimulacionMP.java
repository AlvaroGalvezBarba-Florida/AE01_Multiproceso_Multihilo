package Auxiliar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JOptionPane;

public class SimulacionMP {

	public static void main(String[] args) {
		try {
			// Obtener los argumentos
			int tipoProteina = Integer.parseInt(args[0]);
			int cantidadProteinas = Integer.parseInt(args[1]);

			// Realizar la simulación (por ejemplo, simular multiproceso)
			realizarSimulacionMultiproceso(tipoProteina, cantidadProteinas);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Los parámetros deben ser números válidos.");
		}
	}

	private static void realizarSimulacionMultiproceso(int tipoProteina, int cantidadProteinas) {
		// Simulación multiproceso - Aquí se pueden crear varios procesos
		for (int i = 0; i < cantidadProteinas; i++) {
			// Simulación simple de creación de procesos
			new Thread(() -> {
				long startTime = System.currentTimeMillis();
				double result = simulation(tipoProteina);
				long endTime = System.currentTimeMillis();
				creacionArchivo(tipoProteina, Thread.currentThread().getId(), result, startTime, endTime);
			}).start();
		}
	}

	public static void creacionArchivo(int prot, long process, double result, long startTime, long endTime) {
		try {
			String formattedFecha = fecha();
			String trascuredTime = getSimulationTookTime(startTime, endTime);
			String finalitazionTime = getFinalitazionTime(startTime, endTime);

			File archivo = new File(
					"data" + File.separatorChar + "PROT_MP_" + prot + "_n" + process + "_ " + formattedFecha + ".sim");

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

	public static double simulation(int type) {
		double calc = 0.0;
		double simulationTime = Math.pow(5, type);
		double startTime = System.currentTimeMillis();
		double endTime = startTime + simulationTime;
		while (System.currentTimeMillis() < endTime) {
			calc = Math.sin(Math.pow(Math.random(), 2));
		}
		return calc;
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
