/**
*  Appropriately overwrites Piece for a Rook piece
*  @author asuwoto3
*/
public class Rook extends Piece {

    private Color color;

    /**
    *  Constructor for rook
    */
    public Rook(Color color) {
        super(color);
    }

    /**
    *  Returns algebraic name
    */
    public String algebraicName() {
        return "R";
    }

    /**
    *  Returns FEN name
    */
    public String fenName() {
        if (color == Color.WHITE) {
            return "R";
        } else {
            return "r";
        }
    }

    /**
    *  Returns squares the piece could have moved from in an array
    */
    public Square[] movesFrom(Square square) {
        Square[] fleb = new Square[1];
        fleb[0] = new Square("b1");
        return fleb;
    }
}