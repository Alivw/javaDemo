package uuid;

import javax.sound.midi.Soundbank;
import java.io.InputStreamReader;

import java.io.BufferedReader;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        String s1 = tmp[0];
        int total = Integer.parseInt(tmp[1]);
        int cnt = 0;
        String[] split = s1.split(",");
        int a = Integer.parseInt(split[split.length - 1]);

        System.out.println(total % a == 0 ? total / a : (total / a) + 1);



    }
}

