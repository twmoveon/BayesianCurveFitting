package com.company;

import java.io.*;
import java.util.Arrays;

public class LoadData {
    private int N;
    private int count;
    private String path;
    private double[] prices;

    String[] data;
    public LoadData(int n, String company) {
        setN(n);
        setPath("/Users/wuxiaodong/Downloads/BayesianCurveFitting-master/StockData/" + company + "_history.csv");
        readFile();
    }
    public void readFile() {
        data = new String[300];
        prices = new double[N];
        String line = "";
        count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            line = reader.readLine();
            while(line != null) {
                if(line!=null) {
                    String[] item = line.split(",");
                    data[count] = item[3];
                    count++;
                    line = reader.readLine();
                }else break;
                }
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < N; i++) {
            prices[i] = Double.parseDouble(data[count - i - 1]);
        }
    }

    public double[] getPrices() {
        return prices;
    }

    public double getRealPrice(){
        double price = Double.parseDouble(data[count - N - 1]);
        return price;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
