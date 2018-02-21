package com.company;


import Jama.Matrix;

public class Bayesian {

    private double alpha;
    private double beta;
    private int M;
    private int N;
    private double[] x;
    private double[] t;
    private Matrix S;
    private Matrix I;

    public Bayesian(double[] a, double[] b, int m){
        if(a.length != b.length) {
            System.out.println("The degree of matrix is not valid");
            return;
        }
        setAlpha(0.005);
        setBeta(11.1);
        setX(a);
        setT(b);
        setM(m);
        setN(x.length);
        setI(Matrix.identity(M + 1, M + 1));
        getMatrixS();
    }

    public double getAlpha() {
        return alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public void setM(int m) {
        M = m;
    }

    public void setN(int n) {
        N = n;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public void setT(double[] t) {
        this.t = t;
    }

    public void setI(Matrix i) {
        I = i;
    }
    public double mean(double x){
        return getMx(x);
    }
    public double variance(double x){
        return getS2X(x);
    }
    public Matrix getMatrixO(double x){
        double[] Val = new double[M + 1];
        for (int i = 0; i <= M; i++) {
            Val[i] = Math.pow(x, i);
        }
        Matrix O = new Matrix(Val, M + 1);
        return O;
    }
    public Matrix getMatrixS() {
        Matrix sum = new Matrix(M + 1, M + 1);
        for (int i = 0; i < N; i++) {
            Matrix phi = getMatrixO(x[i]);
            sum = sum.plus(phi.times(phi.transpose()));
        }
        sum = sum.times(this.beta);
        S = I.times(this.alpha).plus(sum);
        S = S.inverse();
        return S;
    }
    public double getS2X(double x) {
        Matrix s = getMatrixO(x).transpose().times(S).times(getMatrixO(x));
        double sVal = s.get(0, 0);
        double s2x = 1 / beta + sVal;
        return s2x;
    }
    public double getMx(double x) {
        Matrix sum = new Matrix(M + 1, 1);
        for (int i = 0; i < N; i++) {
            Matrix O = getMatrixO(this.x[i]);
            sum = sum.plus(O.times(t[i]));
        }
        Matrix mx = getMatrixO(x).transpose().times(this.beta);
        mx = mx.times(S);
        mx = mx.times(sum);
        return mx.get(0, 0);
    }
}
