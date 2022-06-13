import org.springframework.web.bind.annotation.RestController;
import org.testng.annotations.Test;

import java.util.Stack;

/**
 * @author minzhang
 * @date 2022/04/06 22:58
 **/
public class StringTestDemo {


    @Test
    public  void test1(){

        String s = "{[()()]}";
        System.out.println(isLegal(s));
    }


    @Test
    public void test2() throws ClassNotFoundException {
        // getClass()需要获取Class对象对应的对象的引用。是根类Objecct的函数
        User compareMethod = new User();
        Class c1 = compareMethod.getClass();

        Class c2 = null;
        try {
            // forName()是Class类的static方法，参数是全限定名，会抛出ClassNotFoundException异常
            c2 = Class.forName("User");
        } catch (ClassNotFoundException e) {
            System.out.println("not find class");
            System.exit(1);
        }

        // getName()函数是Class类中的一个static方法，返回该Class对象的全限定名字符串
        System.out.println("I'm c1--------- name of class: " + c1.getName());
        System.out.println("I'm c2--------- name of class: " + c2.getName());

        // 通过.equals()函数得出c1和c2两个对象的内容是相同的
        // == 来比较c1和c2的地址，相同，由此得出getName()和forName()函数返回的是同一个Class对象的引用
        System.out.println("compared content between c1 and c2: " + c1.equals(c2));
        System.out.println("compared address between c1 and c2: " + (c1 == c2));

    }




    private static int isLeft(char c) {
        if (c == '{' || c == '(' || c == '[') {
            return 1;
        } else {
            return 2;
        }
    }
    private static int isPair(char p, char curr) {
        if ((p == '{' && curr == '}') || (p == '[' && curr == ']') || (p == '(' && curr == ')')) {
            return 1;
        } else {
            return 0;
        }
    }
    private static String isLegal(String s) {
        Stack stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (isLeft(curr) == 1) {
                stack.push(curr);
            } else {
                if (stack.empty()) {
                    return "非法";
                }
                char p = (char) stack.pop();
                if (isPair(p, curr) == 0) {
                    return "非法";
                }
            }
        }
        if (stack.empty()) {
            return "合法";
        } else {
            return "非法";
        }
    }

}
