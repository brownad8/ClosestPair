//Adam Brown CSC 421
public class MergeSort {

    public static void mergeX(Point[] A, int first, int mid, int last) {
        int LSize = (mid - first) + 1;
        int RSize = last - mid;

        Point[] L = new Point[LSize + 1];
        Point[] R = new Point[RSize + 1];

        int LPos = 0;
        int RPos = 0;

        for(int i=first; i<mid+1; i++) {
            L[LPos] = A[i];
            LPos++;
        }

        L[LSize] = new Point(Integer.MAX_VALUE, 0);

        for (int i=mid+1; i<last+1; i++) {
            R[RPos] = A[i];
            RPos++;
        }

        R[RSize] = new Point(Integer.MAX_VALUE, 0);

        int i = 0;
        int j = 0;

        for(int k=first; k<last+1; k++) {
            if(L[i].x <= R[j].x) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
        }

    }

    public static void mergeY(Point[] A, int first, int mid, int last) {
        int LSize = (mid - first) + 1;
        int RSize = last - mid;

        Point[] L = new Point[LSize + 1];
        Point[] R = new Point[RSize + 1];

        int LPos = 0;
        int RPos = 0;

        for(int i=first; i<mid+1; i++) {
            L[LPos] = A[i];
            LPos++;
        }

        L[LSize] = new Point(0, Integer.MAX_VALUE);

        for (int i=mid+1; i<last+1; i++) {
            R[RPos] = A[i];
            RPos++;
        }

        R[RSize] = new Point(0, Integer.MAX_VALUE);

        int i = 0;
        int j = 0;

        for(int k=first; k<last+1; k++) {
            if(L[i].y <= R[j].y) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
        }
    }

    public static void sortX(Point[] A, int first, int last) {

        if(first < last) {
            int mid = (first + last)/2;
            sortX(A, first, mid);
            sortX(A, mid+1, last);
            mergeX(A, first, mid, last);
        }
    }

    public static void sortY(Point[] A, int first, int last) {

        if(first < last) {
            int mid = (first + last)/2;
            sortY(A, first, mid);
            sortY(A, mid+1, last);
            mergeY(A, first, mid, last);
        }
    }

}
