package phonetik;


public class CPhonetVsPhonetikTest1 extends CPhonetVsPhonetikTest {

	@Override Coder createCoder() { return new Phonet1(); }
	@Override int rules() { return 1; }
}
