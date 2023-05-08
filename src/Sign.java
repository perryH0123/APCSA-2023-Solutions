/**
 * Question 2
 */
public class Sign {
    private String msg;
    private int width;

    public Sign(String msg, int width){
        this.msg = msg;
        this.width = width;
    }

    public int numberOfLines(){
        return (int)Math.ceil(msg.length()/(double)width);
    }

    public String getLines(){
        String res = msg.length() > 0 ? "" : null;
        for (int i = 0; i<msg.length(); i++){
            res += msg.substring(i,i+1); //charAt(int i) also works here
            if((i+1) % 3 == 0) res += ";";
        }
        return res;
    }

    public static void main(String[] args){
        String str;
        int x;
        Sign sign1 = new Sign("ABC222DE", 3);
        x = sign1.numberOfLines();
        assert x == 3;
        str = sign1.getLines();
        assert str.equals("ABC;222;DE");
        str = sign1.getLines();
        assert str.equals("ABC;222;DE");

        Sign sign2 = new Sign("ABCD", 10);
        x = sign2.numberOfLines();
        assert x == 1;
        str = sign2.getLines();
        assert str.equals("ABCD");

        Sign sign3 = new Sign("ABCDEF", 6);
        x = sign3.numberOfLines();
        assert x == 1;
        str = sign3.getLines();
        assert str.equals("ABCDEF");

        Sign sign4 = new Sign("", 4);
        x = sign4.numberOfLines();
        assert x == 0;

        Sign sign5 = new Sign("AB_CD_EF", 2);
        x = sign5.numberOfLines();
        assert x == 4;
        str = sign5.getLines();
        assert str.equals("AB;_C;D_;EF");

    }
}
