class Solution {
    public int solution(int[][] sizes) {
        int width;
        int height;
        if (sizes[0][0] < sizes[0][1]) {
            width = sizes[0][0];
            height = sizes[0][1];
        } else {
            width = sizes[0][1];
            height = sizes[0][0];
        }

        for(int i=1; i<sizes.length; i++) {
            if (sizes[i][0] < sizes[i][1]) {
                width = Math.max(width, sizes[i][0]);
                height = Math.max(height, sizes[i][1]);
            } else {
                width = Math.max(width, sizes[i][1]);
                height = Math.max(height, sizes[i][0]);
            }
        }
        return width*height;
    }
}