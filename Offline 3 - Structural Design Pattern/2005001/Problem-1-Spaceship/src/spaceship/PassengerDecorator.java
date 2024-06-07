package spaceship;

public abstract class PassengerDecorator implements Passenger{
    private Passenger wrappee;
    public PassengerDecorator(Passenger wrappee){
        this.wrappee = wrappee;
    }
    @Override
    public void login() {
        wrappee.login();
    }
    @Override
    public void repair() {
        wrappee.repair();
    }
    @Override
    public void work() {
        wrappee.work();
    }
    @Override
    public void logout() {
        wrappee.logout();
    }
}
