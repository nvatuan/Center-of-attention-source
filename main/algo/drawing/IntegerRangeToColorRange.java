package main.algo.drawing;

import java.awt.Color;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;

// `IntegerRangeToColorRange` will tries to map an Integer range with a Color range
// numbers of distinct integers will correspond with number of colors
// Argument: int[] represents a list of integers you want to map
// Returns: HashMap<Integer, java.awt.Color>, map a distinct Integer from int[] to a (supposedly) distinct Color
public class IntegerRangeToColorRange {
    public static final float SATURATE = 1.0f;
    public static final float BRIGHTNESS = 1.0f;
    public static final float HUE_RANGE_START = 0.0f;
    public static final float HUE_RANGE_END   = 1.0f;

    public static HashMap<Integer, Color> getColorMapping(int[] intArray) {
        // -- Collecting distinct integers and sort them
        List<Integer> list = Arrays.stream(intArray).boxed().distinct().collect(Collectors.toList());
        Collections.sort(list);
        // -- Use hue circle to map integer with colors, more details under this link
        // The following code uses the HSB ColorRange to map
        HashMap<Integer, Color> hashMap = new HashMap<Integer, Color>();
        
        int n = list.size();
        for (int i = 0; i < n; i++) {
            float hue = (HUE_RANGE_END - HUE_RANGE_START)/n*i + HUE_RANGE_START;
            hashMap.put(list.get(i), Color.getHSBColor(hue, SATURATE, BRIGHTNESS));
        }

        return hashMap;
    }
}