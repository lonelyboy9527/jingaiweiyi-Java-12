package cc.openhome;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import cc.openhome.class1.Some;

public class MyClass1 {
	// 日志API简介
	public static void exp1() {
		System.out.println("exp1 -> 日志API简介");
		
		/* java.util.logging包提供了日志功能相关类与接口，
		 * 
		 * 它们是从JDK1.4之后加入标准API，不必额外配置日志组件，就可在标准Java平台使用是其好处。
		 * 
		 * 使用日志等起点是Logger类，Logger实例的创建有许多要处理的要素，
		 * Logger类的构造函数标示为 protected，不是java.util.logging同包的类不能直接以 new创建
		 * 要取得Logger实例，必须使用 Logger的静态方法getLogger()。
		 * 
		 * 例如:
		 * 
		 * 第一种方式：
		 * */
		Logger logger = Logger.getLogger("cc.openhome.MyClass1");
		/* 调用 getLogger时，必须指定Logger实例所属的命名空间。*/
		
		/* 通常在哪个类取得Logger，名称空间就会命名为哪个类全名。
		 * 
		 * 第二种方式：
		 * */
		Logger logger2 = Logger.getLogger(MyClass1.class.getName());
		/* 之后谈反射时会介绍，类之后接下 .class可以取得该类的 java.lang.Class实例，
		 * 调用其getName()就可以获得类全名
		 * 
		 * 取得 Logger实例后，可以使用 log()方法输出信息，
		 * 输出信息时可以使用 Level的静态成员指定信息层级。
		 * */
		
		Logger logger3 = Logger.getLogger(MyClass1.class.getName());
		logger3.log(Level.WARNING, "WARNING 信息");
		logger3.log(Level.INFO, "INFO 信息");
		logger3.log(Level.CONFIG, "CONFIG 信息");
		logger3.log(Level.FINE, "FINE 信息");
		
		/*     一月 13, 2017 10:07:43 上午 cc.openhome.MyClass1 exp1
		 * (1).警告: WARNING 信息
		 *     一月 13, 2017 10:07:43 上午 cc.openhome.MyClass1 exp1
		 * (2).信息: INFO 信息
		 * 
		 * 可以看到，除了指定信息外，Logger还会记录时间、类、方法等信息。
		 * 但是我们只看到 WARNING和 INFO的信息呢？为何默认输出在控制台？
		 * 
		 * 简单来说，Logger是记录信息的起点，
		 * 要输出的信息，必须先通过Logger的 Level与 Filter过滤，
		 * 再通过Handler的 Level与 Filter过滤，
		 * 格式化信息的动作交给 Formatter，输出信息的动作是 Handler负责。
		 * 
		 * 还得知道：
		 * Logger有层级关系，名称空间层级相同的 Logger,父 Logger组态会相同，
		 * 每个 Logger处理完自己得日志动作后，会向父 Logger传播，让父 Logger也可以处理日志。
		 * 
		 * 一句话总结：向父Logger传播
		 * */
	}
	
