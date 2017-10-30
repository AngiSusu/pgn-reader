/**
*  Appropriately overwrites Piece for a King piece
*  @author asuwoto3
*/
public class King extends Piece {

    private Color color;

    /**
    *  Constructor for king
    */
    public King(Color color) {
        super(color);
    }

    /**
    *  Returns algebraic name
    */
    public String algebraicName() {
        return "K";
    }

    /**
    *  Returns FEN name
    */
    public String fenName() {
        if (color == Color.WHITE) {
            return "K";
        } else {
            return "k";
        }
    }

    /**
    *  Returns squares the piece could have moved from in an array
    */
    public Square[] movesFrom(Square square) {
        /*ArrayList sqs = new ArrayList();
        if (square.file == 'a') {
            if (square.rank == '8') {
                sqs.add(new Square("b8"));
                sqs.add(new Square("a7"));
            } else if (square.rank == '1') {
                sqs.add(new Square("b1"));
                sqs.add(new Square("a2"));
            } else {
                sqs.add(new Square('a', square.rank));
            }
        }*/
        Square[] fleb = new Square[1];
        fleb[0] = new Square("b1");
        return fleb;
    }
}