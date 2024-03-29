package fr.lma.advent;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class Advent_3 {

    private final static String[] wires1 = {
            "R75,D30,R83,U83,L12,D49,R71,U7,L72",
            "U62,R66,U55,R34,D71,R55,D58,R83"
    }; // Expected Manhattan distance : 159, Min. steps 610.

    private final static String[] wires2 = {
            "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51",
            "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
    }; // Expected Manhattan distance : 135, Min. steps 410.

    private final static String[] wires3 = {
            "R1002,U407,R530,D268,R516,U937,L74,U838,R784,D684,L912,U746,R189,U192,R868,D345,L972,D492,R942,U631,L559,U634,L80,U513,L746,D997,L348,D160,L655,U949,R717,U396,R549,D167,R591,U469,L22,U977,L167,D856,L320,D920,L396,U490,L895,U180,R661,D828,R864,U189,R307,U402,R409,U445,L101,D418,R812,U419,R319,U75,L813,D46,L491,U39,R737,U11,R177,U311,L278,U254,R475,U166,L515,D105,L694,D437,L298,U169,L613,D234,L999,U380,L711,D758,R932,D27,L951,D529,L935,D189,R816,D176,R98,D320,R965,D333,L367,U622,R18,U83,R275,D205,L960,U177,R750,D466,R442,U797,R355,D717,L569,D578,R384,U863,R541,U405,R527,D658,L514,U168,L64,D918,R947,D11,L189,D875,R599,U201,L165,U772,L679,U566,L195,U660,R896,D622,R678,U390,L984,D900,R889,D714,R557,U848,L176,U541,R518,D699,L904,D23,L55,D886,L206,D621,L48,D197,R502,D259,L779,D72,L183,U747,L424,U452,L603,U561,L430,D942,R515,D378,R962,U508,R230,D650,R804,D453,R899,D813,R484,U798,R456,D231,L316,U117,R630,D436,L985,D283,L393,D370,R158,U957,L914,D455,L875,U536,R889,U400,R347,U712,R487,D455,R428,U590,R127,D132,L202,U377,R138,U654,L760,D46,R213,D225,L817,U455,L612,U543,L525,U979,R591,D940,R446,U786,R750,U244,R325,U928,L44,U551,L955,U221,L986,U516,L916,D242,L280,D71,R80,U849,L271,U626,R737,D646,R82,U120,R646,U569,R463,D94,R570,U456,L548,D687,R221,D759,L606,D818,L859,U218,R682,U299,R818,D966,R407,U605,L859,D378,L53,D722,L216,D221,R639,U485,L865,D620,R99,D988,R944,D323,R540,U372,L470,U106,L804,D92,L177,U518,R277,U670,R451,D194,L695,D502,L601,U596,R374,U682,L19,D54,L156",
            "L1003,U22,R594,D241,L215,D906,R733,D831,L556,U421,L780,D242,R183,U311,R46,D52,R124,D950,L18,U985,R999,D528,R850,U575,L138,D62,L603,U467,R641,U155,L165,D63,L489,U4,R635,D460,L446,D938,R983,U494,L491,D433,L722,U427,L469,U832,L712,U798,R906,U804,R646,U721,R578,D194,L726,U803,L985,D934,R943,U198,R726,U341,R583,U998,L992,D401,L132,D681,L363,U949,L814,D977,R840,D145,L177,D291,L652,D396,R330,D951,L363,U813,R847,D374,R961,D912,R516,D178,R495,U49,R340,D395,R632,D991,R487,D263,R320,D948,R456,D142,L783,D888,R589,D999,L159,U686,R402,D586,L425,U946,R56,D979,L534,U313,R657,D401,L666,D504,R712,D232,L557,D620,R193,D670,L134,D975,R837,D901,R813,U459,L499,U450,L87,U84,L582,U310,R795,D280,L730,D458,L727,D196,R95,U210,R498,U760,R778,U325,R715,U479,R275,U557,L450,D196,L60,U115,R475,D265,L611,D372,R60,U935,L717,U809,L344,D854,L386,U473,R72,U968,L816,U900,R866,U172,R965,U383,R576,D774,R753,U788,L781,D237,L401,U786,R873,U331,R609,D232,L603,U685,L494,U177,L982,D173,L673,U772,L7,U7,R517,U573,R212,D413,L124,D810,L223,U137,L576,U95,R128,U896,L91,U932,L875,U917,R106,U911,L208,D507,L778,D59,L71,D352,R988,U708,L58,D429,L122,U771,L713,D801,R188,U661,R752,D374,R312,D848,L504,D540,R334,U517,R343,D739,L727,D552,L555,U580,L857,U474,R145,U188,L789,U698,R735,U131,L494,U162,L27,D849,L140,D849,R615,U798,R160,U492,R884,U521,L542,D729,R498,D853,R918,U565,R65,U32,L607,U552,L38,D822,L77,U490,L190,D93,L104,U268,R702,D112,L917,D876,L631,D139,L989,U810,R329,U253,L498,D767,L550,U666,L549,U616,R376"
    }; // Expected Manhattan distance : 245, Min. steps 48262.

    public static final String CLOSEST_INTERSECTION = "Closest intersection : ";
    public static final String SHORTEST_TOTAL_PATH = "Shortest total path : ";

    public static void main(final String[] args) {
        compute(wires1);
        compute(wires2);
        compute(wires3);
    }

    private static void compute(final String[] wires) {
        final List<Point> intersections = getIntersections(getSegmentList(wires[0]), getSegmentList(wires[1]));
        System.out.println(CLOSEST_INTERSECTION + calculateShortestCrossingsDistance(intersections));
        System.out.println(SHORTEST_TOTAL_PATH + getShortestTotalPath(intersections));
    }

    private static int getShortestTotalPath(final List<Point> intersections) {
        int currentShortest = Integer.MAX_VALUE;
        for (final Point point : intersections) {
            if (new Point(0, 0).equals(point)) {
                continue;
            }
            final int totalDistance = calculateDistance(point, true) + calculateDistance(point, false);
            if (totalDistance < currentShortest) {
                currentShortest = totalDistance;
            }
        }
        return currentShortest;
    }

    private static int calculateDistance(final Point point, final boolean goVert) {
        final Segment initialSegment = goVert ? point.getVert() : point.getHori();
        final Segment previousSegment = initialSegment.getPrevious();
        final Point attachementPoint;
        final int remainingDistance;
        if (null == previousSegment) {
            attachementPoint = new Point(0, 0);
            remainingDistance = 0;
        } else {
            attachementPoint = findAttachementPoint(initialSegment, previousSegment);
            remainingDistance = getTotalRemainingDistance(previousSegment);
        }
        return new Segment(point, attachementPoint).getLength() + remainingDistance;
    }

    private static int getTotalRemainingDistance(final Segment segment) {
        final int distance = segment.getLength();
        if (null != segment.getPrevious()) {
            return distance + getTotalRemainingDistance(segment.getPrevious());
        } else {
            return distance;
        }
    }

    private static Point findAttachementPoint(final Segment segA, final Segment segB) {
        if ((segA.getA().equals(segB.getA()))
                || (segA.getA().equals(segB.getB()))) {
            return segA.getA();
        } else if ((segA.getB().equals(segB.getA()))
                || segA.getB().equals(segB.getB())) {
            return segA.getB();
        } else {
            throw new IllegalStateException();
        }
    }

    private static List<Point> getIntersections(final List<Segment> segList1, final List<Segment> segList2) {
        final List<Point> intersections = new ArrayList<>();
        for (final Segment seg1 : segList1) {
            for (final Segment seg2 : segList2) {
                final Point p = seg1.intersectionWith(seg2);
                if (null != p) {
                    intersections.add(p);
                }
            }
        }
        return intersections;
    }

    private static int calculateShortestCrossingsDistance(final List<Point> intersections) {
        int distance = Integer.MAX_VALUE;
        for (final Point p : intersections) {
            int newDistance = Math.abs(p.getX()) + Math.abs(p.getY());
            if (newDistance < distance && newDistance > 0) {
                distance = newDistance;
            }
        }
        return distance;
    }

    private static List<Segment> getSegmentList(final String wire) {
        final String[] directions = wire.split(",");
        int prevX = 0;
        int prevY = 0;
        int x = 0;
        int y = 0;
        final List<Segment> segments = new ArrayList<>(directions.length);
        Segment previousSegment = null;
        for (int i = 0; i < directions.length; i++) {
            int length = Integer.parseInt(directions[i].substring(1));
            switch (directions[i].charAt(0)) {
                case 'R':
                    x += length;
                    break;
                case 'U':
                    y += length;
                    break;
                case 'L':
                    x -= length;
                    break;
                case 'D':
                    y -= length;
                    break;
            }
            final Segment newSegment = new Segment(prevX, prevY, x, y, length, previousSegment);
            segments.add(newSegment);
            previousSegment = newSegment;
            prevX = x;
            prevY = y;
        }
        return segments;
    }
}

