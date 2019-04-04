package singleton;

import java.util.Random;

public class AppConfig {
    //
    private static volatile AppConfig self;
    public int appId;

    // private constructor : not allow other class to create a new instance of your class you want to create the Singleton
    private AppConfig() {
        // prevent from Reflection API
        if (self != null) {
            throw new UnsupportedOperationException("Use getInstance()");
        }
        appId = new Random().nextInt(100);
    }

    // only one method to return the static value
    public static AppConfig getInstance() {
        // lazy initialization : create the new instance of class when needed, prevent from memory leak.

        //Double check locking pattern
        if (self == null) { //Check for the first time

            synchronized (AppConfig.class) {   //Check for the second time.
                //if there is no instance available... create new one
                if (self == null) self = new AppConfig();
            }
        }


        return self;
    }

    public static void main(String[] args) {
//        Thread threadDownload = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                AppConfig config = AppConfig.getInstance();
//                System.out.println(config.appId);
//            }
//        });

        Thread threadDownload = new Thread(() -> {
            AppConfig config = AppConfig.getInstance();
            System.out.println(config.appId);
        });


        Thread threadUpload = new Thread(() -> {
           AppConfig config = AppConfig.getInstance();
            System.out.println(config.appId);
        });

        threadDownload.start();
        threadUpload.start();
    }
}