package socket.thread;

import java.util.concurrent.CountDownLatch;

public class NetworkChecker extends BaseChecker {

	public NetworkChecker(CountDownLatch latch) {
		super(latch,"network checker");
	}

	@Override
	public void verifyService() {
		System.out.println("checking:"+this.getServiceName());
		try{
			Thread.sleep(10000);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(this.getServiceName()+":is up");

	}

}
