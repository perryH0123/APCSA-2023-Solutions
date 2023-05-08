import java.util.Arrays;

public class BoxOfCandy {
    public static class Candy {
        String flavor;
        private Candy(String flavor){ this.flavor = flavor; }
        public String getFlavor() {return flavor;}
    }

    //Helper method to preserve my sanity
    public static Candy c(String flavor) {return new Candy(flavor);}

    /** box contains at least one row and is initialized in the constructor. */
    private Candy[][] box;

    public BoxOfCandy(Candy[][] box){
        this.box = box;
    }

    /**
     * Moves one piece of candy in column col, if necessary and possible, so that the box
     * element in row 0 of column col contains a piece of candy, as described in part (a).
     * Returns false if there is no piece of candy in column col and returns true otherwise.
     * Precondition: col is a valid column index in box.
     */
    public boolean moveCandyToFirstRow(int col){
        if(box[0][col] != null) return true;
        for (int i = 1; i < box.length; i++){
            if(box[i][col] != null) {
                box[0][col] = box[i][col];
                box[i][col] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Removes from box and returns a piece of candy with flavor specified by the parameter, or
     * returns null if no such piece is found, as described in part (b)
     */
    public Candy removeNextByFlavor(String flavor){
        for(int i = box.length - 1; i>= 0; i--){
            for(int j = 0; j<box[i].length; j++){
                if (box[i][j].getFlavor().equals(flavor)){ //MUST USE .equals(o) FOR FULL CREDIT
                    Candy c = box[i][j];
                    box[i][j] = null;
                    return c;
                }
            }
        }
        return null;
    }

    public Candy[][] getBox(){
        return box;
    }

    public static void main(String[] args){
        BoxOfCandy boxA = new BoxOfCandy(new Candy[][]{
                {null, c("lime"), null},
                {null, c("orange"), null},
                {null, null, c("cherry")},
                {null, c("lemon"), c("Grape")}
        });
        boxA.moveCandyToFirstRow(0);
        Candy[][] testCaseA1 = boxA.getBox();
        boxA.moveCandyToFirstRow(1);
        Candy[][] testCaseA2 = boxA.getBox();
        assert Arrays.deepEquals(testCaseA1, testCaseA2);
        boxA.moveCandyToFirstRow(2);
        Candy[][] testCaseA3 = boxA.getBox();
        assert Arrays.deepEquals(testCaseA3, new Candy[][] {
                {null, c("lime"), c("cherry")},
                {null, c("orange"), null},
                {null, null, null},
                {null, c("lemon"), c("Grape")}
        });

        BoxOfCandy boxB = new BoxOfCandy(new Candy[][] {
                {c("lime"), c("lime"), null, c("lemon"), null},
                {c("orange"), null, null, c("lime"), c("lime")},
                {c("cherry"), null, c("lemon"), null, c("orange")}
        });
        assert boxB.removeNextByFlavor("cherry").getFlavor().equals("cherry");
        assert Arrays.deepEquals(boxB.getBox(), new Candy[][] {
                {c("lime"), c("lime"), null, c("lemon"), null},
                {c("orange"), null, null, c("lime"), c("lime")},
                {null, null, c("lemon"), null, c("orange")}
        });
        assert boxB.removeNextByFlavor("lime").getFlavor().equals("lime");
        assert Arrays.deepEquals(boxB.getBox(), new Candy[][] {
                {c("lime"), c("lime"), null, c("lemon"), null},
                {c("orange"), null, null, null, c("lime")},
                {null, null, c("lemon"), null, c("orange")}
        });
    }
}
