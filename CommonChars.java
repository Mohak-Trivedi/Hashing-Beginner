// Problem: https://leetcode.com/problems/find-common-characters/

// Can't solve with indexOf(), because not just the occurrence, but the number of times it occurs must also be kept into consideration.
// Example: Notice how 2nd "o" is not common.
// Input: words = ["cool","lock","cook"]
// Output: ["c","o"]
// This is why we must opt for creating a frequency map.

import java.util.*;

class Solution {
    public List<String> commonChars(String[] words) {
        String firstStr = words[0];
        Map<Character, Integer> firstStrFMap = new HashMap<>();
        for (int i = 0; i < firstStr.length(); i++) {
            char ch = firstStr.charAt(i);

            int freq = firstStrFMap.getOrDefault(ch, 0) + 1;
            firstStrFMap.put(ch, freq);
        }

        for (int i = 1; i < words.length; i++) {
            String newStr = words[i];
            Map<Character, Integer> newStrFMap = new HashMap<>();
            for (int j = 0; j < newStr.length(); j++) {
                char ch = newStr.charAt(j);

                int freq = newStrFMap.getOrDefault(ch, 0) + 1;
                newStrFMap.put(ch, freq);
            }

            for (Character key : firstStrFMap.keySet()) {
                if (newStrFMap.containsKey(key)) {
                    firstStrFMap.put(key, Math.min(firstStrFMap.get(key), newStrFMap.get(key))); // The MAIN part
                                                                                                 // responsible for
                                                                                                 // maintaining count
                                                                                                 // check and not just
                                                                                                 // existence check.
                } else {
                    firstStrFMap.put(key, 0);
                }
            }
        }

        List<String> list = new ArrayList<>();
        for (Character key : firstStrFMap.keySet()) {
            if (firstStrFMap.get(key) != 0) {
                for (int i = 1; i <= firstStrFMap.get(key); i++) {
                    list.add(key + "");
                }
            }
        }
        return list;
    }
}
