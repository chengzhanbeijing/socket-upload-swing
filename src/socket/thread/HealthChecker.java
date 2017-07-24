package socket.thread;

import java.util.concurrent.CountDownLatch;

public class HealthChecker extends BaseChecker {

	public HealthChecker(CountDownLatch latch) {
		super(latch,"health checker");
	}

	@Override
	public void verifyService() {
		System.out.println("checking:"+this.getServiceName());
		try{
			Thread.sleep(7000);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(this.getServiceName()+":is up");

	}

}
