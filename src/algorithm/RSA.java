package algorithm;
import java.math.BigInteger;

import static utils.MathUtil.*;
import static utils.UtilChar.toChar;

public class RSA {
    private char[]p;
    private char[]q;

    public static char[] getN() {
        return n;
    }

    private static char[]n;
    private static char[]e;

    public static char[] getE() {
        return e;
    }

    public static char[] getD() {
        return d;
    }

    private static char[]d;
    private static char[]phi;
    public RSA(char[]p,char[]q,char[]e){//传入两个大素数
        this.q=q;
        this.p=p;
        //计算n
        n = bigMultiBig(p,q);
        char[]one = new char[1];
        one[0] = '1';
        //计算phi
        char[]pone = bigSubBig(p,one);
        char[]qone = bigSubBig(q,one);
        phi = bigMultiBig(pone,qone);
        //随机选取e 取e为质数65537\
        this.e = e;
        BigInteger ee = new BigInteger(String.valueOf(e));
        //计算d 即e对于phi的模反元素
        BigInteger newD = ee.modPow(new BigInteger("-1"),new BigInteger(String.valueOf(phi)));
        d = newD.toString().toCharArray();
    }
    public static void getParemeters(RSA rsa){
        System.out.println("RSA参数如下 ");
        System.out.println("p "+String.valueOf(rsa.p));
        System.out.println("q "+String.valueOf(rsa.q));
        System.out.println("phi "+String.valueOf(phi));
        System.out.println("e "+String.valueOf(e));
        System.out.println("d "+String.valueOf(d));
        System.out.println("n "+String.valueOf(n));
    }
    public static void alphaTable(RSA r){
        for(int i=0;i<26;i++){
            long c = r.encrypt((char)('a'+i),e,n);
            System.out.println("密文 "+c);
            System.out.println("明文 "+decrypt(c,d,n));
            System.out.println();
        }
    }
    public static void main(String[]args){
        char []p = "885320963".toCharArray();
        char []q = "238855417".toCharArray();
        RSA r = new RSA(p,q,"1001".toCharArray());
        //getParemeters(r);
        char[]plaintext = toChar("china is beautiful");
        long []cipher = encrypt(plaintext,e,n);
        char[]decipher = decrypt(cipher,d,n);
        System.out.println(String.valueOf(decipher));
    }

    /**
     * 对单字符m进行加密
     * @param m
     * @param e
     * @param n
     * @return cipher
     */
    public static long encrypt(char m,char[]e,char[]n){  //m^e mod n
        long a = m;
        long b = Long.parseLong(String.valueOf(e));
        long c = Long.parseLong(String.valueOf(n));
        long cipher = mulmod(a,b,c);
        return cipher;
    }

    /**
     * 对cipher进行解密
     * @param cipher
     * @param d
     * @param n
     * @return
     */
    public static char decrypt(long cipher,char[]d,char[]n){//用私钥d进行解密
        long a = cipher;
        long b = Long.parseLong(String.valueOf(d));
        long c = Long.parseLong(String.valueOf(n));
        long plainchar = mulmod(a,b,c);
        return (char)(plainchar);
    }

    /**
     * 用公钥对一个明文字符串加密
     * @param plaintext
     * @param e
     * @param n
     * @return
     */
    public static long[] encrypt(char[]plaintext,char[]e,char[]n){//用公钥对(e,n)进行加密
        long[]cipher = new long[plaintext.length];
        for(int i=0;i<plaintext.length;i++){
            cipher[i] = encrypt(plaintext[i],e,n);
        }
        return cipher;
    }

    /**
     * 对一个密文字符数组用私钥d，p，q解密
     * @param cipher
     * @param d
     * @param n
     * @return
     */
    public static char[] decrypt(long []cipher,char[]d,char[]n){
        char[]plainttext = new char[cipher.length];
        for(int i=0;i<plainttext.length;i++){
            plainttext[i] = decrypt(cipher[i],d,n);
        }
        return plainttext;
    }
}