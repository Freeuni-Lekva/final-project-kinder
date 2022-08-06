package ge.kinder.Security;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Authentificator {

    private final HashMap<String, String> codes;

    public Authentificator(HashMap<String, String> codes) {
        this.codes = codes;
    }

    public String generateCode(String mail){
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        String code =  String.format("%06d", number);
        codes.put(code,mail);

        TimerTask task = new TimerTask() {
            public void run() {
                codes.remove(code);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 30000);
        return code;
    }

    public boolean CodeIsCorrect(String code){
        return codes.containsKey(code);

    }
}
