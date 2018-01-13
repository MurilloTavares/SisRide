package controle;

import javax.xml.bind.DatatypeConverter;

public class EncodeDecode {
    
    public static String encode(String texto){
        return DatatypeConverter.printBase64Binary(texto.getBytes());
    }
    
    public static String decode(String encoded){
        return new String(DatatypeConverter.parseBase64Binary(encoded));
    }
    
}
