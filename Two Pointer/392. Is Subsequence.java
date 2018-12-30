class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int sIdx = 0;
        int tIdx = 0;
        for ( ; sIdx < s.length(); sIdx++) {
            tIdx = t.indexOf(s.charAt(sIdx), tIdx) + 1;
            if (tIdx == 0) return false;
        }
        return true;
    }
    
    public boolean isSubsequence1(String s, String t) {
        if (s.length() == 0) return true;
        int sIdx = 0;
        int tIdx = 0;
        for ( ; tIdx < t.length(); tIdx++) {
            if (t.charAt(tIdx) != s.charAt(sIdx)) continue;
            if ((++sIdx) == s.length()) return true;
        }
        return false;
    }
}