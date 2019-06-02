package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MathUtil {
    /**
     * test
     * @param args
     */
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        String astr=sc.next();
        char[]a = astr.toCharArray();
        String bstr=sc.next();
        char[]b = bstr.toCharArray();
        char[]result = bigSubBig(a,b);
        System.out.println(String.valueOf(result));
    }
    /**
     *  计算a-b
     * @param a
     * @param b
     * @return
     */
    public static char[]bigSubBig(char[]num1,char[]num2){
        int[]sum = new int[410];
        int len1 = num1.length;
        int len2 = num2.length;
        int blag = 0;
        int i;
        if(len1<len2){
            blag = 1;
        }else if(len1==len2){
            for(i = 0; i < len1; i++)
            {
                if(num1[i] == num2[i])
                    continue;
                if(num1[i] > num2[i])
                {
                    blag = 0;
                    break;
                }
                else
                {
                    blag = 1;
                    char[]temp;
                    temp = num1;
                    num1 = num2;
                    num2 = temp;
                    break;
                }
            }
        }
        int len = len1>len2 ? len1 : len2;
        int j;
        int []n2 = new int[420];
        for (i = len1-1, j = 0; i >= 0; i--, j++)
            sum[j] = num1[i] - '0';

        for (i = len2-1, j = 0; i >= 0; i--, j++)
            n2[j] = num2[i] - '0';
        for (i = 0; i <= len; i++)
        {
            sum[i] = sum[i] - n2[i];
            if (sum[i] < 0)
            {
                sum[i] += 10;
                sum[i+1]--;
            }
        }
        char[]result = new char[num1.length];
        int k=0;
        boolean bStartOutput = false;
        for(int ix=sum.length-1;ix>=0;ix--){
            if( bStartOutput){
                result[k] = (char)(sum[ix]+'0');
                k++;
            }else if(sum[ix]!=0){
                bStartOutput = true;
                result[k] = (char)(sum[ix]+'0');
                k++;
            }
        }
        return result;
    }
    /**
     * 计算a·b
     * @param a
     * @param b
     * @return
     */
    public static char[]bigMultiBig(char[]a,char[]b){
        int[]result = new int[410];
        int i = 0;
        int[]a1=new int[a.length];
        for(int j=a.length-1;j>=0;j--){
            a1[i++]=(char)(a[j]-'0');
        }
        int[]b1=new int[b.length];
        i=0;
        for(int j=b.length-1;j>=0;j--){
            b1[i++]=(char)(b[j]-'0');
        }
        for(int x=0;x<b.length;x++)
            for(int y=0;y<a.length;y++)
                result[x+y] += a1[y]*b1[x];
        for(int m=0;m<200*2;m++)                             //处理进位
        {
            if(result[m]>=10)
            {
                result[m+1]+=result[m]/10;
                result[m]%=10;
            }
        }
        boolean bStartOutput = false;
        List<Character> list = new ArrayList<>();
        int k=0;
        for(int m=200*2;m>=0;m--){
            if( bStartOutput)
            {
                list.add((char)(result[m]+'0'));
                k++;
            }else if(result[m]!=0){
                list.add((char)(result[m]+'0'));
                k++;
                bStartOutput = true;        //有效结果数字标志量
            }
        }
        if(! bStartOutput )//防止乘积为0
            list.add('0');
        char[]resultnumber = new char[list.size()];
        k=0;
        for(char c:list)
        {
            resultnumber[k] = c;
            k++;
        }
        return resultnumber;
    }
    public static long mul(long a,long b,long c){
            long res=0;
            a = a%c;
            while(b!=0){
                if((b & 1)!=0)
                    res = res + a;
                if(res >= c)
                    res = res-c;
                b = b/2;
                a = a*2;
                if(a >= c)
                    a=a-c;
            }
            return res;
    }

    /**
     * 计算 a^b mod c
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static long mulmod(long a,long b,long c){
        long ans = 1;
        a = a%c;
        while (b!=0){
            if(b%2==1)
                ans = mul(ans,a,c);
            a = mul(a,a,c);
            b /= 2;
        }
        return ans;
    }
}