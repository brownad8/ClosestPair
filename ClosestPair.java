//Adam Brown CSC 421
import java.util.ArrayList;

public class ClosestPair {


    public static double computeDistance(Point p, Point q) {
        double xs = (q.x - p.x) * (q.x - p.x);
        double ys = (q.y - p.y) * (q.y - p.y);
        double res = Math.sqrt(xs + ys);
        return res;
    }

    public static Pair CPA(Point[] S, Point[] X, Point[] Y) {

        //Base Case
        if(S.length <= 3) {
            if(S.length == 3) {
                double[] distances = new double[3];
                distances[0] = computeDistance(S[0], S[1]);
                distances[1] = computeDistance(S[0], S[2]);
                distances[2] = computeDistance(S[1], S[2]);
                double min = distances[0];
                int minPos = 0;
                for(int i=1; i<S.length; i++) {
                    if(distances[i] < min) {
                        min = distances[i];
                        minPos = i;
                    }
                }
                if(minPos == 0)
                    return new Pair(S[0], S[1]);
                else if(minPos == 1)
                    return new Pair(S[0], S[2]);
                else if(minPos == 2)
                    return new Pair(S[1], S[2]);
            } else if(S.length == 2) {
                return new Pair(S[0], S[1]);
            }
        }

        //Create SL and SR
        int median = (S.length-1)/2;
        int D = X[median].x;
        Point[] SL = new Point[median+1];
        Point[] SR = new Point[(S.length-1)-median];

        int SRPos = 0;

        for(int i=0; i<median+1; i++) {
            SL[i] = X[i];
        }

        for(int i=median+1; i<S.length; i++) {
            SR[SRPos] = X[i];
            SRPos++;
        }

        //Create XL, XR, YL and YR
        Point[] XL = SL;
        Point[] XR = SR;
        Point[] YL = new Point[SL.length];
        Point[] YR = new Point[SR.length];

        int YLPos = 0;
        int YRPos = 0;
        boolean pFound = false;

        for(int i=0; i<Y.length; i++) {
            Point p = Y[i];
            for(int j=0; j<XL.length; j++) {
                if(XL[j] == p) {
                    YL[YLPos] = p;
                    YLPos++;
                    pFound = true;
                    break;
                }
            }
            if(pFound == false) {
                YR[YRPos] = p;
                YRPos++;
            }
            pFound = false;
        }

        //Recurse
        Pair closestL = CPA(SL, XL, YL);
        double deltaL = Double.MAX_VALUE;
        if(closestL != null)
            deltaL = computeDistance(closestL.p, closestL.q);
        Pair closestR = CPA(SR, XR, YR);
        double deltaR = Double.MAX_VALUE;
        if(closestR != null)
            deltaR = computeDistance(closestR.p, closestR.q);
        double delta = Math.min(deltaL, deltaR);

        Pair minPair = null;

        if(delta == deltaL)
            minPair = closestL;
        else if(delta == deltaR)
            minPair = closestR;

        ArrayList<Point> YMid = new ArrayList<Point>();

        for(int i=0; i<Y.length; i++) {
            if(Y[i].x <= D+delta || D-delta <= Y[i].x)
                YMid.add(Y[i]);
        }

        //Find pair in middle with minimum distance
        Pair closestMid = null;
        int n = YMid.size();
        int maxComputes = n-1;
        int computes = 0;
        double minDistance = Double.MAX_VALUE;

        for(int i=0; i<n; i++) {
            int j = i+1;
            while(computes < maxComputes && computes < 7) {
                double res = computeDistance(YMid.get(i), YMid.get(j));
                if((res < minDistance)) {
                    minDistance = res;
                    closestMid = new Pair(YMid.get(i), YMid.get(j));
                }
                computes++;
                j++;
            }
            maxComputes--;
            computes = 0;
        }

        if(minDistance < delta)
            minPair = closestMid;

        return minPair;

    }

    public static Pair findClosestPair(Point[] S) {

        Point[] X = S;
        Point[] Y = S;

        int n = S.length-1;

        MergeSort.sortX(X, 0, n);
        MergeSort.sortY(Y, 0, n);

        return CPA(S, X, Y);
    }

}
