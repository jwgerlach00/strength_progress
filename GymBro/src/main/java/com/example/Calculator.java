package com.example;
import java.util.ArrayList;

public class Calculator {
    public static float conservativeIncrementLbs = 2.5f;
    public static float aggressiveIncrementLbs = 5;

    public static float oneRepMax(float weightLbs, int numReps) {
        if (numReps < 1 || numReps > 10) {
            throw new IllegalArgumentException("numReps must be between 1 and 10");
        }
        return (float)Math.floor((weightLbs / (1.0278 - (0.0278*numReps)))); // Brzycki Equation
    }

    public static ArrayList<Float> goalProgressionTimeline(
        float weightLbs,
        int numReps,
        float goalOneRepMax,
        String protocolType
    ) {
        float incrementLbs;
        switch (protocolType) {
            case "conservative":
                incrementLbs = conservativeIncrementLbs;
                break;
            case "aggressive":
                incrementLbs = aggressiveIncrementLbs;
                break;
            default:
                throw new IllegalArgumentException("protocolType must be either 'conservative' or 'aggressive'");
        }

        float oneRM = oneRepMax(weightLbs, numReps);
        float diff = goalOneRepMax - oneRM;
        ArrayList<Float> progressionTimelineLbs = new ArrayList<>();

        while (diff > 0) {
            if (diff >= incrementLbs) {
                progressionTimelineLbs.add(incrementLbs);
                diff -= incrementLbs;
            } else if (diff > conservativeIncrementLbs) {
                progressionTimelineLbs.add(conservativeIncrementLbs);
                diff -= conservativeIncrementLbs;
            } else {
                progressionTimelineLbs.add(diff);
                diff -= diff;
            }
        }
        
        return progressionTimelineLbs;
    }
}
