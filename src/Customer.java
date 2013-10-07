
public class Customer extends Thread {
    Thread t;
    private int customer_ID;
    private int takeAwayOrders;
    private int eatInOrders;
    private int totalOrders;
    
    
    public Customer(int id) {
        this.customer_ID = id;
        this.takeAwayOrders = (int) (Math.random()*5);
        this.eatInOrders = (int) (Math.random()*5);
        this.totalOrders = this.takeAwayOrders + this.eatInOrders;
       
    }
    
    public int getCustomerID() {
    	return this.customer_ID;
    }
    
    public void setCustomerID(int newID) {
    	this.customer_ID = newID;
    }
    
    public int getTakeAwayOrders() {
    	return this.takeAwayOrders;
    }
    
    public int getEatInOrders() {
    	return this.eatInOrders;
    }
    
    public int getTotalOrders() {
    	return this.totalOrders;
    }
    
	@Override
	public void run() {
		
		 SushiBar.write(Thread.currentThread().getName()
					+": Customer " + customer_ID
					+ " is now created.");
		
			try {
				SushiBar.servingArea.enterServingArea(this);
				for(int i = 1; i <= this.eatInOrders; i++) {
					try {
						Thread.sleep(SushiBar.customerWait);//Eat the orders
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				System.out.println("Something went wrong");
			}

			SushiBar.servingArea.leaveServingArea(this);
			//this.cancel();

	}
}