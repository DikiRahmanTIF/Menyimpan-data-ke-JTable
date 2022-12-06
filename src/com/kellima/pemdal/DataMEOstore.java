package com.kellima.pemdal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Scanner;

public class DataMEOstore {
    private String merek;
    private String kustomer;
    private String jenisproduk;
    private int harga;
    private int jumlah;
    private int hargaAkhir;

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getKustomer() {
        return kustomer;
    }

    public void setKustomer(String kustomer) {
        this.kustomer = kustomer;
    }

    public String getJenisproduk() {
        return jenisproduk;
    }

    public void setJenisproduk(String jenisproduk) {
        this.jenisproduk = jenisproduk;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    private String[][] mObject;

    public String[][] getmObject() throws FileNotFoundException {
        this.readingData();
        return mObject;
    }

    public void insertingDataToFile(String inputtedData) throws IOException {
        FileWriter fw = new FileWriter("FileDataBase.txt", true);
        fw.write(inputtedData);
        fw.close();
    }

    public void storedDataToTempFile(String inputtedData) throws IOException {
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/src/TEMPStrArr.txt", true);
        fw.write(inputtedData);
        fw.close();
    }

    public void readingData() throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir") + "/src/TEMPStrArr.txt");
        Scanner sc2 = new Scanner(file);
        Scanner sc = new Scanner(file);

        int totalLine = 0;
        while (sc2.hasNextLine()){
            sc2.nextLine();
            totalLine += 1;
        }

        String[][] StoDArray = new String[totalLine][7];

        int startLoopMain = 0;
        while (sc.hasNextLine()) {
            String sObject[] = String.valueOf(sc.nextLine()).split("#");
            for (int i = 0; i < sObject.length; i++) {
                StoDArray[startLoopMain][i] = sObject[i];
            }
            startLoopMain++;
        }
        this.mObject = StoDArray;
    }

    public void generateList(){
        for (int i = 0;i<this.mObject.length;i++){
            for (int b = 0;b < this.mObject[i].length;b++){
                System.out.println(this.mObject[i][b]);
            }
        }
    }

    public int hargaAkhir(){
        return this.harga * getJumlah();
    }

    public void restartHarga(){
        this.harga = 0;
    }

}