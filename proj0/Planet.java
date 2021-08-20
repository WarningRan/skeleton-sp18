public class Planet {
    public double xxPos; // Its current x position
    public double yyPos; // Its current y position
    public double xxVel; //Its current velocity in the x direction
    public double yyVel;// Its current velocity in the y direction
    public double mass; // Its mass
    public String imgFileName; // The name of the file that corresponds to the image that depicts the planet (for example, jupiter.gif)

    // to declare any constants as a ‘static final’ variable in your class, and to use that variable anytime you wish to use the constant.
    private static final double G = 6.67e-11;

    // two Planet constructors that can initialize an instance of the Planet class.
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    //The second constructor should take in a Planet object and initialize an identical Planet object (i.e. a copy).
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    // calculates the distance between two Planets
    public double calcDistance(Planet p){
        double delX = this.xxPos - p.xxPos;
        double delY = this.yyPos - p.yyPos;
        double rSq = delX * delX + delY * delY;

        return java.lang.Math.sqrt(rSq);
    }

    // describing the force exerted on this planet by the given planet
    public double calcForceExertedBy(Planet p){
        double upV = G * this.mass * p.mass;
        double downV = this.calcDistance(p) * this.calcDistance(p);

        return upV / downV;
    }

    // describe the force exerted in the X and Y directions, respectively.
    public double calcForceExertedByX(Planet p){
        double force = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dx = p.xxPos - this.xxPos;

        return force * dx / r;
    }

    public double calcForceExertedByY(Planet p){
        double force = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dy = p.yyPos - this.yyPos;;

        return force * dy / r;
    }

    // each take in an array of Planets and calculate the net X and net Y force exerted by all planets
    // in that array upon the current Planet
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double result = 0;
        for(Planet p: allPlanets){
            if(!p.equals(this)){
                result += this.calcForceExertedByX(p);
            }
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double result = 0;
        for(Planet p: allPlanets){
            if(!p.equals(this)){
                result += this.calcForceExertedByY(p);
            }
        }
        return result;
    }

    // a method that determines how much the forces exerted on the planet will cause that planet to accelerate,
    // and the resulting change in the planet’s velocity and position in a small period of time dt
    public void update(double dt, double fX, double fY){
        // a_net update
        double aNetX = fX / this.mass;
        double aNetY = fY / this.mass;
        // vel update
        this.xxVel = this.xxVel + dt * aNetX;
        this.yyVel = this.yyVel + dt * aNetY;
        // position update
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw(){
        StdDraw.setScale(- 3e+11, 3e+11);
        StdDraw.picture(this.xxPos,this.yyPos,this.imgFileName);
    }

}

