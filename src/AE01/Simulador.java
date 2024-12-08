package AE01;

import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Simulador {

	private Vista1 vista1;
	private ActionListener actionListenerEnviar;

	public static void main(String[] args) {
		Vista1 vista1 = new Vista1();
		new Simulador(vista1);
	}

	public Simulador(Vista1 vista1) {
		this.vista1 = vista1;
		initEventHandlers();
	}

	public void initEventHandlers() {
		this.vista1.mostrar();
		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (!vista1.getTextField().getText().isEmpty()) {
					int proteina1 = Integer.parseInt(vista1.getTextField().getText());
					ejecutarSimulacionMP(1, proteina1);
					ejecutarSimulacionMT(1, proteina1);
				}

				if (!vista1.getTextField_1().getText().isEmpty()) {
					int proteina2 = Integer.parseInt(vista1.getTextField_1().getText());
					ejecutarSimulacionMP(2, proteina2);
					ejecutarSimulacionMT(2, proteina2);
				}

				if (!vista1.getTextField_2().getText().isEmpty()) {
					int proteina3 = Integer.parseInt(vista1.getTextField_2().getText());
					ejecutarSimulacionMP(3, proteina3);
					ejecutarSimulacionMT(3, proteina3);
				}

				if (!vista1.getTextField_3().getText().isEmpty()) {
					int proteina4 = Integer.parseInt(vista1.getTextField_3().getText());
					ejecutarSimulacionMP(4, proteina4);
					ejecutarSimulacionMT(4, proteina4);
				}
				vista1.getTextField().setText(null);
				vista1.getTextField_1().setText(null);
				vista1.getTextField_2().setText(null);
				vista1.getTextField_3().setText(null);
			}
		};
		vista1.getBtnNewButton().addActionListener(actionListenerEnviar);

		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				vista1.getTextField().setText(null);
				vista1.getTextField_1().setText(null);
				vista1.getTextField_2().setText(null);
				vista1.getTextField_3().setText(null);
				vista1.getTextPane().setText(null);
			}
		};
		vista1.getBtnNewButton_1().addActionListener(actionListenerEnviar);
	}

	private void ejecutarSimulacionMP(int tipoProteina, int cantidadProteinas) {
		long startTime = System.currentTimeMillis();

		try {
			String clase = "Auxiliar.SimulacionMP";
			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			String className = clase;

			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(String.valueOf(tipoProteina));
			command.add(String.valueOf(cantidadProteinas));

			ProcessBuilder builder = new ProcessBuilder(command);
			Process p = builder.start();

			p.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al ejecutar la simulación.");
		}
		long endTime = System.currentTimeMillis();
		String result = getSimulationTookTimeMP(startTime, endTime);
		String lastTextPane = vista1.getTextPane().getText();
		vista1.getTextPane().setText(lastTextPane + "Proteina " + tipoProteina + " MP:" + result + "\n");
	}

	private void ejecutarSimulacionMT(int tipoProteina, int cantidadProteinas) {
		long startTime = System.currentTimeMillis();
		Thread t1 = new Thread(new SimulacionMT(tipoProteina, cantidadProteinas));
		t1.start();
		long endTime = System.currentTimeMillis();
		String result = getSimulationTookTimeMT(startTime, endTime);
		String lastTextPane = vista1.getTextPane().getText();
		vista1.getTextPane().setText(lastTextPane + "Proteina " + tipoProteina + " MT:" + result + "\n");
	}

	public String getSimulationTookTimeMP(long startTime, long endTime) {
		double durationInSeconds = (endTime - startTime) / 1000.0;
		int segundos = (int) durationInSeconds;
		int centesimas = (int) ((durationInSeconds - segundos) * 100);
		String transcuredTime = segundos + "seg." + centesimas + "ctos.";
		return (transcuredTime);
	}

	public String getSimulationTookTimeMT(long startTime, long endTime) {
		double durationInSeconds = (endTime - startTime) / 1000.0;
		int segundos = (int) durationInSeconds;
		int centesimas = (int) ((durationInSeconds - segundos) * 100);
		String transcuredTime = segundos + "seg." + centesimas + "ctos.";
		return (transcuredTime);
	}
}