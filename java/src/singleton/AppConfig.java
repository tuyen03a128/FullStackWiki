package singleton;

import java.util.Random;

public class AppConfig {
    private static AppConfig self;
    public int appId;

    private AppConfig() {
        if (self != null) {
            throw new UnsupportedOperationException("Use getInstance()");
        }
        appId = new Random().nextInt(100);
    }

    public static AppConfig getInstance() {
        if (self == null) {
            self = new AppConfig();
        }
        return self;
    }

    public static void main(String[] args) {
        Thread threadDownload = new Thread(new Runnable() {
            @Override
            public void run() {
                AppConfig config = AppConfig.getInstance();
                System.out.println(config.appId);
            }
        });

        Thread threadUpload = new Thread(new Runnable() {
            @Override
            public void run() {
                AppConfig config = AppConfig.getInstance();
                System.out.println(config.appId);
            }
        });

        threadDownload.start();
        threadUpload.start();
    }
}