/**
 * This is a class
 * Created 2020-10-28
 *
 * @author Magnus Silverdal
 */
public class FourierExpansion {
    private double[] a;
    private double[] b;

    public FourierExpansion(double[] a, double[] b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Default construction of a squarewave using n terms (c_0 = 0.5, c_k = 2/(k*pi) for odd k, c_k = 0 for even k)
     */

    public FourierExpansion(int n) {
        a = new double[n];
        a[0] = 0.5;
        double twoByPi = 2/Math.PI;
        for (int i = 1 ; i < n ; i++) {
            if (i%2==1) {
                a[i] = twoByPi / i;
            } else{
                a[i] = 0;
            }
        }
    }

    /**
     * get real part of the expansion value at theta
     * @param theta
     * @return
     */
    public double getRealValue(double theta) {
        double sum = a[0];
        for (int i = 1 ; i < a.length ; i++) {
            sum += a[i]*Math.sin(i*theta);
        }
        return sum;
    }
    /**
     * get imagonary part of the expansion value at theta
     * @param theta
     * @return
     */
    public double getImaginaryValue(double theta) {
        double sum = b[0];
        for (int i = 1 ; i < a.length ; i++) {
            sum += b[i]*Math.cos(i*theta);
        }
        return sum;
    }

    /**
     * get expansion value at theta
     * @param theta
     * @return
     */
    public double[] getComplexValue(double theta) {
        double[] result = {getRealValue(theta), getImaginaryValue(theta)};
        return result;
    }

    public double getComponentRealValue(int k, double theta) {
        return a[k]*Math.sin(k*theta);
    }

    // For testing
    public static void main(String[] args) {
        FourierExpansion fe = new FourierExpansion(10);
        for (double theta = 0 ; theta < 2*Math.PI ; theta+=0.1) {
            System.out.println(theta + ", " + fe.getRealValue(theta));
        }
    }
}
