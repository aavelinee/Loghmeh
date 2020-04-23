package loghmeh_server.scheduler;

import loghmeh_server.domain.Loghmeh;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BackgroundGetFoodPartyManager implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    private ScheduledExecutorService delaySetterTimer;
    ScheduledFuture<?> future;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        future = scheduler.scheduleAtFixedRate(new GetFoodParty(), 0, 2, TimeUnit.MINUTES);

        delaySetterTimer = Executors.newSingleThreadScheduledExecutor();
        delaySetterTimer.scheduleAtFixedRate(new SetDelay(), 0, 2, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
        delaySetterTimer.shutdown();
    }


    public class SetDelay implements Runnable{
        @Override
        public void run() {

            System.out.println("delay" + future.getDelay(TimeUnit.SECONDS));
            Loghmeh.getInstance().setNextFoodPartySchedulerFire(future.getDelay(TimeUnit.SECONDS));
        }
    }

}