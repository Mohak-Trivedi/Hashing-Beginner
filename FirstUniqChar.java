
// Problem: https://leetcode.com/problems/first-unique-character-in-a-string/
import java.util.*;

class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> fmap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            int freq = fmap.getOrDefault(c, 0) + 1;
            fmap.put(c, freq);
        }

        int ans = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (fmap.get(c) == 1) {
                ans = i;
                break;
            }
        }

        return ans;
    }
}
