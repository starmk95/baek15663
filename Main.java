import java.util.*;

public class Main {
    static int[] nums;
    static int[] ans;
    static int[] cnt;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        nums = new int[n]; // 중복되는 수를 1개로 정리한 수열
        ans = new int[m]; // 결과 저장할 배열
        cnt = new int[n]; // 각 수열의 수의 중복이 몇 개가 있는지 저장한 배열
        int[] temp = new int[n];
        for (int i=0;i<n;i++) {
            temp[i] = sc.nextInt();
        }
        Arrays.sort(temp);
        int x = temp[0];
        int index = 0;
        int c = 1;
        for (int i=1;i<n;i++) {
            if (x == temp[i]) {
                c+=1; // 중복되는 수 있음
            } else { // 중복되는 수가 더 이상 없고, 새로운 수가 들어오면
                nums[index] = x; // 해당 수를 배열에 저장하고
                cnt[index] = c; // 같은 수가 총 몇개가 있었는지 저장
                c = 1; // 다음 수의 검사를 위해 1로 초기화
                index+=1; // 다음 수로 접근 할 수 있게 인덱스로 사용하는 변수 +1
                x = temp[i]; // 다음 수를 검사하기 위해 x에 자장
            }
        }
        // 마지막 수는 배열에 저장 처리가 안되므로 반복문 밖에서 따로 처리
        nums[index] = x;
        cnt[index] = c;
        n = index+1; // 인덱스+1을 해주어야 중복을 제거한 수들의 총 개수가 된다.
        makeSeq(n, m, 0);
        System.out.print(sb);
    }
    // 브루트 포스
    static void makeSeq(int n, int m, int index) {
        if (index == m) {
            for (int x : ans) {
                sb.append(x + " ");
            }
            sb.append("\n");
            return;
        }
        for (int i=0;i<n;i++) {
            if (cnt[i] > 0) {
                ans[index] = nums[i];
                cnt[i]-=1;
                makeSeq(n, m, index+1);
                cnt[i]+=1;
            }
        }
    }
}
