package phonet;

public class CPhonet{
    public native String phonet(String s);
    static { System.loadLibrary("phonet"); }
}                                                                    
