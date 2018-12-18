class Solution {
    public int[] getModifiedArray1(int length, int[][] updates) {
        int[] res = new int[length];
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int[] u : updates) {
            tm.put(u[0], tm.getOrDefault(u[0], 0) + u[2]);
            tm.put(u[1] + 1, tm.getOrDefault(u[1] + 1, 0) - u[2]);
        }
        
        int sum = 0, idx = 0;
        for (int key : tm.keySet()) {
            if (key >= length) break;
            while (idx < key) 
                res[idx++] = sum;
            // int change = tm
            sum += tm.get(key);
            res[idx++] = sum;
        }
        
        while (idx != length) {
            res[idx] = sum;
            idx++;
        }
        return res;
    }

    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        int[] changes = new int[length]; 
        
        for (int[] u : updates) {
            changes[u[0]] += u[2];
            if (u[1] != length - 1)
                changes[u[1] + 1] -= u[2];
        }
        
        int sum = 0;
        for (int idx = 0; idx < length; idx++) {
            sum += changes[idx];
            res[idx] = sum;
        }
        return res;
    }

    public int[] getModifiedArray(int length, int[][] updates) {

            int[] res = new int[length];
             for(int[] update : updates) {
                int value = update[2];
                int start = update[0];
                int end = update[1];
                
                res[start] += value;
                
                if(end &lt; length - 1)
                    res[end + 1] -= value;
                
            }
            
            int sum = 0;
            for(int i = 0; i &lt; length; i++) {
                sum += res[i];
                res[i] = sum;
            }
            
            return res;
        }

}


