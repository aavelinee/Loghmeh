package loghmeh_server.scheduler;

import loghmeh_server.domain.Loghmeh;

public class GetFoodParty implements Runnable{
    @Override
    public void run() {
        String foodPartyJson = loghmeh_server.external_services.ExternalServices.getFromExtenalAPI("http://138.197.181.131:8080/foodparty");
        System.out.println(foodPartyJson);
//        Loghmeh.getInstance().deleteFoodParty();
        Loghmeh.getInstance().addFoodPartyRestaurants(foodPartyJson);
    }
}
