package cc.openhome;

import java.util.ResourceBundle;
import java.util.Locale;
public class MyClass2 {
	// 关于i18n
	public static void exp1() {
		System.out.println("exp1 -> 关于i18n(国际化)");
		/* 在讨论Java如何处理 i18n之前，必须先了解Java中如何处理 非西欧字符，
		 * 这在 4.4.3节中已经谈过，建议先回顾一下 4.3.3节的内容，
		 * 再来了解 java.util.ResourceBundle 与 java.util.locale的使用。
		 * */
		
		/* <1>.使用 ResourceBundle*/
		ResourceBundle();
		
		/* <2>使用 Locale*/
		Locale();
	}
	public static void ResourceBundle() {
		System.out.println("ResourceBundle -> 使用 ResourceBundle来做信息绑定");
		/* <1>.使用 ResourceBundle
		 * 
		 * 在程序中有很多字符串信息会被写死在程序中，如果想要改变某个字符串信息，必须修改程序代码重新编译。
		 * 例子：System.out.println("HelloWorld!") 中的 HelloWorld!
		 * 
		 * 对于日后可能变动的文字信息，可以考虑将信息移至程序之外，方法是使用 ResourceBundle来做信息绑定。
		 * 
		 * 首先：要准备一个 .properties文档，如 message.properties。
		 * 文档内容如下：
		 * cc.openhome.welcome=Hello
		 * cc.openhome.name=World
		 * 文档中撰写的是键值配对。
		 * 
		 * */
		ResourceBundle res = ResourceBundle.getBundle("cc.openhome.class2.message"); // 注意：如果是放在包下，需要加 包名
		System.out.print(res.getString("cc.openhome.welcome"));
		System.out.print(res.getString("cc.openhome.name") + "!");
	}
	
	public static void Locale() {
		System.out.println("Locale -> 地区信息");
		System.out.println("国际化的三个重要 概念是 地区信息（Locale）、资源包（Resource bundle）与基础名称（base name）");
		/* <2>使用 Locale
		 * 
		 * 国际化的三个重要 概念是 地区信息（Locale）、资源包（Resource bundle）与基础名称（base name）
		 * 
		 * 地区信息代表了特定的地理、政治或文化区，地区信息可由一个语言编码（Language code）与可选的地区编码 （Country code）来指定。
		 * 其中语言编码是 ISO-639定义，由两个消协字母代表，
		 * 
		 * 如 ca代表加拿大文字，zh代表中文。
		 * 地区编码则由两个大写字母表示，定义在ISO-3166，如IT表示意大利， 表示台湾
		 * 
		 * 地区信息的对应类是 Locale，在建立 Locale实例时，可以指定语言编码与地区编码。
		 * 
		 * 例子：建立代表台湾繁体中文的 Locale：
		 * */
		
		 Locale locale = new Locale("zh", "TW");
		 Locale locale2 = Locale.getDefault();
		 System.out.println("默认Locale: " + locale2.toString());
		 
		 /* 资源包中 包括了特定的地区相关信息，前面介绍的 ResourceBundle对象，就是 JVM中资源包的代表对象。
		  * 
		  * 例如：ResourceBundle的 getBundle() 若仅指定 message，会尝试默认 Locale(由 Locale.getDefault()取得的对象) 取得 .properties文档。
		  * 
		  * 例如：若默认Local代表 zh TW，则 ResourceBundle的 getBundle()若指定 message，则会尝试取得 message_zh_TW.properties文档中的信息。
		  * 若找不到，再尝试找 message.properties文档中的信息。 
		  * 
		  * 注意：在 message.properties中撰写中文，必须使用 Unicode 编码，（可以在 .txt写好，使用 JDK工具 native2ascii来协助转换，再放进工程中。）
		  * */
	}
}
