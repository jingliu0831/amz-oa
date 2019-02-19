package oa.amazon;

import java.util.*;
public class kSizeSubstringWithDuplicate_William {

    public static List<List<Character>> kSizeSubstringWithDuplicate(char[] input, int k) {
        Set<List<Character>> res = new HashSet<List<Character>>();
        HashMap<Character, Integer> window = new HashMap<Character, Integer>();
        int left = 0, right = 0;
        int dupIndex = 0;
        boolean duplicate = false;

        while (right < input.length) {
            if (!window.containsKey(input[right])) {
                window.put(input[right], right);
            } else if (window.containsKey(input[right]) && !duplicate){
                duplicate = true;
                dupIndex = right;
            }
            right++;

            if (window.size() == k - 1 && duplicate) {
                List<Character> tmp = new ArrayList<>();
                for (int i = right - k; i < right; i++) {
                    tmp.add(input[i]);
                }
                res.add(tmp);

                if (input[left] == input[dupIndex]) {
                    duplicate = false;
                    window.put(input[dupIndex], dupIndex);
                } else {
                    window.remove((input[left]));
                    left++;
                }
            }
            if (window.size() == k && !duplicate) {
                window.remove((input[left]));
                left++;
            }
        }
        List<List<Character>> result = new ArrayList<List<Character>>();
        result.addAll(res);
        return result;
    }

    public static void main(String[] args) {
        char[] test1 = {'a', 'b', 'a', 'c', 'd', 'c', 'k', 'f'};
        char[] test2 = {'a', 'a', 'b', 'a'};
        List<List<Character>> results = kSizeSubstringWithDuplicate(test1, 4);
        System.out.println(results);
    }
}
