package scheduler;

import domain.Loghmeh;

public class GetFoodParty implements Runnable{
    @Override
    public void run() {
        String foodPartyJson = external_services.ExternalServices.getFromExtenalAPI("http://138.197.181.131:8080/foodparty");
        Loghmeh.getInstance().deleteFoodParty();
        Loghmeh.getInstance().addFoodPartyRestaurants(foodPartyJson);
    }
}
