package phonetik;


public class CPhonetVsPhonetikTest2 extends CPhonetVsPhonetikTest {

	@Override Coder createCoder() { return new Phonet2(); }
	@Override int rules() { return 2; }
	
}
