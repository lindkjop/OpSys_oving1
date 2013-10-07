import java.util.ArrayList;
import java.util.TimerTask;


public class ServingArea {
	private int capacity;
	public int nextCustomer = 1;
	public int totalOrders = 0; 
	public int takeAwayOrders = 0;
	public int eatInOrders = 0; 
	private ArrayList<Customer> customersInServingArea;
	
	public ServingArea(int capacity){
		this.capacity = capacity;
		this.customersInServingArea = new ArrayList<Customer>();
	}
	
	public ArrayList<Customer> getCustomersInServingArea() {
		return this.customersInServingArea;
	}
		
	public synchronized boolean seatAvailable() {
		if(customersInServingArea.size() < capacity) {
			
			return true;
		}
		return false;
	}
	
	public synchronized void enterServingArea(Customer c) throws InterruptedException {
	
		while(!(customersInServingArea.size() < capacity || !(c.getCustomerID() == nextCustomer))) {
			
			SushiBar.write(Thread.currentThread().getName()
					+": Customer " + c.getCustomerID()
					+ " is waiting for a free seat.");
			this.wait();
			
		}
		
		customersInServingArea.add(c);
		
		SushiBar.write(Thread.currentThread().getName()
				+": Customer " + c.getCustomerID()
				+ " has a seat now.");
		SushiBar.write(Thread.currentThread().getName()
				+": Customer " + c.getCustomerID()
				+ " is eating sushi.");
		nextCustomer++;
		System.out.println("Waiting for customer: " + nextCustomer);
			
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public synchronized void leaveServingArea(Customer c) {
		if(!customersInServingArea.isEmpty()){
			totalOrders += c.getTotalOrders();
			takeAwayOrders += c.getTakeAwayOrders();
			eatInOrders += c.getEatInOrders();
			
    		customersInServingArea.remove(c);
    		this.notifyAll();
    		SushiBar.write(Thread.currentThread().getName()
					+": Customer " + c.getCustomerID()
					+ " has left the bar.");
    	}
	}
	
}
