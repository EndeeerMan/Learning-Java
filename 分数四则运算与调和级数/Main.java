import java.math.BigInteger;
import java.util.Scanner;

final class Fraction implements Comparable<Fraction>{
    private final BigInteger num,den;

    public Fraction(BigInteger num, BigInteger den){
        if(den.equals(BigInteger.ZERO)){
            throw new ArithmeticException("Denominator Cannot be 0");
        }

        if(num.compareTo(BigInteger.ZERO) > 0 && den.compareTo(BigInteger.ZERO) < 0){
            num = num.negate();
            den = den.abs();
        }

        if(num.compareTo(BigInteger.ZERO) < 0 && den.compareTo(BigInteger.ZERO) < 0){
            num = num.abs();
            den = den.abs();
        }

        if(num.equals(BigInteger.ZERO)){
            den = BigInteger.ONE;
        }

        BigInteger gcd = num.abs().gcd(den);

        this.num = num.divide(gcd);
        this.den = den.divide(gcd);
    }
    
    public BigInteger getNum(){
        return num;
    }
    
    public BigInteger getDen(){
        return den;
    }

    @Override 
    public int compareTo(Fraction another){
        BigInteger cmp = num.multiply(another.getDen()).subtract((den.multiply(another.getNum())));

        if(cmp.compareTo(BigInteger.ZERO) < 0) return -1;
        if(cmp.compareTo(BigInteger.ZERO) > 0) return 1;
        return 0;
    }

    @Override 
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Fraction)) return false;
        Fraction another = (Fraction) obj;
        return num.equals(another.getNum()) && den.equals(another.getDen());
    }

    @Override 
    public int hashCode(){
        return java.util.Objects.hash(num,den);
    }

    public Fraction add(Fraction another){
        Fraction result = new Fraction(num.multiply(another.getDen()).add(den.multiply(another.getNum())), (den.multiply(another.getDen())));
        return result;
    }

    public Fraction sub(Fraction another){
        Fraction result = new Fraction(num.multiply(another.getDen()).subtract(den.multiply(another.getNum())), (den.multiply(another.getDen())));
        return result;
    }

    public Fraction multiply(Fraction another){
        Fraction result = new Fraction(num.multiply(another.getNum()), den.multiply(another.getDen()));
        return result;
    }

    public Fraction divide(Fraction another){
        Fraction result = new Fraction(num.multiply(another.getDen()), den.multiply(another.getNum()));
        return result;
    }

    @Override 
    public String toString(){
        if(num.equals(BigInteger.ZERO)) return "0";
        if(den.equals(BigInteger.ONE)) return num.toString();
        return num + "/" + den;
    }
}

public class Main{
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            System.out.print("num1 = ");
            Fraction num1 = new Fraction(sc.nextBigInteger(),sc.nextBigInteger());
            System.out.print("num2 = ");
            Fraction num2 = new Fraction(sc.nextBigInteger(),sc.nextBigInteger());

            System.out.println();
            System.out.println("num1 = " + num1);
            System.out.println("num2 = " + num2);
            System.out.println();

            System.out.println("ADD: " + num1.add(num2));
            System.out.println("SUB: " + num1.sub(num2));
            System.out.println("MUL: " + num1.multiply(num2));
            System.out.println("DIV: " + num1.divide(num2));
            System.out.println("EQL: " + num1.equals(num2));
            System.out.println();

            System.out.print("Harmonic Series: n = ");
            int n = sc.nextInt();
            System.out.println();

            Fraction result = new Fraction(BigInteger.ZERO, BigInteger.ONE);
            for(int i=1;i<=n;++i){
                Fraction temp = new Fraction(BigInteger.ONE,BigInteger.valueOf(i));
                result = result.add(temp);
            }

            System.out.println("H_n: " + result + '\n');
        }
    }
}