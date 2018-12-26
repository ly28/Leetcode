class Solution {
    public int longestSubstring(String s, int k) {
        if (s.length() < k) return 0;
        int n = s.length();
        int[] cnts = new int[256];
        int l = 0, r = 0, count = 0, kind = 0, maxLen = 0;
        
        for (int i = 1; i <= 26; i++) {
            Arrays.fill(cnts, 0);
            l = r = count = kind = 0;
            for ( ; r < n; r++) {
                if (cnts[s.charAt(r)]++ == 0) 
                    kind++;
                if (cnts[s.charAt(r)] == k)
                    count++;
                if (kind < i) continue;
                
                while (kind > i) {
                    if (cnts[s.charAt(l)]-- == k)
                        count--;
                    if (cnts[s.charAt(l++)] == 0)
                        kind--;
                }
                
                if (count == i) {
                    maxLen = Math.max(maxLen, r - l + 1);
                }
            }
        }
        return maxLen;
    }
}