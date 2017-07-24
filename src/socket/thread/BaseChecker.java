package socket.thread;

import java.util.concurrent.CountDownLatch;

public abstract class BaseChecker implements Runnable {
	
	private CountDownLatch latch;
	private String serviceName;
	private boolean serviceUp;
	
	

	public BaseChecker(CountDownLatch latch, String serviceName) {
		super();
		this.latch = latch;
		this.serviceName = serviceName;
		this.serviceUp = false;
	}
	
	public CountDownLatch getLatch() {
		return latch;
	}


	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	public String getServiceName() {
		return serviceName;
	}


	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public boolean isServiceUp() {
		return serviceUp;
	}

	public void setServiceUp(boolean serviceUp) {
		this.serviceUp = serviceUp;
	}
	
	public abstract void verifyService();





	@Override
	public void run() {
		try{
			verifyService();
			serviceUp = true;
		}catch(Exception e){
			e.printStackTrace(System.err);
			serviceUp = false;
		}finally{
			if(latch!=null){
				latch.countDown();
			}
		}
	}

}
