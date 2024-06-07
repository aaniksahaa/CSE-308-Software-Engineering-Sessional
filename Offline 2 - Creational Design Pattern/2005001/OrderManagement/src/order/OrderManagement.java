package order;

import builders.ShakeBuilder;
import builders.ShakeOrderBuilder;
import director.Director;
import entities.Shake;
import entities.ShakeOrder;
import entities.ShakeVariant;
import ingredients.Topping;
import user.User;

import java.util.Scanner;

public class OrderManagement {
    User currentUser = null;
    Order currentOrder = null;
    Shake currentShake = null;
    ShakeOrder currentShakeOrder = null;
    ShakeBuilder shakeBuilder = null;
    ShakeOrderBuilder shakeOrderBuilder = null;
    Scanner scanner = new Scanner(System.in);
    Director director = new Director();
    private Boolean exitPressed;
    private Boolean quitPressed;

    String booleanInputMessage = "Please press Y or N";
    String invalidInputMessage = "Sorry! Invalid input. Please try again.";
    String landBackMessage = "Landing back to dashboard...\n\n";
    String openPressMessage = "\nSorry! Another order cannot be opened while precessing the current order.\nYou may press 'e' to close the order. Otherwise please specify what you need to add to the order.\n";
    String orderClosingMessage = "\n\nOrder processing interrupted.\nOrder closed.\n"+landBackMessage;
    public void run() {

        System.out.println("Welcome to Shake Order Management");
        System.out.println("Please enter your name: ");

        String username = scanner.nextLine();

        currentUser = new User(username);

        System.out.println("\nGreetings, " + currentUser.getUsername() + "!");

        while(true){
            System.out.println("\nWelcome to the Dashboard. Do you want to place an order?");
            System.out.println("Press 'o' to place an order, 'e' to close an order and 'q' to quit the system.");
            String command = scanner.nextLine();
            exitPressed = Boolean.FALSE;
            quitPressed = Boolean.FALSE;
            switch (command){
                case "o":
                    takeOrder();
                    break;
                case "e":
                    exitPressed = Boolean.TRUE;
                    System.out.println("\nNo order currently open.");
                    System.out.println(landBackMessage);
                    break;
                case "q":
                    quitPressed = Boolean.TRUE;
                    break;
                default:
                    System.out.println(invalidInputMessage);
            }
            if(quitPressed){
                break;
            }
        }
        int totalOrders = currentUser.getOrders().size();
        System.out.println("\n\nDear "+currentUser.getUsername()+", you have placed "+totalOrders+" order(s). They will soon be delivered.");
        System.out.println("Thank you being with us!\n\n");

    }
    private int parseChoice(String str){
        try {
            int value = Integer.parseInt(str);
            return value;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    private int takeChoiceInput(String prompt, int minChoice, int maxChoice){
        int choice = 0;  // dummy value
        while(true){
            System.out.println(prompt);
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("e")){
                exitPressed = Boolean.TRUE;
                System.out.println(orderClosingMessage);
                return -1;
            } else if (input.equalsIgnoreCase("o")) {
                System.out.println(openPressMessage);
                continue;
            }
            choice = parseChoice(input);
            if(choice>=minChoice && choice<=maxChoice){
                break;
            }
            else{
                System.out.println(invalidInputMessage);
            }
        }
        return choice;
    }
    private Boolean takeBooleanInput(String prompt){
        while(true) {
            System.out.println(prompt + '\n' + booleanInputMessage);
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("e")){
                exitPressed = Boolean.TRUE;
                System.out.println(orderClosingMessage);
                return Boolean.FALSE;
            } else if (input.equalsIgnoreCase("o")) {
                System.out.println(openPressMessage);
                continue;
            }
            switch (input.toUpperCase()){
                case "Y":
                    return Boolean.TRUE;
                case "N":
                    return Boolean.FALSE;
                default:
                    System.out.println(invalidInputMessage+'\n');
            }
        }
    }
    public void takeOrder(){
        currentOrder = new Order();
        System.out.println("Order opened");
        while(true){
            inputSingleShake();
            if(exitPressed) return;
            currentOrder.addShakeOrder(currentShakeOrder);

            System.out.println("\nGreat! The above specified Shake is successfully added to the order.");
            System.out.println("Current Number of shakes: "+currentOrder.getShakeOrderList().size()+"\n");

            Boolean response = takeBooleanInput("Do you want to add another shake to the order?");
            if(exitPressed) return;
            if(! response){
                break;
            }
        }
        currentOrder.print();

        Boolean response = takeBooleanInput("Do you want to confirm your order?");
        if(exitPressed) return;
        if(!response){
            System.out.println("\n\nOkay! Your order is discarded.");
            System.out.println(landBackMessage);
            return;
        }
        else{
            currentUser.addOrder(currentOrder);
            System.out.println("\n\nThank you for your order. Hopefully you will receive your shakes soon.");
            System.out.println(landBackMessage);
        }
    }
    public void inputSingleShake(){

        shakeBuilder = new ShakeBuilder();
        shakeOrderBuilder = new ShakeOrderBuilder();

        System.out.println("\nAvailable Shake Variants: \n");
        int count = 0;
        ShakeVariant[] shakeVariants = ShakeVariant.values();
        for(ShakeVariant shakeVariant: shakeVariants) {
            System.out.println(++count + ". " + shakeVariant.getDisplayName());
        }
        System.out.println("");
        int choice = takeChoiceInput("Please enter your choice of Shake variant: ",1,shakeVariants.length);
        if(exitPressed) return;
        switch (choice){
            case 1:
                director.constructChocolateShake(shakeBuilder);
                director.constructChocolateShake(shakeOrderBuilder);
                break;
            case 2:
                director.constructCoffeeShake(shakeBuilder);
                director.constructCoffeeShake(shakeOrderBuilder);
                break;
            case 3:
                director.constructStrawberryShake(shakeBuilder);
                director.constructStrawberryShake(shakeOrderBuilder);
                break;
            case 4:
                director.constructVanillaShake(shakeBuilder);
                director.constructVanillaShake(shakeOrderBuilder);
                break;
            case 5:
                director.constructZeroShake(shakeBuilder);
                director.constructZeroShake(shakeOrderBuilder);
                break;
            default:
                System.out.println(invalidInputMessage);
        }

        Boolean response;

        response = takeBooleanInput("Do you want to make the shake lactose-free?");
        if(exitPressed) return;
        if(response){
            shakeBuilder.makeLactoseFree();
            shakeOrderBuilder.makeLactoseFree();
        }

        response = takeBooleanInput("Do you want to add candy on top?");
        if(exitPressed) return;
        if(response){
            shakeBuilder.addTopping(Topping.CANDY);
            shakeOrderBuilder.addTopping(Topping.CANDY);
        }

        response = takeBooleanInput("Do you want to add cookies on top?");
        if(exitPressed) return;
        if(response){
            shakeBuilder.addTopping(Topping.COOKIES);
            shakeOrderBuilder.addTopping(Topping.COOKIES);
        }

        currentShake = shakeBuilder.getResult();
        currentShakeOrder = shakeOrderBuilder.getResult();

        //System.out.println(currentShakeOrder.getDetails());

    }

}