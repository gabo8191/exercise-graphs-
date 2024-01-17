package model;

import java.util.ArrayList;

public class Exercise {

    protected String nameExcercise;
    protected ArrayList<String> muscularGroups;
    protected String series;
    protected double minBmi; // Body Mass Index
    protected double maxBmi; // Body Mass Index
    protected int idExercise;

    public Exercise(String nameExcercise, ArrayList<String> muscularGroups, String series, double minBmi,
            double maxBmi, int idExercise) {
        this.nameExcercise = nameExcercise;
        this.muscularGroups = muscularGroups;
        this.series = series;
        this.minBmi = minBmi;
        this.maxBmi = maxBmi;
        this.idExercise = idExercise;
    }

    public String getNameExcercise() {
        return nameExcercise;
    }

    public ArrayList<String> getMuscularGroups() {
        return muscularGroups;
    }

    public String getSeries() {
        return series;
    }

    public double getMinBmi() {
        return minBmi;
    }

    public double getMaxBmi() {
        return maxBmi;
    }

    public int getIdExercise() {
        return idExercise;
    }

    @Override
    public String toString() {
        StringBuilder muscular = new StringBuilder();
        for (int i = 0; i < muscularGroups.size(); i++) {
            muscular.append("\n" + muscularGroups.get(i));
        }

        return "<b>Ejercicio: </b>" + nameExcercise + "<br><b>Musculos:</b> " + muscular + "<br><b>Series:</b> "
                + series
                + "<br><b>Min. IMC:</b> "
                + minBmi + "<br><b>Max. IMC: </b>" + maxBmi;
    }

}
