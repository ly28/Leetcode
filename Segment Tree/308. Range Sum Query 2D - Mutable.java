class NumMatrix {
    class SegmentTreeNode {
        int start, end;
        int sum;
        SegmentTreeNode left, right;
        public SegmentTreeNode (int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }
    
    SegmentTreeNode root;
    int m, n;
    
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.root = build(matrix, 0, m * n - 1);
    }
    
    private SegmentTreeNode build(int[][] board, int startIdx, int endIdx) {
        if (startIdx < 0 || endIdx >= m * n || startIdx > endIdx) 
            return null;
        if (startIdx == endIdx) 
            return new SegmentTreeNode(startIdx, startIdx, board[startIdx / n][startIdx % n]);
        
        int midIdx = startIdx + (endIdx - startIdx) / 2;
        SegmentTreeNode left = build(board, startIdx, midIdx);
        SegmentTreeNode right = build(board, midIdx + 1, endIdx);
        SegmentTreeNode root = new SegmentTreeNode(startIdx, endIdx, left.sum + right.sum);
        root.left = left;
        root.right = right;
        return root;
    }
    
    public void update(int row, int col, int val) {
        updateHelper(root, row * n + col, val);
        return;
    }
    
    private void updateHelper(SegmentTreeNode curr, int idx, int val) {
        if (curr == null || curr.start > idx || curr.end < idx) return;//////////curr == null!!!!!!
        if (curr.start == idx && curr.end == idx) {
            curr.sum = val;
            return;
        }
        
        updateHelper(curr.left, idx, val);
        updateHelper(curr.right, idx, val);
        curr.sum = curr.left.sum + curr.right.sum;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        if (col1 == 0 && col2 == n - 1) {
            // System.out.println(row1 * n + "," + row2 * n);
            res = getSum(root, row1 * n, row2 * n + n - 1);///row2 * n + n - 1!!!idx 很容易算错！！！
        } else {
            for ( ; row1 <= row2; row1++) {
                res += getSum(root, row1 * n + col1, row1 * n + col2); 
            }
        }
        return res;
    }
    
    private int getSum(SegmentTreeNode curr, int startIdx, int endIdx) {
        if (curr == null || startIdx > endIdx) return 0;
        if (startIdx < 0 || endIdx >= m * n) return 0;
        
        startIdx = Math.max(startIdx, curr.start);
        endIdx = Math.min(endIdx, curr.end);
        
        if (startIdx == curr.start && endIdx == curr.end) return curr.sum;
        
        int left = getSum(curr.left, startIdx, endIdx);
        int right = getSum(curr.right, startIdx, endIdx);
        return left + right;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */