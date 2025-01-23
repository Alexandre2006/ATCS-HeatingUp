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

    // Cache for memoization
    private static int[] cache;

    /**
     * Longest Warming Trend
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures) {
        // Store adjacency list
        List<List<Integer>> adjList = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < temperatures.length; i++) {
            adjList.add(new ArrayList<>());
        }

        // Map connections
        for (int i = 0; i < temperatures.length; i++) {
            for (int x = 0; x < i; x++) {
                if (temperatures[i] > temperatures[x]) {
                    adjList.get(i).add(x);
                }
            }
        }

        // Initialize cache
        cache = new int[temperatures.length];

        // Return longest path
        int currentMax = 0;
        for (int i = 0; i < temperatures.length; i++) {
            currentMax = Math.max(currentMax, longestPath(i, adjList));
        }

        return currentMax;
    }

    private static int longestPath(int i, List<List<Integer>> adjList) {
        int maxLen = 0;

        // Find connected nodes (and their lengths) to find max length
        for (int x : adjList.get(i)) {
            if (cache[x] != 0) {
                maxLen = Math.max(maxLen, cache[x]);
            } else {
                maxLen = Math.max(maxLen, longestPath(x, adjList));
            }
        }

        // Save current node to cache
        cache[i] = maxLen + 1;

        // Return max length
        return maxLen + 1;
    }
}