	// 指定日志层级
	public static void exp2() {
		System.out.println("exp2 -> 指定日志层级，依据 Level过滤信息");
		/* Logger与Handler默认都会先依据 Level过滤信息，
		 * 如果没有做任何修改，取得Logger实例之父Logger组态，就是 Logger.GLOBAL_LOGGER_NAME名称空间Logger实例的组态。
		 * 这个实例的Level设定为 INFO，可通过Logger实例的 getParent()取得父Logger实例，
		 * 可通过getLevel()取得设定的Level实例。
		 * 
		 *  例子：
		 * */
		Logger logger = Logger.getLogger(Some.class.getName());
		Logger global = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		System.out.println(logger.getLevel());
		System.out.println("logger.getParent: " + logger.getParent());
		System.out.println("global.getParent: " + global.getParent());
		
		/* 取得 Logger的信息处理会往父 Logger传播，
		 * 
		 * 也就是说，在没有做任何组态设定的情况下，默认取得的 Logger实例，
		 * 层级必须大于或者等于 Logger.GLOBAL_LOGGER_NAME名称空间 Logger实例设定的Level.INFO，
		 * 才有可能输出信息。
		 * 
		 * 
		 * 可以通过 Logger的setLevel()指定 Level实例，可使用 Level内建的几个静态成员来指定：
		 * 
		 * Level.OFF(Integer.MAX_VALUE) // 关闭所有信息输出
		 * Level.SEVERE(1000)
		 * Level.WARNING(900)
		 * Level.INFO(800)
		 * Level.CONFIG(700)
		 * Level.FINE(500)
		 * Level.FINER(400)
		 * Level.FINEST(300)
		 * Level.ALL(Integer.MIN_VALUE) // 允许所有信息输出
		 * 
		 * 这些静态成员都是 Level的实例，可以使用 intValue()取得内含int值，
		 * Logger本身可以通过 setLevel()设定Level实例，
		 * 若log()时指定的 Level实例内含int值小于 Logger设定的 Level实例内含的int值，Logger就不会记录信息，
		 * 
		 * 也因此 Level.OFF会用于关闭所有信息输出， Level.All会用于允许所有信息输出。
		 * */
		
		/* 在经过 Logger过滤后，还得再经过 Handler过滤，
		 * 一个Logger 可以拥有多个 Handler，可通过 Logger的 addHandler()新增Handler实例
		 * 实际信息输出时，目前的 Logger的Handler处理完，还会传播给父 Logger的所有 Handler处理。
		 * 可通过getHandlers()方法来取得已有的 Handler实例数组。
		 * 
		 * 例子：
		 * */
		System.out.println("依据 Handler过滤:");
		Logger logger2 = Logger.getLogger(Some.class.getName());
		System.out.println(logger2.getHandlers().length); // 显示0，表示没有Handler
		// 以下会显示两行，
		// 一行包括 java.util.logging.ConsoleHandler字样
		// 一行包括INFO字样
		for (Handler handler : logger2.getHandlers()) {
			System.out.println("handler: " + handler);
			System.out.println("getLevel: " + handler.getLevel());
		}
		/* 也就是说，在没有做任何组态设定的情况下，取得的 Logger实例，
		 * 只会使用Logger.GLOBAL_LOGGER_NAME名称空间 Logger实例拥有的Handler，
		 * 默认是使用 ConsoleHandler，为Handler的子类，作用是在控制台输出日志信息，默认的层级是 Level.INFO。*/
		
		/* Handler可通过setLevel()设定信息，一个信息要经过 Logger与 Handler的过滤后才可输出。
		 * 
		 * 例子：
		 * */
		System.out.println("输出一个完整的日志信息:");
		Logger logger3 = Logger.getLogger(MyClass1.class.getName());
		logger3.setLevel(Level.FINE);
		for (Handler handler : logger3.getHandlers()) {
			handler.setLevel(Level.FINE);
		}
		logger3.log(Level.WARNING, "WARNING 信息");
		logger3.log(Level.INFO, "INFO 信息");
		logger3.log(Level.CONFIG, "CONFIG 信息");
		logger3.log(Level.FINE, "FINE 信息");
	}
	// 使用 Handler 与 Formatter
	public static void exp3() {
		/* 负责日志输出的是 Handler实例，标准API中提供了几个 Handler操作类。
		 * MemoryHandler: 不会格式化日志信息，信息会暂存于缓冲区，直到超过缓冲区大小，才将信息输出至指定的目标 Handler。
		 * StreamHandler：可自行指定信息输出时使用的OutputStream实例，它与子类都会使用指定的Formatter格式化信息。
		 * ConsoleHandler 创建时，会自动指定 OutputStream为 System.err，所以日志信息会显示在控制台。
		 * FileHandler创建时，会建立日志输出时使用的 FileOutputStream，文档位置与名称可以使用模式字符串指定。
		 * SocketHandler创建时可以指定主机位置与端口，内部将自动建立网络联机，将日志信息传送至指定的主机。
		 * 
		 * Logger 可以使用 addHandler()新增 Handler实例，使用 removeHandler() 移除Handler。
		 * 
		 * 例子：
		 * */
		try {
			Handler();
		} catch (Exception e) {
			// TODO: handle exception
		}
		/* Logger的 config()是个简便方法，可以直接以 Level.CONFIG层级输出信息。
		 * 另外也有severe()、info()等简便方法。
		 * 
		 * 
		 * 事实上，如果不想让父 Logger的 Handler处理日志，
		 * 可以调用 Logger实例的setUseParentHandlers()设定为 false，这样日志信息就不会传播给父 Logger，
		 * 也可以使用Logger实例的setParent()方法指定父 Logger。
		 * */
	}
	public static void Handler() throws IOException {
		System.out.println("Handler -> Logger新增Handler");
		Logger logger = Logger.getLogger(MyClass1.class.getName());
		logger.setLevel(Level.CONFIG);
		// 保存在电脑桌面，默认会以XML格式保存 ，这是因为FileHandler默认的 Formatter是 XMLFormatter。
		FileHandler handler = new FileHandler("/Users/yunshang/Desktop/config.log");
		
//		FileHandler handler = new FileHandler("%h/config.log");
		/* %h来表示用户的根目录 
		 * (还可以使用%t取得系统暂存目录，或者使用 %g 自动为文档编号，例如设定为 %h/config%g.log，这表示将configN.log文件储存在用户根目录，N表示每个信息的文档编号，会自动递增。)
		 * */
		handler.setLevel(Level.CONFIG);
		logger.addHandler(handler);
		logger.config("Logger 组态完成");
	}
	// 自定义 Handler、Formatter与Filter
	public static void exp4() {
		
	}
}
