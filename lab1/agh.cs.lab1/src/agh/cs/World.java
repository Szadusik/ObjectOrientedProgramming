package agh.cs;
import static java.lang.System.out;

public class World {
    public static direction[] convert(String[] s) {
        int l = s.length;
        direction[] res = new direction[l]; //metoda konwertująca tablicę elementów string na tablicę elementów typu enum direction
        for (int i = 0; i < l; i++) {
            switch (s[i]) {
                case "f":
                    res[i] = direction.FORWARD;
                    break;
                case "b":
                    res[i] = direction.BACKWARD;
                    break;
                case "r":
                    res[i] = direction.RIGHT;
                    break;
                case "l":
                    res[i] = direction.LEFT;
                    break;
                default:
                    continue;
            }
        }
        return res;
    }
    public static void run(direction[] stq){
        //out.print("Zwierzak idzie do przodu,");
        for(direction arg :stq){ //Przerobiona funkcja run operująca na typach enum direction
            switch (arg) {
                case FORWARD:
                    out.print("Zwierzak idzie do przodu,");
                    break;
                case BACKWARD:
                    out.print("Zwierzak idzie do tyłu,");
                    break;
                case RIGHT:
                    out.print("Zwierzak skręca w prawo,");
                    break;
                case LEFT:
                    out.print("Zwierzak skręca w lewo,");
                    break;
                default:
                    continue;
            }
        }
    }
    public static void main(String[] args) {
        out.print("Start,");
        direction[] enums;
        //direction[] en;
        //String[] test = {"f","f","l","r","b","r","b","b"}; //Prosty przykład sprawdzający poprawność metod convert i run wypisująca rezultaty
        //en = convert(test);
        //run(en);
        enums = convert(args);
        run(enums);
        out.print("Stop");

    }

}



