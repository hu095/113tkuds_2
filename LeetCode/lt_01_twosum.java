import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class lt_01_twosum {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Example 1:");
        System.out.println("Input: " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Output: " + Arrays.toString(sol.twoSum(nums1, target1)));
        System.out.println();

        // Example 2
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        System.out.println("Example 2:");
        System.out.println("Input: " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + Arrays.toString(sol.twoSum(nums2, target2)));
        System.out.println();

        // Example 3
        int[] nums3 = {3, 3};
        int target3 = 6;
        System.out.println("Example 3:");
        System.out.println("Input: " + Arrays.toString(nums3) + ", target = " + target3);
        System.out.println("Output: " + Arrays.toString(sol.twoSum(nums3, target3)));
        System.out.println();
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
