package oa.amazon;

public class LongestPalindromeSubstring {
    // input: abcbx; output: bcb
    // input: xyzzyabc; output: yzzy
    public String longestPalindromicSubstring(String inputStream) {
        if (inputStream.isEmpty()) return "";

        int maxLen = 1;
        int[] maxLenIndices = new int[2];

        for (int i = 0; i < inputStream.length(); i++) {
            int[] palindromeIndices = checkPalindromeAtIndex(inputStream, i);

            int palindromeLen = palindromeIndices[1] - palindromeIndices[0] + 1;
            if (palindromeLen > maxLen) {
                maxLen = palindromeLen;
                maxLenIndices = palindromeIndices;
            }
        }

        return inputStream.substring(maxLenIndices[0], maxLenIndices[1] + 1);
    }

    private int[] checkPalindromeAtIndex(String input, int index) {
        int m = 0, n = 0;
        while (index + m < input.length() && index - m >= 0) {
            if (input.charAt(index - m) != input.charAt(index + m)) break;
            m++;
        }
        m--;

        while (index + 1 + n < input.length() && index - n >= 0) {
            if (input.charAt(index - n) != input.charAt(index + 1 + n)) break;
            n++;
        }
        n--;

        if (2 * m + 1 > 2 * n + 2) {
            return new int[]{index - m, index + m};
        } else {
            return new int[]{index - n, index + 1 + n};
        }
    }
}
