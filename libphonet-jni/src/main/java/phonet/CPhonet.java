package phonet;

public class CPhonet{
    public native String phonet(String s, int rules);
    static { System.loadLibrary("phonet"); }
}                                                                    
