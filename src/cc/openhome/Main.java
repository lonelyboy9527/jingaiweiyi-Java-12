package cc.openhome;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		exp1();
		exp2();
	}
	/* ********************12.2 国际化基础、日期******************** */
	public static void exp2() {
		/* 日期的取得 可以使用 java.util.Date与 java.util.Calendar，
		 * java.text.DateFormat 可以对日期做格式化。
		 * 本节将介绍这几个类对使用。
		 * 
		 * 本地化：应用程序根据不同地区用户，呈现不同语言，日期格式等称为 本地化。
		 * 
		 * 国际化（i18n）：如果应用程序设计时，可在不修改应用程序情况下，根据不同用户直接采用不同语言，日期格式等，这样的设计考虑称为国际化，简称 i18n (因为 internationlization有18个字母。) 
		 * */
//		MyClass2.exp1(); // 12.2.1 关于i18n
//		MyClass2.exp2(); // 12.2.2 使用 Date与DateFormat
		MyClass2.exp3(); // 12.2.3 使用 Calendar
	}
	/* ********************12.1 日志******************** */
	public static void exp1() {
		/* 系统中有许多值得记录的信息，例如捕捉异常之后，
		 * 有些异常值得显示给用户观看就抛出，而对于开发人员或系统人员才有意义的异常，可以记录下来，
		 * 
		 * 那么该记录哪些信息（时间、信息产生处等）？
		 * 用何种方式记录（文档、数据库、远程主机等）？
		 * 记录格式（控制台、纯文本、XML等）？
		 * 这些都是记录时值得考虑等要素。
		 * 
		 * Java SE 提供了日志（Logging）API，可以让你基于标准调用使用。
		 * */
//		MyClass1.exp1(); // 12.1.1 日志API简介
//		MyClass1.exp2(); // 12.1.2 指定日志层级
//		MyClass1.exp3(); // 12.1.3 使用 Handler 与 Formatter
//		MyClass1.exp4(); // 12.1.4 自定义 Handler、Formatter与Filter
		MyClass1.exp5(); // 12.1.5 使用 logging.properties
	} 

}
