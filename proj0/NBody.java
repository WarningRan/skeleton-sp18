public class NBody {
    private static String imageToDraw = "images/starfield.jpg";
    public static double readRadius (String FileName){
        In in = new In(FileName);
        while(!in.isEmpty()) {
            int N = in.readInt();
            double R = in.readDouble();
            return R;
        }
        return 0;
    }

    public static Planet[] readPlanets(String FileName){
        In in = new In(FileName);
        while(!in.isEmpty()) {
            int N = in.readInt();
            double R = in.readDouble();
            Planet[] planets = new Planet[N];
            for (int i = 0; i < planets.length; i++){
                double xxPos = in.readDouble();
                double yyPos = in.readDouble();
                double xxVel = in.readDouble();
                double yyVel = in.readDouble();
                double mass = in.readDouble();
                String location = in.readString();
                planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,location);
            }
            return planets;
        }
        return null;
    }
    public static void main(String[] args) {
        // Collecting All Needed Input
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets =  readPlanets(filename);

        // Drawing the Background
        StdDraw.enableDoubleBuffering();////to do
        StdDraw.setScale(-100, 100);
        StdDraw.clear();
        StdDraw.picture(0,0,imageToDraw);

        // Drawing All of the Planets
        for (Planet p : planets){
            if (!p.imgFileName.contains("images")){
                p.imgFileName = "images/" + p.imgFileName;
            }
            p.update(dt, p.calcNetForceExertedByX(planets), p.calcNetForceExertedByY(planets));
            System.out.println("the path is : " + p.imgFileName);
            System.out.println("the location is : " + p.xxPos + "," + p.yyPos);
            p.draw();
        }
        StdDraw.show();
        StdDraw.pause(10);

    }


}
