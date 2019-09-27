package algorithm;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static utils.UtilChar.toChar;

public class Caesar {
    private static int v;//模式 0:加密 1:解密
	/**
	 * @param cipher 要破解的密文
	 * @return 明文
	 */
	public static char[] crack(char[] cipher,int mode){
        char[] plaintext;
		HashMap<Character,Integer> map = new HashMap<>();
		//统计
		for (char c : cipher) {
			if (null == map.get(c)) {
				map.put(c, 1);
			} else {
				int k = map.get(c);
				map.put(c, k + 1);
			}
		}
		//查找最大值
		char m='A';
		int mcount = 0 ;
		for(Map.Entry<Character,Integer> entry:map.entrySet()){
			int i = entry.getValue();
			if(i>mcount){
				mcount = i;
				m = entry.getKey();
			}
		}
		//char key = (char)(m-'E'+'A');
		int key = m-'E';

		if(mode==0)
        	return plaintext = decrypt(cipher,key);
		else{
			key=key+'A';
			return new char[]{(char)key};
		}
    }

    /**
     *	检查用户输入的是否含有除了空格,a-z,A-Z以外的其他字符
     *	@param str 待检查的字符串
	 *	@param v 模式
     *  @return 格式是否错误
     */
    private static boolean error(int v , char[]str){
		boolean wrongformat=false;
		if(v==0){
			for (char c : str) {
				boolean isalpha = c >= 'a' && c <= 'z';
				boolean isAlpha = c >= 'A' && c <= 'Z';
				//如果不是'a'-'z' 'A'-'Z'
				if (!(isalpha || isAlpha || c == ' ' || c == 9)) {
					System.out.println("输入错误");
					wrongformat = true;
					break;
				}
			}
		}
		if(v==1){
			for (char c : str) {
				boolean isAlpha = c >= 'A' && c <= 'Z';
				if (!isAlpha) {
					wrongformat = true;
					System.out.println("输入的密文应该是A-Z");
					break;
				}
			}
		}
        return wrongformat;
    }
    /**
     * 凯撒密码加密算法:先将明文数组去掉空格，然后再将明文统一成大写,最后对数组加密
     * @param plaintexts 输入明文数组（只包含a-z A-Z）
     * @return encrptlength(密文数组长度en)
     */
    public static char[] encrypt(char[] plaintexts,int key){
    	char []enTable = new char[26];
    	for(int i=0;i<26;i++)
		{
			enTable[i] = (char)((i+key)%26+'A');
		}
    	char []cipher = new char[plaintexts.length];
    	for(int i=0;i<plaintexts.length;i++){
    		char c = plaintexts[i];
			cipher[i] = enTable[c-'A'];
		}
		return cipher;
    }

	/**
	 *
	 *  @Date (2019;4;16;15;35)
	 * @param cipher 待解密密文
	 * @param key 密钥
	 */
	public static char[] decrypt(char[] cipher,int key){
		char[]plaintext = new char[cipher.length];
		char []detable = new char[26];

		for(int j=0;j<26;j++) {
			detable[j] = (char) ((j - key + 26) % 26 + 'A'); //key是整数 key-'A'
		}
		/*
			for(int j=0;j<26;j++)
				detable[j] = (char)((j-key+26)%26+'A');
		*/
		for(int i=0;i<plaintext.length;i++){
			char plainchar = detable[cipher[i]-'A'] ;
			plaintext[i] = plainchar;
		}
		return plaintext;
	}
	/**
 	* @param args   args[0]'-e' 加密，'-d' 解密,'-c' 破解 ,args[1]  加密时明文 解密时密文
 	*/
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        String plaintext;
	    String mod = args[0];
		if(mod.equals("-c")){
			String s = sc.next();
			System.out.println(crack(toChar(s),0));
		}else
		{
			if(mod.equals("-e")){
				int key = Integer.valueOf(args[1]);
				//key = sc.nextInt();
				v = 0;
				//plaintext = sc.nextLine();
				plaintext = args[2];
				if(error(v,plaintext.toCharArray()))
					return;
				char []newplain = plaintext.replaceAll("\\s*","").toCharArray();//去掉空格和tab
				if(newplain.length<20){
					char[] cipher = encrypt(newplain,key);
					System.out.println(cipher);
				}else{
					System.out.println("长度应该小于20");
				}
			}
			else if(mod.equals("-d")){
				v=1;
				int key = Integer.valueOf(args[1]);
				char kchar = (char) (key+'A');
				char []plain = decrypt(toChar(args[2]),kchar);
				System.out.println(plain);
			}
		}
    }
}