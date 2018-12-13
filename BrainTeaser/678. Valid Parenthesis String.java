class Solution {
    public boolean checkValidString(String s) {
        int lowerBound = 0, upperBound = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                lowerBound++;
                upperBound++;
            } else if (ch == ')') {
                lowerBound -= lowerBound > 0 ? 1 : 0;
                upperBound--;
            } else {
                lowerBound -= lowerBound > 0 ? 1 : 0;
                upperBound++;
            }
            if (upperBound < 0) return false;
        }
        return lowerBound == 0;
    }
}