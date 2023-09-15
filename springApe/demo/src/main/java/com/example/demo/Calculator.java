package com.example.demo;
import java.util.ArrayList;

public class Calculator {
    public static float conservativeIncrementLbs = 2.5f;
    public static float aggressiveIncrementLbs = 5;
    public static float conservativeIncrementKgs = 1.25f;
    public static float aggressiveIncrementKgs = 2.5f;
    public static float startingOneRepMaxPercent = 0.85f;

    public static float oneRepMax(float weightLbs, int numReps) {
        if (numReps < 1 || numReps > 10) {
            throw new IllegalArgumentException("numReps must be between 1 and 10");
        }
        return (float)Math.floor((weightLbs / (1.0278 - (0.0278*numReps)))); // Brzycki Equation
    }

    public static float lbsToKgs(float weightKgs) {
        return weightKgs / 2.20462f;
    }

    public static ArrayList<Float> goalProgressionTimeline(
        float currentOneRepMax,
        float goalOneRepMax,
        String units,
        String protocolType
    ) {
        if (!units.equals("lbs") && units.equals("kgs")) {
            throw new IllegalArgumentException("units must be either 'lbs' or 'kgs'");
        }

        float incrementWeight;
        switch (protocolType) {
            case "conservative":
                incrementWeight = (units.equals("lbs")) ? conservativeIncrementLbs : conservativeIncrementKgs;
                break;
            case "aggressive":
                incrementWeight = (units.equals("lbs")) ? aggressiveIncrementLbs : aggressiveIncrementKgs;
                break;
            default:
                throw new IllegalArgumentException("protocolType must be either 'conservative' or 'aggressive'");
        }

        currentOneRepMax *= startingOneRepMaxPercent; // lower starting weight to ease into program

        // float currentOneRepMax = oneRepMax(weightLbs, numReps);
        float diff = goalOneRepMax - currentOneRepMax;
        ArrayList<Float> progressionTimelineLbs = new ArrayList<>();

        progressionTimelineLbs.add(currentOneRepMax); // add current oneRM to timeline

        while (diff > 0) {
            if (diff >= incrementWeight) {
                currentOneRepMax += incrementWeight;
                progressionTimelineLbs.add(currentOneRepMax);
                diff -= incrementWeight;
            } else if (diff > conservativeIncrementLbs) {
                currentOneRepMax += conservativeIncrementLbs;
                progressionTimelineLbs.add(currentOneRepMax);
                diff -= conservativeIncrementLbs;
            } else {
                currentOneRepMax += diff;
                progressionTimelineLbs.add(currentOneRepMax);
                diff -= diff;
            }
        }
        
        return progressionTimelineLbs;
    }
}
