/**
 *  Object that represents squares on a chess board
 *  @author asuwoto3
 */
public class Square {

    private char file;
    private char rank;

    /**
     *  Public constructor assigns file ('a') and rank ('1') as chars
     */
    public Square(char file, char rank) {
        this.file = file;
        this.rank = rank;
    }

    /**
     *  Public constructor assigns file ('a') and rank ('1') as chars from
     *  String
     */
    public Square(String name) {
        file = name.charAt(0);
        rank = name.charAt(1);
    }


    /**
     *  Returns a String representation of the square name, e.g., "a1"
     */
    public String toString() {
        return String.valueOf(file) + String.valueOf(rank);
    }

    /**
     *  Returns equality of Square objects
     */
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Square) {
            Square sqr = (Square) obj;
            if (this.file == sqr.file && this.rank == sqr.rank) {
                return true;
            }
        }
        return false;
    }
}