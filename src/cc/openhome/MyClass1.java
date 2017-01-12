package cc.openhome;

import java.util.logging.Logger;

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
		
		/* 之后谈反射时会介绍，类之后接下 .class可以取得该类的 java.lang.Class实例*/
		
	}
}
