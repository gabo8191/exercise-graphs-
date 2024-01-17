package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private List<Exercise> exercises;
    private List<Map<Exercise, Double>> adyacency;
    private ArrayList<Exercise> recommendedExercises = new ArrayList<Exercise>();

    /*
     * es una lista de mapas. Cada mapa representa las conexiones entre un ejercicio
     * y
     * sus ejercicios adyacentes.La clave del mapa es un ejercicio adyacente y el
     * valor
     * es un double que representa el peso de la conexión.
     */
    public List<Exercise> recommendedExercises() {
        return recommendedExercises;
    }

    public Graph() {
        exercises = new ArrayList<>();
        adyacency = new ArrayList<>();
    }

    public void addExcercise(Exercise exercise) {
        exercises.add(exercise);
        adyacency.add(new HashMap<>());
        // Se agrega una lista vacía para las conexiones del nuevo libro
    }

    // Método para agregar una arista (conexión entre ejercicios con grupos
    // musculares en común)
    public void addConexion() {
        boolean foundConnection = true; // Variable para controlar el bucle while

        while (foundConnection) { // Bucle principal
            foundConnection = false; // Reinicia la variable foundConnection a false

            // Bucle para recorrer los ejercicios en la lista
            for (int i = 0; i < exercises.size(); i++) {
                Exercise exercise1 = exercises.get(i); // Obtiene el ejercicio en la posición i

                // Bucle para comparar ejercicio1 con los ejercicios restantes en la lista
                for (int j = i + 1; j < exercises.size(); j++) {
                    Exercise exercise2 = exercises.get(j); // Obtiene el ejercicio en la posición j

                    // Bucle para recorrer los grupos musculares del ejercicio1
                    for (int k = 0; k < exercise1.getMuscularGroups().size(); k++) {
                        // Obtiene el grupo muscular en la posición k del ejercicio1
                        String muscularGroupExcercise1 = exercise1.getMuscularGroups().get(k);

                        // Bucle para recorrer los grupos musculares del ejercicio2
                        for (int l = 0; l < exercise2.getMuscularGroups().size(); l++) {
                            // Obtiene el grupo muscular en la posición l del ejercicio2
                            String muscularGroupExcercise2 = exercise2.getMuscularGroups().get(l);

                            // Verifica si los grupos musculares son iguales
                            if (muscularGroupExcercise1.equals(muscularGroupExcercise2)) {
                                // Obtiene las conexiones existentes para ejercicio1 y ejercicio2
                                Map<Exercise, Double> excercise1Connections = adyacency.get(i);
                                Map<Exercise, Double> excercise2Connections = adyacency.get(j);

                                // Verifica si ejercicio1 y ejercicio2 aún no están conectados
                                if (!excercise1Connections.containsKey(exercise2)
                                        && !excercise2Connections.containsKey(exercise1)) {
                                    // Calcula el peso de la conexión como la suma de la imc recomendado de ambos
                                    // ejercicios

                                    double weight = (exercise1.getMaxBmi() + (exercise1.getIdExercise() * 0.01))
                                            + (exercise2.getMaxBmi() + (exercise2.getIdExercise() * 0.01));

                                    // Agrega la conexión en ambos sentidos
                                    excercise1Connections.put(exercise2, weight);
                                    excercise2Connections.put(exercise1, weight);

                                    // Actualiza foundConnection a true para continuar el bucle
                                    foundConnection = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Exercise findMostRecommendedExerciseByMuscularGroup(String muscularGroup, double bmi) {

        Exercise mostRecommendedExercise = null; // Variable para almacenar el ejercicio más recomendado
        double maxPopularity = Double.MIN_VALUE; // Variable para almacenar la recomendación máxima encontrada

        // Bucle para recorrer los ejercicios en la lista
        for (int i = 0; i < exercises.size(); i++) {
            Exercise exercise = exercises.get(i); // Obtiene el ejercicio en la posición i

            // Verifica si el ejercicio tiene el grupo muscular buscado y no está en la
            // lista de ejercicios recomendados
            if (exercise.getMuscularGroups().contains(muscularGroup) && !recommendedExercises.contains(exercise)) {
                // Obtiene las conexiones del ejercicio
                Map<Exercise, Double> connections = adyacency.get(i);

                // Bucle para recorrer las conexiones del ejercicio
                for (Map.Entry<Exercise, Double> entry : connections.entrySet()) {
                    Exercise connectedExercise = entry.getKey(); // Obtiene el ejercicio conectado
                    double connectionWeight = entry.getValue(); // Obtiene el peso de la conexión
                    // Verifica si el ejercicio conectado tiene el grupo muscular buscado, tiene una
                    // conexión más recomendada o popular
                    // que el ejercicio actual y no está en la lista de ejercicios recomendados
                    if (connectedExercise.getMuscularGroups().contains(muscularGroup)) {
                        if (!recommendedExercises.contains(connectedExercise)) {

                            // VALIDAR SI EL BMI DADO ESTA ENTRE EL BMI MIN Y MAX DEL EJERCICIO
                            if (bmi >= connectedExercise.getMinBmi() && bmi <= connectedExercise.getMaxBmi()) {
                                // Verifica si el peso de la conexión es mayor que la recomendación máxima
                                // encontrada
                                if (connectionWeight > maxPopularity) {
                                    mostRecommendedExercise = connectedExercise;
                                    maxPopularity = connectionWeight;
                                }
                            }
                        }
                    }

                }
            }
        }

        // Si se encontró un ejercicio más recomendado, se agrega a la lista de libros
        // recomendados
        if (mostRecommendedExercise != null) {
            recommendedExercises.add(mostRecommendedExercise);
        }

        return mostRecommendedExercise; // Devuelve el ejercicio más recomendado encontrado
    }
}
