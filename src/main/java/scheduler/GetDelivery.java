package scheduler;


import domain.Loghmeh;

public class GetDelivery implements Runnable {

    @Override
    public void run() {
            String deliveriesJson = external_services.ExternalServices.getFromExtenalAPI("http://138.197.181.131:8080/deliveries");
        Loghmeh.getInstance().addDeliveries(deliveriesJson);
//        System.out.println("Job trigged by scheduler");
    }
}