import java.util.TimerTask;


public class Door extends Thread{
    int customer_ID;
    Customer customer;
    
    public Door(){
        this.customer_ID = 1;
    }
    
    public void createCustomer() {
    	customer = new Customer(customer_ID);
        customer_ID++;
        customer.start();
    }

	@Override
	public void run() {
		while(SushiBar.isOpen) {
			createCustomer();
			try {
				Thread.sleep(SushiBar.doorWait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Total orders: " + SushiBar.servingArea.totalOrders + "TakeAway : " + SushiBar.servingArea.takeAwayOrders + "/n Eat in: " + SushiBar.servingArea.eatInOrders );
		System.out.println("**** NO MORE CUSTOMERS - THE SHOP IS CLOSED NOW ****");
	}
}