package view;

public class TimeZoo extends Thread{
	
		private int n=5;
		private boolean flag=false;//false�� �ʱ�ȭ

		
		synchronized public void run() {
			while(n>0) {
				System.out.println(n);
				n--;
				
				try {
					wait();
					sleep(1000);//1�ʵ��� ���� �� �� �����.
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
