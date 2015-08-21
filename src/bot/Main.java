/*
 * Copyright (C) 2015 erkanmdr
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package bot;

//Bağımlılık sorunu çözümü: "sudo pacman -S opencv  tesseract"

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

/**
 *
 * @author erkanmdr
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //  
        String dota2_winID = terminalSpeaker("xdotool search --name DOTA");
        if (Integer.valueOf(dota2_winID) > 1) {
            System.out.println("FALKON çalışıyor!");
            terminalSpeaker("xdotool windowactivate " + dota2_winID);
            System.out.println("Pencereye odaklanıldı!");
            Screen s = new Screen();
            try {
                System.out.println("Açılışı bekleniyor...");
                s.wait("img/oyna.png", 60);
                Thread.sleep(2);
                System.out.println("Açıldı!");
                s.click("img/oyna.png", 0);
                System.out.println("Maç türü seçiliyor...");
           //     s.wait("img/dereceli_0.png", 0);
                s.click("img/dereceli_0.png", 0);
             //   s.wait("img/dereceli_1.png", 0);
                s.click("img/dereceli_1.png", 0);
                System.out.println("Dereceli seçildi.");
                s.wait("img/macbul.png", 2);
                s.click("img/macbul.png", 0);
                System.out.println("Maç aranıyor...");
                s.wait("img/kabulet.png", 900);
                System.out.println("Maç bulundu!");
                Thread.sleep(2);
                s.click("img/kabulet.png", 0);
                System.out.println("Kabul edildi!");

            } catch (FindFailed | InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Dota 2 başlatılıyor...");
            Runtime.getRuntime().exec("steam -applaunch 570");
        }

//        
//        try {
//
//            s.wait("img/xlike.png", 10);
//            s.click("img/xlike.png", 0);
//        } catch (FindFailed ex) {
//            Logger.getLogger(MikuliX.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public static String terminalSpeaker(String command) throws IOException {
        String line;
        Process p = Runtime.getRuntime().exec(command);
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = input.readLine()) != null) {
            return line;
        }
        input.close();
        return null;
    }
}
