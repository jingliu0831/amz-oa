package oa.amazon;
import java.util.*;

public class kSizeSubstrings_William {
    public static List<List<Character>> kSizeSubstings(char[] input, int k) {
        Set<List<Character>> res = new HashSet<List<Character>>();
        HashMap<Character, Integer> window = new HashMap<Character, Integer>();
        int left = 0, right = 0;

        while (right < input.length) {
            if (!window.containsKey(input[right])) {
                window.put(input[right], right);
            } else {
                left = window.get(input[right]) + 1;
                window = new HashMap<Character, Integer>();
                window.put(input[right], right);
            }
            right++;

            if (window.size() == k) {
                List<Character> tmp = new ArrayList<>();
                for (int i = left; i < left + k; i++) {
                    tmp.add(input[i]);
                }
                res.add(tmp);

                window.remove((input[left]));
                left++;
            }
        }
        List<List<Character>> result = new ArrayList<List<Character>>();
        result.addAll(res);

        return result;
    }

    public static void main(String[] args) {
        char[] test = {'a', 'd', 'f', 'g', 'k', 'g'};

        List<List<Character>> results = kSizeSubstings(test, 4);
        System.out.println(results);
    }
}
