package hiram.common.utils;

/**
 * @Author: HiramHe
 * @Date: 2020/9/26 20:33
 * @Description: ""
 */
public class Solution1 {

    public static void main(String[] args) {
        int n = 23;
        StringBuffer sb = new StringBuffer();
        while(n>0){
            boolean flag = true;
            for(int i=2;i<=Math.sqrt(n);i++){
                if(n%i==0){
                    sb.append(i+"*");
                    n = n/i;
                    flag = false;
                    break;
                }
            }

            if (flag){
                sb.append(n+"");
                break;
            }
        }
        System.out.println(sb.toString());
    }
}
