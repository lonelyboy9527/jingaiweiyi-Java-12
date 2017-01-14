package cc.openhome;

import java.util.ResourceBundle;
import java.util.Scanner;

import org.omg.CORBA.Current;

import java.util.Locale;
import java.util.Map;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
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
		 * 首先：要准备一个 .properties文档，如 messages.properties。
		 * 文档内容如下：
		 * cc.openhome.welcome=Hello
		 * cc.openhome.name=World
		 * 文档中撰写的是键值配对。
		 * 
		 * */
		ResourceBundle res = ResourceBundle.getBundle("cc.openhome.class2.messages"); // 注意：如果是放在包下，需要加 包名
		System.out.print(res.getString("welcome"));
		System.out.print(res.getString("name") + "!" + "\n");
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
		 
		 /* ResourceBundle的 getBundle()可以指定 Locale对象，如果这样撰写程序：
		  * 
		  * 注意：使用这种方法，要放在 根目录才行
		  * */
		 Locale locale3 = new Locale("en", "US");
		 ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", locale3);
		 System.out.print(resourceBundle.getString("welcome"));
		 System.out.println(resourceBundle.getString("name")  + "!");
		 
		 System.out.println("使用 ResourceBundle时，如何根据基础名称 取得对应的信息文档:");
		 /* 总结：使用 ResourceBundle时，如何根据基础名称 取得对应的信息文档：
		  * 
		  * （1）：使用指定的Locale对象取得信息文档。
		  * （2）：使用Locale.getDefault()取得的对象取得信息文档。
		  * （3）：使用基础名称取得信息文档。
		  * 
		  * */
	}
	// 使用 Date与DateFormat
	public static void exp2() {
		System.out.println("exp2 -> 使用 Date与DateFormat");
		/* 如果想要取得系统时间，方法之一是使用 System.currentTimeMillis()方法
		 * 返回long类型整数，代表1970年1月1日0分0秒0毫秒至今经过的毫秒数。
		 * 
		 * 可以使用 Date类让这个类变得有意义一些。
		 * 例子：
		 * */
		Date date = new Date(System.currentTimeMillis());
		System.out.println(date);
		
		Date date2 = new Date();
		System.out.println(date2);
		System.out.println(date2.getTime());
		
		System.out.println("使用 DateFormat格式化输出日期：");
		/* 使用 DateFormat格式化输出日期：
		 * 
		 * DateFormat是个抽象类，其操作类是： java.text.SimpleDateFormat。
		 * 可以直接创建SimpleDateFormat实例，或使用 DateFormat的 getDateInstance()、getTimeInstance()、getDateTimeInstance()等静态方法。
		 * 
		 * 例子：
		 * */
//		DateFormat();
		/* 取得 DateFormat实例时，可以指定 Locale实例，这将会将日期时间格式化为指定的 语系显示方式。*/
		
		
		/* 使用 SimpleDateFormat
		 * 直接创建 SimpleDateFormat的好处是，可使用 模式字符串自定义格式。
		 * 
		 * 会依据指定的 Locale设定显示对应语言，
		 * EE：表示星期格式设定
		 * MM：表示月份格式设定
		 * dd：表示日期格式设定
		 * yyyy：表示公元格式设定
		 * */
		try {
			SimpleDateFormat();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		/* 注意 ：同日期格式化DateFormat相似的 还有 NumberFormat。是对数字格式化的*/ 
	}
	public static void DateFormat() {
		System.out.println("DateFormat -> 使用DateFormat格式化日期");
		DateFormat dateFormat = null;
		Date date = new Date();
		
		System.out.println("获取日期：getDateInstance():");
		dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA); // 指定Locale为 CHINA，将以简体中文显示。
		System.out.println("style: LONG: " + dateFormat.format(date));
		dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINA);
		System.out.println("style: SHORT: " + dateFormat.format(date));
		
		
		System.out.println("获取时间：getTimeInstance():");
		dateFormat = DateFormat.getTimeInstance(DateFormat.LONG, Locale.CHINA);
		System.out.println("style: LONG: " + dateFormat.format(date));
		dateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.CHINA);
		System.out.println("style: MEDIUM: " + dateFormat.format(date));
		dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.CHINA);
		System.out.println("style: SHORT: " + dateFormat.format(date));
		
		
		System.out.println("获取日期和时间：getDateTimeInstance():");
		dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CHINA);
		System.out.println("style: LONG LOGN: " + dateFormat.format(date));
		dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.CHINA);
		System.out.println("style: SHORT MEDIUM: " + dateFormat.format(date));
		dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.CHINA);
		System.out.println("style: SHORT SHORT: " + dateFormat.format(date));
	}
	public static void SimpleDateFormat() throws ParseException {
		System.out.println("SimpleDateFormat -> 使用模式字符串自定义格式");
		DateFormat dateFormat = new SimpleDateFormat("EE-MM-dd-yyyy", Locale.CHINA);
		System.out.println(dateFormat.format(new Date()));
		
		/* SimpleDateFormat有个 parse()方法，可以依创建 SimpleDateFormat时指定的格式，
		 * 将指定的字符串剖析为 Date实例。
		 * */
		Scanner scanner = new Scanner(System.in);
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("输入出生年月日(yyyy-MM-dd):");
		Date birth = dateFormat2.parse(scanner.nextLine()); // 会抛出异常。
		Date current = new Date();
		
		long life = current.getTime() - birth.getTime();
		System.out.println("你今年的岁数为: " + (life / (365 * 24 * 3600 * 1000L)));
		
	}
	// 使用 Calendar
	public static void exp3() {
		System.out.println("exp3 -> 使用 Calendar");
		
		/* 可以使用 Date取得完整日期时间
		 * 可以使用 toString()取得日期文字描述
		 * 或使用 DateFormat格式化日期。
		 * 
		 * 但是如果想要取得某个时间或日期信息该如何进行？
		 * 例如：想知道现在时间是6月的第几天。若查看 Date的API文件，
		 * 会发现许多方法都不再建议使用，而建议改用 Calendar的相关方法取代。
		 * 
		 * Calendar是个抽象类，java.util.GregorianCalendar是其子类成果，
		 * 	通常通过 Calendar的 getInstance()来取得 Calendar实例。
		 * 
		 * 	通过实例的 get()方法，可以获取 公元，月，星期等。
		 * 
		 * 例子：想看看今天星期几，显示不同的信息，那该怎么办呢？
		 * */
//		Calendar();
		
		/* 如果想以区域化方式显示星期、月份名称，
		 * 可以使用Calendar的 getDisplayNames() 或 getDisplayName()方法。
		 * 
		 * 例子：
		 * */
		getDisplayName();
	}
	public static void Calendar() {
		System.out.println("Calendar -> 获取年月日、星期及时间");
		Map<Integer, String> days = new HashMap<>();
		days.put(Calendar.MONDAY, "星期一穿新衣");
		days.put(Calendar.TUESDAY, "星期二肚子饿");
		days.put(Calendar.WEDNESDAY, "星期三去爬山");
		days.put(Calendar.THURSDAY, "星期四去考试");
		days.put(Calendar.FRIDAY, "星期五去跳舞");
		days.put(Calendar.SATURDAY, "星期六去溜溜");
		days.put(Calendar.SUNDAY, "星期天乐翻天");
		
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 获取今天的星期
		System.out.println("dayOfWeek: " + dayOfWeek);
		System.out.println(days.get(dayOfWeek));
	}
	public static void getDisplayName() {
		System.out.println("getDisplayName -> 区域化方式显示星期、月份名称等信息");
		Calendar calendar = Calendar.getInstance();
		System.out.println("现在时间是:");
		System.out.println("年份: " + calendar.getDisplayName(Calendar.ERA, Calendar.LONG, Locale.CHINA) + calendar.get(Calendar.YEAR));
		System.out.println("月份: " + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.CHINA));
		System.out.println("日期: " + calendar.get(Calendar.DAY_OF_MONTH)); // 获取今天是一个月中哪一天
		System.out.println("星期: " + calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.CHINA));
		
		/* 注意 get()方法直接获得 数字
		 * 
		 * getDisplayName 可以显示区域化文本 (如公元、星期)
		 * */  
		
		/* 在取得一个 Calendar的实例后，可以使用 add()方法，
		 * 来改变 Calendar的时间
		 * */
		System.out.println("通过 add()方法改变Calendar:");
		calendar.add(Calendar.MONTH, 1); // Calendar的时间加1个月
		calendar.add(Calendar.HOUR, 3); // 加3小时
		calendar.add(Calendar.YEAR, -2); // 减去两年
		calendar.add(Calendar.DAY_OF_WEEK, 3); // 加三天
		System.out.println("年份: " + calendar.getDisplayName(Calendar.ERA, Calendar.LONG, Locale.CHINA) + calendar.get(Calendar.YEAR));
		System.out.println("月份: " + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.CHINA));
		System.out.println("日期: " + calendar.get(Calendar.DAY_OF_MONTH)); // 获取今天是一个月中哪一天
		System.out.println("星期: " + calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.CHINA));
	}
}
