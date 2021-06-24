/**
 * @author minzhang
 * @date 2021/06/20 17:52
 **/
public class StringTest {

    public static void main(String[] args) {

        searchSameStr();
    }


    /**
     * 查询重复字符串
     */
    static void searchSameStr(){
        String s = "goodgoogle";
        String t = "google";
        int isfind = 0;

        for (int i = 0; i < s.length() - t.length() + 1; i++) {
            if (s.charAt(i) == t.charAt(0)) {
                int jc = 0;
                for (int j = 0; j < t.length(); j++) {
                    if (s.charAt(i + j) != t.charAt(j)) {
                        break;
                    }
                    jc = j;
                    System.out.print(jc);
                }
                System.out.println(t.length() - 1);
                if (jc == t.length() - 1) {
                    isfind = 1;
                }
            }
        }
        System.out.println(isfind);
    }
}
