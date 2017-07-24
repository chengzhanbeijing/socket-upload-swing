package socket.thread;

import java.util.concurrent.CountDownLatch;

public class DataBaseChecker extends BaseChecker {

	public DataBaseChecker(CountDownLatch latch) {
		super(latch,"dataBase checker");
	}

	@Override
	public void verifyService() {
		System.out.println("checking:"+this.getServiceName());
		try{
			Thread.sleep(3000);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(this.getServiceName()+":is up");

	}

}
