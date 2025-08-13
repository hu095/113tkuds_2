import java.util.*;

public class ValidMaxHeapChecker {
    public static boolean isValidMaxHeap(int[] arr){
        return firstViolationIndex(arr)==-1;
    }

    // 依題意回傳「第一個違規的子節點索引」，若無違規回 -1
    public static int firstViolationIndex(int[] arr){
        if(arr==null || arr.length<=1) return -1;
        int n = arr.length, lastParent = (n-2)/2;
        for(int p=0; p<=lastParent; p++){
            int L = 2*p+1, R = 2*p+2;
            if(L<n && arr[L] > arr[p]) return L;
            if(R<n && arr[R] > arr[p]) return R;
        }
        return -1;
    }

    public static void main(String[] args){
        int[][] tests = {
            {100,90,80,70,60,75,65},
            {100,90,80,95,60,75,65},
            {50},
            {}
        };
        for(int[] t: tests){
            int bad = firstViolationIndex(t);
            System.out.println(Arrays.toString(t) + " → " + (bad==-1) +
                (bad==-1?"" : " (索引"+bad+"的"+t[bad]+" 大於父節點 "+t[(bad-1)/2]+")"));
        }
    }
}
