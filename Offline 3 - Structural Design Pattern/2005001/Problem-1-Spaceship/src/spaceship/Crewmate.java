package spaceship;

public class Crewmate implements Passenger{
    private String name;
    public Crewmate(String name){
        this.name = name;
    }
    @Override
    public void login() {
        System.out.println("Welcome Crewmate!");
    }

    @Override
    public void repair() {
        System.out.println("Repairing the spaceship.");
    }

    @Override
    public void work() {
        System.out.println("Doing research");
    }

    @Override
    public void logout() {
        System.out.println("Bye Bye crewmate.");
    }
}
