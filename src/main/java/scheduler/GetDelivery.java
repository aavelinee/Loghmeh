package scheduler;


import domain.Loghmeh;

public class GetDelivery implements Runnable {

    @Override
    public void run() {
        Loghmeh.getInstance().addDeliveries();
//        System.out.println("Job trigged by scheduler");
    }
}