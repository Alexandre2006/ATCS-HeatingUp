import java.util.ArrayList;
import java.util.List;

/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Alexandre Haddad-Delaveau
 */

public class WeatherPatterns {

    /**
     * Longest Warming Trend
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures) {
        // Quick exit conditions
        if (temperatures == null || temperatures.length == 0) {
            return 0;
        }

        // Keep track of length of runs
        int[] lengths = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            lengths[i] = 1;
        }

        int longestRun = 1;

        // Loop through all temps (checking for an increasing temp)
        for (int i = 1; i < temperatures.length; i++) {
            // Find which (previous) temps the current temp is greater than
            for (int j = 0; j < i; j++) {
                if (temperatures[i] > temperatures[j]) {
                    // Find the node the colder node with the longest run, and add 1 to the new higher temperature
                    if (lengths[i] < lengths[j] + 1) {
                        lengths[i] = lengths[j] + 1;
                    }
                }
            }

            // Update the longest run if any improvements have been made
            if (lengths[i] > longestRun) {
                longestRun = lengths[i];
            }
        }

        // Return the longest run
        return longestRun;
    }
}
