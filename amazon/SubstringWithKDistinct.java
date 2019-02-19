package oa.amazon;

import java.util.HashMap;
import java.util.HashSet;

public class SubstringWithKDistinct {
    /* K-1 distinct chars, substring length is k */
    public HashSet<String> kLengthSubstringWithKMinus1Distinct(String s, int k) {
        HashMap<Character, Integer> charToCnt = new HashMap<Character, Integer>();

        int left = 0, right = 0;
        HashSet<String> qualified = new HashSet<>();

        while (right < s.length()) {
            char charOnRight = s.charAt(right);
            charToCnt.put(charOnRight, charToCnt.getOrDefault(charOnRight, 0) + 1);

            while (right - left + 1 > k || charToCnt.size() > k - 1) {
                char charOnLeft = s.charAt(left);
                charToCnt.put(charOnLeft, charToCnt.get(charOnLeft) - 1);
                if (charToCnt.get(charOnLeft) == 0) {
                    charToCnt.remove(charOnLeft);
                }
                left++;
            }

            if (charToCnt.size() == k - 1 && right - left + 1 == k) {
                qualified.add(s.substring(left, right + 1));
            }

            right++;
        }

        return qualified;
    }
}
