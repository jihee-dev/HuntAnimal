package view;

public class TimeZoo extends Thread{
	
		private int n=5;
		private boolean flag=false;//false로 초기화

		
		synchronized public void run() {
			while(n>0) {
				System.out.println(n);
				n--;
				
				try {
					wait();
					sleep(1000);//1초동안 잠을 잔 후 깨어난다.
					if(n==1) {
						this.flag=true;
					}
					
					
				}catch(InterruptedException e) {
				
				
				}
				notifyAll();
			}
			
		
		}
		
		public boolean getFlag() {
			return this.flag;
		}
		
}
