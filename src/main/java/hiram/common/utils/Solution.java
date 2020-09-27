package hiram.common.utils;

/**
 * @Author: HiramHe
 * @Date: 2020/9/26 20:08
 * @Description: ""
 */
public class Solution {

    public static void main(String[] args) {
        String str = "aa___b_c__d";

        int i = 0;
        StringBuffer sb = new StringBuffer();
        while(i<str.length()){
            char ch = str.charAt(i);
            sb.append(ch);
            boolean flag = false;
            while(i<str.length() && str.charAt(i)=='_'){
                i++;
                flag = true;
            }
            if(flag){
                continue;
            }
            i++;
        }

        System.out.println(sb.toString());
    }
}
