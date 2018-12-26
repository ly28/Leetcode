class Solution {
    public String minWindow(String s, String t) {
        int[] cnts = new int[256];
        for (char ch : t.toCharArray())
            cnts[ch]++;
        
        int start = -1, end = s.length();
        int l = 0, r = 0, count = 0;
        for ( ; r < s.length(); r++) {
            if (--cnts[s.charAt(r)] >= 0) count++;
            if (count < t.length()) continue;
    
            while (count >= t.length()) {
                if (r - l < end - start) {
                    start = l;
                    end = r;
                }
                //cnts[s.charAt(l)]++;
                if (++cnts[s.charAt(l++)] > 0)
                    count--;
            }
        }
        return start == -1 ? "" : s.substring(start, end + 1);
    }
}