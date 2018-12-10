public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{10,13,12,14,15};
        int[] arr1 = new int[]{1,1};
        int[] arr2 = new int[]{0};
        int[] arr3 = new int[]{1,3,2,5,4};
        int[] arr4 = new int[]{10, 13, 12, 14, 15};
        int[] arr5 = new int[]{10, 11, 14, 11, 10};
        System.out.println(getCount(arr));
        System.out.println(getCount(arr1));
        System.out.println(getCount(arr2));
        System.out.println(getCount(arr3));
        System.out.println(getCount(arr4));
        System.out.println(getCount(arr5));


    }

    public static int getCount(int[] nums) {
        if (nums == null)  return 0;
        int n = nums.length;
        if (n < 2) return n;

        int res = 1;
        int[][] cache = new int[2][n];
        for (int i = 0; i < n - 1; i++) {
            if(isValid(1, i, nums, cache)) res++;
        }
        return res;
    }

    private static boolean isValid(int step, int idx, int[] nums, int[][] cache) {
        if (idx == nums.length - 1) 
            return true;
        if (cache[step][idx] != 0)
            return cache[step][idx] == 1 ? true : false;
        
        int nextIdx = step == 1 ? oddFind(nums, idx) : evenFind(nums, idx);
        if (nextIdx == -1) {
            cache[step][idx] = -1;
            return false;
        }
        boolean res = isValid(1 - step, nextIdx, nums, cache);
        cache[step][idx] = res ? 1 : -1;
        return res;
    }

    private static int oddFind(int[] nums, int idx) {
        int res = idx + 1;
        int min = Integer.MAX_VALUE;
        for (int i = res; i < nums.length; i++) {
            if (nums[i] <= nums[idx] || nums[i] >= min) continue;
            res = i;
            min = nums[i];
        }
        return nums[res] <= nums[idx] ? -1 : res;
    }

    private static int evenFind(int[] nums, int idx) {
        int res = idx + 1;
        int max = Integer.MIN_VALUE;
        for (int i = res; i < nums.length; i++) {
            if (nums[i] >= nums[idx] || nums[i] <= max) continue;
            res = i;
            max = nums[i];
        }
        return nums[res] >= nums[idx] ? -1 : res;
    }
}