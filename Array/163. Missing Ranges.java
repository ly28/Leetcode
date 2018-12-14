class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            res.add(getRange(lower, upper));
            return res;
        }
            
        int next = lower, idx = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < next) continue;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] == next) {
                next = nums[i] == Integer.MAX_VALUE ? nums[i] : nums[i] + 1;
                continue;
            } 
            res.add(getRange(next, nums[i] - 1));
            next = nums[i] == Integer.MAX_VALUE ? nums[i] : nums[i] + 1;
        }
        if (next <= upper && nums[nums.length - 1] != next)
            res.add(getRange(next, upper));
        return res;
    }
    
    private String getRange(int a, int b) {
        return a == b ? String.valueOf(a) : a + "->"+ b;
    }
}