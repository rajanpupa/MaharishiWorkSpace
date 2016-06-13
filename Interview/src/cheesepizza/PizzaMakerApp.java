package cheesepizza;

import java.util.Scanner;

public class PizzaMakerApp {
	
	public static Pizza createPizza(String pizzatype){
		Pizza pizza;
		
		switch(pizzatype){
		case "cheese":
			pizza = new CheesePizza();
			break;
		case "chicken":
			pizza = new ChickenPizza();
			break;
		case "pineapple":
			pizza = new PineapplePizza();
			break;
		case "mushroom":
			pizza = new MushroomPizza();
			break;
			
		default:
			return new ChickenPizza();
		}
		
		return pizza;
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter a pizza type(cheese, chicken, pineapple, mushroom) : ");
		
		String type = s.nextLine();
		
		Pizza p = PizzaMakerApp.createPizza(type);
		
		p.getPizza();
		
	}
}
