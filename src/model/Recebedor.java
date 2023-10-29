package model;

import java.util.Scanner;
import java.io.InputStream;

public class Recebedor implements Runnable{
    public InputStream servidor;

    public Recebedor(InputStream servidor){
        this.servidor = servidor;
    }

    public void run(){
        Scanner s = new Scanner(this.servidor);
        while(s.hasNextLine()){
            System.out.println(s.nextLine());
        }
    }
}