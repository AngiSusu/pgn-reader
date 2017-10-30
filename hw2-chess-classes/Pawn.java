/**
*  Appropriately overwrites Piece for a Pawn piece
*  @author asuwoto3
*/
public class Pawn extends Piece {

    private Color color;

    /**
    *  Constructor for pawn
    */
    public Pawn(Color color) {
        super(color);
    }

    /**
    *  Returns algebraic name
    */
    public String algebraicName() {
        return "";
    }

    /**
    *  Returns FEN name
    */
    public String fenName() {
        return "";
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