class Segment {
    private Point A;
    private Point B;
    private Integer length;
    private Segment previous;

    public Segment(final int xA, final int yA, final int xB, final int yB, final int length, final Segment previous) {
        // On nomme A et B dans le sens de la lecture pour simplifier
        if ((xA == xB && yA >= yB) || (yA == yB && xB >= xA)) {
            this.A = new Point(xA, yA);
            this.B = new Point(xB, yB);
        } else {
            this.A = new Point(xB, yB);
            this.B = new Point(xA, yA);
        }
        this.length = length;
        this.previous = previous;
    }

    public Segment(final Point pointA, final Point pointB) {
        this.A = pointA;
        this.B = pointB;
    }

    public boolean isHorizontal() {
        return A.getY() == B.getY();
    }

    public boolean isVertical() {
        return !isHorizontal();
    }

    public Point intersectionWith(final Segment other) {
        if ((this.isHorizontal() && other.isHorizontal())
                || (this.isVertical() && other.isVertical())) {
            return null;
        }
        if (this.isVertical()) {
            return intersect(this, other);
        } else {
            return intersect(other, this);
        }
    }

    private static Point intersect(final Segment vert, final Segment hori) {
        int vertX = vert.getA().getX();
        int vertYa = vert.getA().getY();
        int vertYb = vert.getB().getY();
        int horiY = hori.getA().getY();
        int horiXa = hori.getA().getX();
        int horiXb = hori.getB().getX();
        if (vertX >= horiXa
                && vertX <= horiXb
                && horiY >= vertYb
                && horiY <= vertYa) {
            return new Point(vertX, horiY, vert, hori);
        }
        return null;
    }

    public Segment getPrevious() {
        return previous;
    }

    public Point getA() {
        return A;
    }

    public int getLength() {
        if (null != length) {
            return length;
        } else if (isHorizontal()) {
            return Math.abs(A.getX() - B.getX());
        } else if (isVertical()) {
            return Math.abs(A.getY() - B.getY());
        } else {
            throw new IllegalStateException();
        }
    }

    public Point getB() {
        return B;
    }
}

class Point {
    private int x;
    private int y;
    private Segment vert;
    private Segment hori;

    public Point(final int x, final int y, final Segment vert, final Segment hori) {
        this.x = x;
        this.y = y;
        this.vert = vert;
        this.hori = hori;
    }

    public Point(final int x, final int y) {
        this(x, y, null, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Segment getVert() {
        return vert;
    }

    public Segment getHori() {
        return hori;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final Point point = (Point) o;

        return new EqualsBuilder()
                .append(getX(), point.getX())
                .append(getY(), point.getY())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getX())
                .append(getY())
                .toHashCode();
    }
}
