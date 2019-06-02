package algorithm;
import utils.UtilChar;
import view.MyJFrame;

import java.util.*;
import java.util.stream.IntStream;

import static utils.UtilChar.toChar;

public class Vigenere {
    static class Word{
        String word;
        HashSet<Integer> set = new HashSet<>();//用来记录word在密文出现的位置
        public Word(String word){this.word = word;}
    }
    public static char VEntable[][] = new char[26][26];

    /**
     * 维吉尼亚破解
     * @param cipher 密文
     * @return plaintext 明文
     */
    public static char[] crack(char[] cipher){
        HashSet<Word> index = getIndex(cipher);
        Word[]a = index.toArray(new Word[0]);
        int []xkey = new int[30];
        for(int i=0;i<a.length;i++){
            Integer []num = a[i].set.toArray(new Integer[0]);
            if(num.length<=1)
                continue;
            Integer[]num2 = new Integer[num.length-1];
            //现将num排序,在计算num每两个数的间隔,存入nums
            Arrays.sort(num);
            for(int j=1;j<num.length;j++){
                num2[j-1] = num[j]-num[j-1];
            }
            int key = gcd(num2);
            //System.out.println(a[i].word+" "+gcd(num2));
            if(key<30)
                xkey[key]++;
        }
        xkey[1] = 0;
        int keylength = IntStream.range(0, xkey.length).reduce((i, j) -> xkey[i] > xkey[j] ? i : j).getAsInt();//求最大值所在下标
        //System.out.println(keylength);                                                                        //获得密钥长度
        //分治
        char [][]cToCaesar = split(cipher,keylength);
        Caesar caesar = new Caesar();
        char[]key = new char[keylength];
        for(int i=0;i<keylength;i++){
           char[]ki = caesar.crack(cToCaesar[i],1);
           key[i]=ki[0];
        }
        return decrypt(cipher,key);
    }

    /**
     *
     * @param cipher 密文
     * @param keylength 密钥长度
     * @return xcipher 分治后的密文
     */
    public static char[][] split(char []cipher,int keylength){
        char [][]xcipher = new char[keylength][(cipher.length/keylength)]; //分治为k组
        for(int i=0;i<keylength;i++){
            int k = i;
            for(int j=0;j<xcipher[i].length;j++){
                xcipher[i][j] = cipher[k];
                k+=keylength;
            }
        }
        return xcipher;
    }

    /**
     * 求m ，n最大公约数
     * @param m
     * @param n
     * @return 最大公约数
     */
    private static int gcd(int m,int n){
        if (m < n)
        {
            int tmp = m;
            m = n;
            n = tmp;
        }
        if (n == 0)
            return m;
        else
            return gcd(n, m % n);
    }

    /**
     * 求一组数的最大公约数
     * @param nums 一组数字
     * @return number 最大公约数
     */
    private static Integer gcd(Integer []nums){
        int number = nums[0];
        if(nums.length==1)
            return nums[0];
        for (int i = 1; i < nums.length; ++i)
            number = gcd(number, nums[i]);
        return number;
    }
    private static HashSet<Word> getIndex(char[] cipher) {
        HashSet<Word> set = new HashSet<Word>();
        HashMap<String,Integer> map = new HashMap<>();
        for(int i=0;i<cipher.length-1;i++){
            String s = cipher[i] +""+cipher[i+1];
            Word w = new Word(s);
            if(null==map.get(s)){
                map.put(s,1);
                w.set.add(i);
                set.add(w);
            }else {
                int k = map.get(s);
                map.put(s, k + 1);
                Iterator<Word> iter = set.iterator();
                while (iter.hasNext()){
                    Word w1 = iter.next();
                    if(w1.word.equals(s)){
                        iter.remove();
                        Word w2 = w1;
                        w2.set.add(i);
                        set.add(w2);
                        break;
                    }
                }
            }
        }
        return set;
    }

    /**
     * 维吉尼亚加密算法
     * @param plaintext 明文
     * @param key 密钥
     * @return cipher 密文
     */
    public static char[] encrypt(char[] plaintext,char[] key){
        char cipher[] = new char[plaintext.length];
        int j=0;
        for(int i=0;i<cipher.length;i++){
            cipher[i] = VEntable[plaintext[i]-'A'][key[j]-'A'];
            j = (j+1)%key.length;
        }
        return cipher;
    }

    /**
     * 维吉尼亚解密算法
     * @param cipher 密文
     * @param key 密钥
     * @return
     */
    public static char[] decrypt(char[]cipher,char[]key){
        char plaintext[] =new char[cipher.length];
        int j=0;
        for(int i=0;i<plaintext.length;i++){
            plaintext[i] = VEntable[cipher[i]-'A'][( (-key[j]-'A') %26 + 26) % 26];
            j=(j+1)%key.length;
        }
        return plaintext;
    }

    /**
     * 初始话维吉尼亚代换表
     */
    public Vigenere(){
        int i,j;
        for(i=0;i<26;i++)
            for(j=0;j<26;j++)
                VEntable[i][j] = (char)('A'+(i+j)%26);
    }

    /**
     * 打印代换表
     */
    public static void getTable(){
        int i,j;
        for(i=0;i<26;i++) {
            for (j = 0; j < 26; j++)
                System.out.print(VEntable[i][j]);
            System.out.println();
        }
    }

    public static void main(String[]args){

        new MyJFrame();
        //Vigenere v = new Vigenere();
        //Scanner sc = new Scanner(System.in);
        //String plaintext = sc.nextLine();
        //char []s = toChar(plaintext);
        //        char[] key = toChar(sc.next());
        //        System.out.println(v.encrypt(s,key));
        //        char cipher[] = v.encrypt(s,key);
        //        System.out.println(v.decrypt(cipher,key));
        //        crack(cipher);
    }
}