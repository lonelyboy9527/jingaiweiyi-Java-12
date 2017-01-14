package cc.openhome;

import java.util.Iterator;

public class MyClass3 {
	// 定义规则表示式
	public static void exp1() {
		System.out.println("exp1 -> 定义规则表示式(俗称正则表达式)");

		/* 例子：
		 * 
		 * 如果你有一个字符串，想根据某个字符 或字符串切割，可以使用 String的 split()方法，
		 * 它会返回切割后各个子字符串组成的 String数组。
		 * 
		 * split方法里多了两个斜杠，如果去掉斜杠后，运行，会发现，没有任何结果，
		 * 其实split(String regex)方法中的regex是一个正则表达式，
		 * 而在正则表达式中有其他的意思，所以需要进行转义才可以用
		 * 
		 * */
		
//		Split();
		
		/* 上面 根据Tab切割时为何 \\t ?
		 * 这就需要规则表示式如何定义：
		 * */
		
		/* <1>.字面意义字符 (一些转义字符)*/
//		SplitDemo();
		
		/* <2>.字符类 (一些正则表达式)*/
//		SplitDemo2();
		
		/* <3>.贪婪、逐步、独吞吐量*/
		SplitDemo3();
	} 
	public static void Split() {
		System.out.println("Split -> 切割字符串");
		String tokens = "TANGFEI,ZHANGJINGYI,AINIO,WOAINI";
		// 根据逗号切割
		for (String token : tokens.split(",")) {
			System.out.println(token);
		}
		System.out.println("************************");
		// 根据AI切割
		for (String token : tokens.split("AI")) {
			System.out.println(token);
		}
		System.out.println("************************");
		// 根据Tab切割
		for (String token : "Jsadlkfj\tAjkl\tIfga".split("\\t")) {
			System.out.println(token);
		}
		System.out.println("************************");
	}
	public static void SplitDemo() {
		System.out.println("SplitDemo -> 字面意义字符 (一些转义字符)");
		
		String myString = "\\+";
		System.out.println(myString);
		/* 在C语言中:
		 * 转义字符中的'\'表示它后面的字符已失去它原来的含义，转变成另外的特定含义。
		 * 反斜杠与其后面的字符一起构成一个特定的字符。
		 * 
		 * 细心的读者可能已经发现，转义字符"\\"代表的反斜杠"\"、
		 * 转义字符"\'"代表的字符"'"
		 * 转义字符"\＂"代表的字符"＂"，其本身就是可显示字符，为什么还要对它转义呢?
		 * 
		 * 这是因为它们的原有的字符形式已作它用，其中，
		 * 单引号"'"用作区分字符常量的括号，双引号"""用作区分字符串(下面将要介绍字符串)的括号，
		 * 而反斜杠本身已用来表示转义字符的开头，因此必须对它们用转义字符重新声明。
		 * 
		 * 例子：
		 * 因为有些字符在规则标中有特别的意义。如：
		 * 
		 * ! $ ~ * ( ) + = { } [ ] | \ : 等，若要比较这些，需要加上忽略符号 "\\"，需要对它们重新声明，则需要加上 "\"
		 * 
		 * 但是按照 Java字符串对规定，必忽略"\+"的 "\"，所以必须写为 "\\+"
		 * Java的字符串会忽略第一个"\" 后面正常用 "\+"。
		 * */
		String string = "JUHGH+ksdkg+sdfhg+KKKHS";
		// 加忽略字符切割
		for (String token : string.split("\\+")) {
			System.out.println(token);
		}
		System.out.println("************************");
		
		/* 记得，规则表示式是规则表示式，在Java中要将规则表示式在 "" 中是另一回事。
		 * 
		 * 在Java中，使用字符串撰写规则表达式时，先写下规则表示式，再在每个 \ 前加 \
		 * */	
	}
	
	public static void SplitDemo2() {
		System.out.println("SplitDemo2 -> 字符类(按照正则表达式切割)");
		
		/* <2>.字符类
		 * 
		 * 规则表示式中，多个字符可以归在一起，成为一个字符类（Character class）， 
		 * 字符类会比较文字中是否有 “任一个” 字符符合字符类中某个字符。
		 * */
		String string = "JUHGH+ksdkg+sdfhg+KKKHS";
		for (String token : string.split("[JdS]")) {
			System.out.println(token);
		}
		System.out.println("************************");
		
		/* 字符类中可以使用连字符-， 
		 * 例如： 要比较文字中是否有1到5的数字，规则表示式为 [1-5]；
		 * 		 要比较文字中是否有 a到 z的字母，可以写成 [a-z]；
		 * 		 要比较1到5，a到z，M到W，可以写为 [1-5a-zM-W]；
		 * 
		 *  [^]则为反字符类，[^abc]会比较a，b，c以外的字符。
		 *  
		 *  [a-z&&[^bc]]  等于 [ad-z]，a到z而且不是bc到任一字符。
		 *  
		 *  
		 *  有些字符很常用，例如经常会比较是否为 0-9的数字，我们撰写为 \d，这个称为 预定义字符类。
		 *  java.util.regex.Pattern文件说明中，列出了一些可用的字符类，可以参考。
		 * */
	}
	
	public static void SplitDemo3() {
		System.out.println("SplitDemo3 -> 贪婪、逐步、独吞吐量");
		/* <3>.贪婪、逐步、独吞吐量
		 * 
		 * （1）.贪婪量词：{n}。
		 * 如果输入手机号 xxxx-xxxxxxx，可以写成 \d{4}-\d{7}
		 * 
		 * 例如：
		 * X?：X出现一次或没有
		 * X*：X出现0次或多次
		 * X+：X出现一次或多次
		 * X{n}：X出现n次
		 * X{n,}：X至少出现n次（>=n）
		 * X{n,m}：X出现n但不超过m次 { n<=次数<m}
		 * 
		 * */
	}
}
