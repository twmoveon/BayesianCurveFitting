package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice=0;
        int num = 0;
        double[] x;
        int N;
        double Mean;
        double Variance;
        double actualPrice;
        double absMeanErr = 0.0;	// absolute mean error
        double relErr = 0.0;	// relative error;
        double sumErr = 0.0;
        double sumPrice = 0.0;
        double[] prices;
        String[]company = {"AAPL","AMZN","CAJ","FB","GOOG","MSFT","NINOY","SNE", "WMT", "YHOO"};
        while(choice!=11) {
            System.out.println("----------Stock Prediction----------");
            System.out.println("Please choose the stock:");
            System.out.println("1.AAPL, 2.AMZN, 3.CAJ, 4.FB, 5.GOOG");
            System.out.println("6.MSFT, 7.NINOY, 8.SNE, 9.WMT, 10.YHOO, 11.EXIT");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            String stock = company[choice - 1];
            System.out.println("Please enter the number of data:");
            N = scanner.nextInt();
                LoadData loadData = new LoadData(N,stock);
                prices = loadData.getPrices();
                x = new double[N];
                for(int i = 0; i < N; i++){
                    x[i] = i+1;
                }
                int M = 9;
                Bayesian bayesian = new Bayesian(x,prices,M);
                Mean = bayesian.mean(N+1);
                Variance = bayesian.variance(N+1);
                actualPrice = loadData.getRealPrice();
                absMeanErr = Math.abs(actualPrice - Mean);
                relErr = absMeanErr / actualPrice;
                System.out.println("- - - - - - - - - - - - - - - -");
                System.out.println("Stock: "+ stock);
                System.out.println("The prediction of price is: " + Mean + "$");
                System.out.println("The variance is: "+ Variance);
                System.out.println("The actual price is: " + actualPrice + "$");
                System.out.println("The absolute mean error: " + absMeanErr);
                System.out.println("The average relative error: " + relErr);
                System.out.println("-----------------------------------------------------------");
        }

    }

}
