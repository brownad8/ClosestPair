//Adam Brown CSC 421
import java.util.ArrayList;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<Point> points = new ArrayList<Point>();

        try {
            String fileName = args[0]; 
            File pointsFile = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(pointsFile));
            String point = "";

            while ((point = br.readLine()) != null) {
                String[] xAndY = point.split(" ");
                int x = Integer.parseInt(xAndY[0]);
                int y = Integer.parseInt(xAndY[1]);
                points.add(new Point(x, y));
            }

        } catch (IOException e) {
            System.out.println("File not found");
        }

        Point[] S = new Point[points.size()];
        int SPos = 0;

        for(Point p : points) {
            S[SPos] = p;
            SPos++;
        }

        Pair res = ClosestPair.findClosestPair(S);
        System.out.println("Closest pair in S: p = (" + res.p.x + ", " + res.p.y + ") q = (" + res.q.x +
            ", " + res.q.y + ")");
        double distance = ClosestPair.computeDistance(res.p, res.q);
        System.out.println("Distance between p and q: " + distance);

    }

}