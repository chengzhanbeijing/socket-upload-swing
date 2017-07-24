package socket.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import socket.thread.BaseChecker;
import socket.thread.DataBaseChecker;
import socket.thread.HealthChecker;
import socket.thread.NetworkChecker;

public class TestCountDownUtil {
	private static List<BaseChecker> services;
	private static CountDownLatch latch;
	private TestCountDownUtil() {
		super();
		// TODO Auto-generated constructor stub
	}
	private final static TestCountDownUtil INSTANCE = new TestCountDownUtil();
	public static TestCountDownUtil getInstance(){
		return INSTANCE;
	}
	public static boolean checkServices()throws Exception{
		latch = new CountDownLatch(3);
		services = new ArrayList<BaseChecker>();
		services.add(new HealthChecker(latch));
		services.add(new DataBaseChecker(latch));
		services.add(new NetworkChecker(latch));
		Executor exe = Executors.newFixedThreadPool(services.size());
		for(BaseChecker c:services){
			exe.execute(c);
		}
		latch.await();
		for(BaseChecker c:services){
			if(!c.isServiceUp()){
				return false;
			}
		}
		return true;
	}
	
}
