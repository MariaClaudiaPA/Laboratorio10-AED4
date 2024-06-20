package hash;

public class Coordenada implements Comparable<Coordenada> {
    int x;
    int y;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordenada that = (Coordenada) obj;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public int compareTo(Coordenada o) {
        if (this.x != o.x) {
            return Integer.compare(this.x, o.x);
        } else {
            return Integer.compare(this.y, o.y);
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
