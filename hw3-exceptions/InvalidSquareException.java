/**
 *  Unchecked exception when an invalid square is passed in to Square.
 *  Exception is unchecked because the file read may contain an invalid square
 *  but the external file cannot be checked by the compiler, thus any invalid
 *  square errors can only be found at runtime.
 *  @author asuwoto3
 *  @version 1.0
 */
class InvalidSquareException extends RuntimeException {

    /**
    *  creates an unchecked exception for an invalid square
    */
    InvalidSquareException(String sqr) {
        super(sqr);
    }

}
