class NumArray {
    class SegmentTreeNode {
        SegmentTreeNode left;
        SegmentTreeNode right;
        int start;
        int end;
        int sum;
        
        public SegmentTreeNode(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }
    
    public SegmentTreeNode build(int[] nums, int start, int end) {
        if (start < 0 || end >= nums.length || end < start) return null;
        if (start == end) return new SegmentTreeNode(start, start, nums[start]);
        
        int mid = start + (end - start) / 2;
        SegmentTreeNode left = build(nums, start, mid);
        SegmentTreeNode right = build(nums, mid + 1, end);
        SegmentTreeNode root = new SegmentTreeNode(start, end, left.sum + right.sum);
        root.left = left;
        root.right = right;
        return root;
    } 
        
    SegmentTreeNode root;    
    public NumArray(int[] nums) {
        int n = nums.length;
        this.root = build(nums, 0, n - 1);
    }
    
    public void update(int i, int val) {
        updateHelper(root, i, val);
    }
    
    private void updateHelper(SegmentTreeNode curr, int i, int val) {
        if (curr == null) return;
        if (i < curr.start || i > curr.end) return;
        
        if (curr.start == i && curr.end == i) {
            curr.sum = val;
            return;
        }
        updateHelper(curr.left, i, val);// not root ! is curr!!!!!!!
        updateHelper(curr.right, i, val);
        curr.sum = curr.left.sum + curr.right.sum;
    }


    
    public int sumRange(int i, int j) {
        return sumRangeHelper(root, i, j);
    }
    
    private int sumRangeHelper(SegmentTreeNode curr, int i, int j) {
        if (j < curr.start || i > curr.end || i > j) return 0;

        i = Math.max(curr.start, i);
        j = Math.min(curr.end, j);

        if (i == curr.start && j == curr.end) {
            return curr.sum;
        }
            

        int left = sumRangeHelper(curr.left, i, j);
        int right = sumRangeHelper(curr.right, i, j);
        return left + right;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */