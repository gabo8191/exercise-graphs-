package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import model.Exercise;
import model.Graph;
import view.PrincipalWindow;

public class Manager implements ActionListener {

	Graph graph = new Graph();
	private PrincipalWindow principal;

	public Manager() {
		createGraph();
		principal = new PrincipalWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comand = e.getActionCommand();
		if (comand.equals("Buscar")) {
			String muscularGroup = (String) principal.getComboBox().getSelectedItem();

			double weight = isNumeric(principal.getTxtPeso().getText())
					? Double.parseDouble(principal.getTxtPeso().getText())
					: 0;
			double height = isNumeric(principal.getTxtAltura().getText())
					? Double.parseDouble(principal.getTxtAltura().getText()) * 0.01
					: 0;
			double bmi = height != 0 ? (weight / (height * height)) : 0;
			Exercise aux = graph.findMostRecommendedExerciseByMuscularGroup(muscularGroup, bmi);
			if (aux != null) {
				if (bmi == 0) {
					principal.updateLabelText("Ingrese valores numéricos para altura y peso");
				} else {
					principal.setLblBMIData(bmi);
					principal.updateLabelText(aux.toString());
				}
			} else {
				principal.updateLabelText("No hay ejercicios recomendados en este grupo muscular para su IMC.");
			}
		}
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	public ArrayList<String> armMuscles = new ArrayList<>(
			Arrays.asList("Biceps", "Tríceps", "Antebrazos"));

	public ArrayList<String> shoulderMuscles = new ArrayList<>(
			Arrays.asList("Deltoides"));

	public ArrayList<String> hindTrunkMuscles = new ArrayList<>(
			Arrays.asList("Trapecio", "Dorsal ancho"));

	public ArrayList<String> anteriorTrunkMuscles = new ArrayList<>(
			Arrays.asList("Pectorales", "Abdominales", "Oblicuos", "Serrato"));

	public ArrayList<String> hipMuscles = new ArrayList<>(
			Arrays.asList("Fascia lata", "Glúteos"));

	public ArrayList<String> thighMuscles = new ArrayList<>(
			Arrays.asList("Cuadriceps", "Aductor", "Isquiotibiales"));

	public ArrayList<String> legMuscles = new ArrayList<>(
			Arrays.asList("Gemelos", "Tibial anterior"));

	private void data() {

		graph.addExcercise(new Exercise("Curl de biceps con barra", armMuscles, "3", 0, 29.9, 1));
		graph.addExcercise(new Exercise("Curl de biceps con mancuernas", armMuscles, "3", 0, 29.9, 2));
		graph.addExcercise(new Exercise("Curl de martillo", armMuscles, "4", 18.5, 29.9, 3));
		graph.addExcercise(new Exercise("Press de tríceps en polea alta", armMuscles, "3", 18.6, 29.9, 4));
		graph.addExcercise(new Exercise("Fondos en paralelas", armMuscles, "3", 0, 34.9, 5));
		graph.addExcercise(new Exercise("Extensión de triceps con mancuerna.", armMuscles, "3", 0, 100, 6));
		graph.addExcercise(new Exercise("Press de hombros (press militar)", shoulderMuscles, "3", 18.6, 100, 7));
		graph.addExcercise(new Exercise("Elevaciones laterales con mancuernas", shoulderMuscles, "3", 0, 29.9, 8));
		graph.addExcercise(new Exercise("Elevaciones frontales con mancuernas", shoulderMuscles, "3", 18.5, 30, 9));
		graph.addExcercise(new Exercise("Encogimientos de hombros con barra", hindTrunkMuscles, "3", 18.6, 25, 10));
		graph.addExcercise(new Exercise("Encogimientos de hombros con mancuernas", hindTrunkMuscles, "3", 0, 30, 11));
		graph.addExcercise(new Exercise("Dominadas o Pull-Ups", hindTrunkMuscles, "3", 18.6, 29.9, 12));
		graph.addExcercise(new Exercise("Pulldown en polea alta", hindTrunkMuscles, "3", 18.5, 34.5, 13));
		graph.addExcercise(new Exercise("Peso muerto", hindTrunkMuscles, "3", 18.6, 100, 14));
		graph.addExcercise(new Exercise("Press de banco (bench press)", anteriorTrunkMuscles, "3", 18.6, 34.9, 15));
		graph.addExcercise(new Exercise("Fondos en máquina de pecho", anteriorTrunkMuscles, "3", 18.9, 29.9, 16));
		graph.addExcercise(new Exercise("Aperturas con mancuernas", anteriorTrunkMuscles, "3", 0, 18.6, 17));
		graph.addExcercise(new Exercise("Crunch abdominal", anteriorTrunkMuscles, "3", 0, 100, 18));
		graph.addExcercise(new Exercise("Plancha abdominal", anteriorTrunkMuscles, "3", 15, 34.9, 19));
		graph.addExcercise(new Exercise("Elevación de piernas colgado", anteriorTrunkMuscles, "3", 0.0, 29.9, 20));
		graph.addExcercise(
				new Exercise("Push-ups con progresión de desplazamiento", anteriorTrunkMuscles, "3", 0, 29.9, 21));
		graph.addExcercise(new Exercise("Fondos declinados", anteriorTrunkMuscles, "3", 24.9, 100, 22));
		graph.addExcercise(new Exercise("Giros rusos", anteriorTrunkMuscles, "3", 0, 100, 23));
		graph.addExcercise(new Exercise("Flexiones laterales con pesas", anteriorTrunkMuscles, "3", 0, 100, 24));
		graph.addExcercise(new Exercise("Sentadillas (Sumo y Bárbaras)", hipMuscles, "3", 0, 100, 25));
		graph.addExcercise(new Exercise("Prensa de piernas", hipMuscles, "3", 25, 34.9, 26));
		graph.addExcercise(new Exercise("Hip Thrust (levantamiento de cadera)", hipMuscles, "3", 18.6, 39.9, 27));
		graph.addExcercise(new Exercise("Sentadillas (tradicional)", thighMuscles, "3", 0, 100, 28));
		graph.addExcercise(new Exercise("Sentadillas (frontal, hack)", thighMuscles, "3", 15, 34.9, 29));
		graph.addExcercise(new Exercise("Extensiones de cuádriceps en máquina", thighMuscles, "3", 18.6, 34.9, 30));
		graph.addExcercise(new Exercise("Zancadas", thighMuscles, "3", 24.9, 100, 31));
		graph.addExcercise(new Exercise("Aductores en máquina", thighMuscles, "3", 0, 29.9, 32));
		graph.addExcercise(new Exercise("Sentadillas sumo", thighMuscles, "3", 0, 100, 33));
		graph.addExcercise(new Exercise("Prensa de piernas cerrada", thighMuscles, "3", 18.6, 34.9, 34));
		graph.addExcercise(new Exercise("Curl de piernas tumbado", thighMuscles, "3", 0, 29.9, 35));
		graph.addExcercise(new Exercise("Peso muerto", thighMuscles, "3", 18.6, 34.5, 36));
		graph.addExcercise(new Exercise("Glute Ham Raise (ejercicio de máquina)", thighMuscles, "3", 0, 30, 37));
		graph.addExcercise(new Exercise("Elevaciones de talones en máquina", legMuscles, "3", 0, 18.6, 38));
		graph.addExcercise(new Exercise("Elevaciones de talones con mancuernas", legMuscles, "3", 18.6, 29.9, 39));
		graph.addExcercise(new Exercise("Saltos de tijera", legMuscles, "3", 24.9, 100, 40));
		graph.addExcercise(new Exercise("Elevación de talones sentado", legMuscles, "3", 0, 100, 41));
	}

	private void createGraph() {
		data();
		graph.addConexion();
	}

	public static void main(String[] args) {
		Manager go = new Manager();
		go.createGraph();

	}

}
