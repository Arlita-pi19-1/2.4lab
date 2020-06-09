package com.company;
import java.math.BigDecimal;
import java.util.Scanner;
//Вариант 7
public class Main {
    public static double f(double x){
            return x * x * x - 4 * x * x + x + 6;
        }
        public static double prf ( double x){
            return 3 * x * x - 8 * x + 1;
        }
        // ВЫЧИСЛЕНИЕ КОРНЯ МЕТОДОМ НЬЮТОНА
        public static double x_newton ( double a, double e){
            double x = a;
            double razn;
            do {
                double xn = x - f(x) / prf(x);
                razn = Math.abs(xn - x);
                x = xn;
            } while (razn > e);
            return x - f(x) / prf(x);
        }
        // ВЫЧИСЛЕНИЕ КОРНЯ МЕТОДОМ ДЕЛЕНИЯ ОТРЕЗКА ПОПОЛАМ
        public static double x_div2 ( double a, double b, double e){
            while (b - a > e) {
                double c = (a + b) / 2;
                if (f(a) * f(c) < 0)
                    b = c;
                else if (f(a) * f(c) > 0)
                    a = c;
                else
                    return c;
            }
            return (a + b) / 2;
        }

        public static void main (String[]args){
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите начальные значения интервала и + погрешность");
            double a = sc.nextDouble();
            double b = sc.nextDouble();
            double e = sc.nextDouble();
            System.out.print("Введите предполагаемое количество корней=> ");
            int kol = sc.nextInt();
            double xArray[] = new double[kol];
            int kol_naideno;
            double x, y1, y2;
            double h = (b - a) / kol;
            do {
                kol_naideno = 0;
                x = a;
                while (x < b) {
                    y1 = f(x);
                    y2 = f(x + h);
                    if ((y1 * y2 <= 0)) {
                        x = x_newton(x, e);
                        // x=x_div2(x,x+h, e);
                        xArray[kol_naideno] = x;
                        kol_naideno++;
                    }
                    x += h;
                }
                h /= 2;
            } while ((kol > kol_naideno) && (2 * h > e));
            // Вывод вычисленных корней в заданном форматес помощью класса BigDecmal
            System.out.println("Корни уравнения:");
            for (int i = 0; i < xArray.length; i++) {
                System.out.println(new BigDecimal(xArray[i]).setScale(3, BigDecimal.ROUND_UP));
            }
        }
    }

