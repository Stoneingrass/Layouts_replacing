package layoutsReplacing;/*
   доработать передачу пар при инициализации
*/

public class LayoutReplacement {
    static final int nLayouts = 2;

    static final char[] enLower = "qwertyuiop[]\\asdfghjkl;'zxcvbnm,./".toCharArray();
    static final char[] enUpper = "QWERTYUIOP{}ASDFGHJKL:\"ZXCVBNM<>?".toCharArray();
    static final char[] enNum = "~!@#$%^&*()_+".toCharArray();
    static final char[][] en = new char[][] {enLower, enUpper, enNum};

    static final char[] ruLower = "йцукенгшщзхъ\\фывапролджэячсмитьбю.".toCharArray();
    static final char[] ruUpper = "ЙЦУКЕНГШЩЗХЪ/ФЫВАПРОЛДЖЭЯЧСМИТЬБЮ,".toCharArray();
    static final char[] ruNum = "Ё!\"№;%:?*()_+".toCharArray();
    static final char[][] ru = new char[][] {ruLower, ruUpper, ruNum};

    static final char [][][] layouts = new char[][][] {en, ru};


    //index in array is changing layout, value by that index is replacing layout
    private Layouts[] replacementArray = new Layouts[nLayouts];

    public LayoutReplacement() {
        for (int i=0; i<nLayouts; i++) {    //setting replacement layouts by itself
            replacementArray[i]=Layouts.values()[i];
        }
    }
    public LayoutReplacement(Layouts[]... replacementPair) {
        this();
        for (Layouts[] l: replacementPair) {
            if(l.length==2) {   //checking the validity of the layout replacement array
                replacementArray[l[0].ordinal()] = l[1];
            }
        }
    }


    public void addReplacement(Layouts changingLayout, Layouts replacingLayout) {
        replacementArray[changingLayout.ordinal()]=replacingLayout;
    }

    public void removeReplacement(Layouts removingReplacement) {
        replacementArray[removingReplacement.ordinal()]=removingReplacement;    //setting replacement layouts by itself
    }

    public String initializeReplacing(String inputString) {
        StringBuilder outputString = new StringBuilder();

        boolean isSymbolFound;

        for (int ch=0; ch<inputString.length(); ch++) {     //ch is char index in the string
            isSymbolFound=false;
            for (int i=0; i<nLayouts; i++) {     //i is layout index
                if (i != replacementArray[i].ordinal()) {    //if layout do not replace itself
                    for (int j = 0; j < layouts[i].length; j++) {    //j is sub layout index
                        for (int k = 0; k < layouts[i][j].length; k++) {    //k is char index in layout
                            if (inputString.charAt(ch) == layouts[i][j][k]) {
                                outputString.append(layouts[replacementArray[i].ordinal()][j][k]);
                                isSymbolFound = true;
                            }
                            if (isSymbolFound) break;
                        }
                        if (isSymbolFound) break;
                    }
                    if (isSymbolFound) break;
                }
            }
            //if there is not existing symbol
            if (!isSymbolFound) {
                outputString.append(inputString.charAt(ch));
            }
        }

        return outputString.toString();
    }
}