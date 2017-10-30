/**
 *  Object that represents squares on a chess board
 *  @author asuwoto3
 *  @version 1.0
 */
public class Square {

    private char file;
    private char rank;

    /**
     *  Public constructor assigns file ('a') and rank ('1') as chars
     */
    public Square(char file, char rank) {
        if ((Character.getNumericValue(file) >= 10
            && Character.getNumericValue(file) <= 17)
            && (rank >= '1' && rank <= '8')) {
            this.file = file;
            this.rank = rank;
        } else {
            throw new InvalidSquareException("" + file + rank);
        }

    }

    /**
     *  Public constructor assigns file ('a') and rank ('1') as chars from
     *  String
     */
    public Square(String name) {
        if (name.length() == 2
            && (Character.getNumericValue(name.charAt(0)) >= 10
            && Character.getNumericValue(name.charAt(0)) <= 17)
            && (Character.getNumericValue(name.charAt(1)) >= 1
            && Character.getNumericValue(name.charAt(1)) <= 8)) {
            file = name.charAt(0);
            rank = name.charAt(1);
        } else {
            throw new InvalidSquareException(name);
        }
    }

    /**
     *  Returns file of the square name, e.g., "a"
     */
    public char getFile() {
        return this.file;
    }

    /**
     *  Returns rank of the square name, e.g., "1"
     */
    public char getRank() {
        return this.rank;
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