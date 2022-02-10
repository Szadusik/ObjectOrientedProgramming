package agh.cs.lab3;

public class World {
    public static void main(String[] args){
        animal pet = new animal();
        animal pet2 = new animal();
        animal pet3 = new animal();
        OptionsParser s = new OptionsParser();
        MoveDirection[] p = s.parse(new String[]{"r","forward","JP","forward","lda","lEfT","f","nie wienem"});
        MoveDirection[] p2 = s.parse(new String[]{"r","forward","left","lEfT","f","f","r","f","forward"});
        MoveDirection[] p3 = s.parse(new String[]{"r","r","r","forward","f","l","l","b"});
        for(int i=0; i< p.length ;i++)
        {
            pet = pet.move(p[i]);
            System.out.println(pet.ToStr());
        }
        System.out.println("-------");
        for(int i=0; i< p2.length ;i++)
        {
            pet2 = pet2.move(p2[i]);
            System.out.println(pet2.ToStr());
        }
        System.out.println("-------");
        for(int i=0; i< p3.length ;i++)
        {
            pet3 = pet3.move(p3[i]);
            System.out.println(pet3.ToStr());
        }
    /* Odpowiedź na pytanie nr.10 jak zaimplementować mechanizm, który wyklucza pojawienie się dwóch zwierząt w tym samym miejscu.
       Mechanizm ten polegałby na stworzeniu w klasie animal metody, która przyjmuje jako parametr kolejne zwierze oraz wektor,
       który po dodaniu do aktualnego miejsca zwierzaka daje nam miejsce, na które zwierzę chce
       się dostać. Metoda ta byłaby wywoływana przed wykonaniem ruchu przez pierwsze zwierzę.
       Wewnątrz tej metody sprawdzamy, czy pozycja, która jest miejscem docelowym pierwszego zwierzaka nie jest równa
       położeniu drugiego zwierzęcia. Jeśli tak, to zwracamy informacje o tym, że ruch jest niemożliwy do wykonania,
       w przeciwnym wypadku pierwsze zwierzę może się ruszyć. Można to zrealizować za pomocą typu boolean gdzie wartość
       False po wykonaniu metody mówi nam, że ruch jest niemożliwy do wykonania i nie wykonujemy żadnego ruchu. W
       przeciwnym wypadku ruch jest możliwy do zrealizowania i przemieszczamy zwierzę.
     */
    }
}
