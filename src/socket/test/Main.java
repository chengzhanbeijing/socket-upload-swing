package socket.test;

public class Main {
	public static void main(String[] args) {
		boolean result = false;
		try{
			result = TestCountDownUtil.checkServices();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("the main thread is the last executor:"+result);
	}
}
