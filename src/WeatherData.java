import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeatherData {
    /** Guaranteed to not be null and to contain only non-null entries */
    private ArrayList<Double> temperatures;

    public WeatherData(ArrayList<Double> temperatures){
        this.temperatures = temperatures;
    }

    /**
     * Cleans the data by removing from temperatures all values that are less than
     * lower and all values that are greater than upper, as described in part (a)
     */
    public void cleanData(double lower, double upper){
        for (int i = temperatures.size() - 1; i >= 0; i--){
            double temp = temperatures.get(i);
            if(temp < lower || temp > upper) temperatures.remove(i);
        }
    }

    /**
     * Returns the length of the longest heat wave found in temperatures, as described in
     * part (b)
     * Precondition: There is at least one heat wave in temperatures based on threshold.
     */
    public int longestHeatWave(double threshold){
        int longest = 0, current = 0;
        for (double t : temperatures){
            if(t > threshold){
                current++;
                if(longest < current) longest = current;
            } else current = 0;
        }
        return longest;
    }

    /** Returns a shallow copy of the temperatures array list */
    public ArrayList<Double> getTemperatures(){
        return new ArrayList<>(temperatures);
    }

    public static void main(String[] args){
        ArrayList<Double> listA = new ArrayList<>(List.of(99.1, 142.0, 85.0, 85.1, 84.6, 94.3, 124.9, 98.0, 101.0, 102.5));
        WeatherData dataA = new WeatherData(listA);
        dataA.cleanData(85.0, 120.0);
        assert dataA.getTemperatures().equals(new ArrayList<>(List.of(99.1, 85.0, 85.1, 94.3, 98.0, 101.0, 102.5)));
        ArrayList<Double> listB = new ArrayList<>(List.of(100.5, 98.5, 102.0, 103.9, 87.5, 105.2, 90.3, 94.8, 109.1, 102.1, 107.4, 93.2));
        WeatherData dataB = new WeatherData(listB);
        assert dataB.longestHeatWave(100.5) == 3;
    }
}
