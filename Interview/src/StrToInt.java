
public class StrToInt {
	
	String value;
	
	public StrToInt(String str){
		this.value = str;
	}
	
	public int getInt(){
		int i=0;
		
		char c;
		for(int j=0; j<value.length(); j++ ){
			c = value.charAt(j);
			i = i*10 + (c-'0');
		}
		return i;
	}
	
	public static void main(String[] args) {
		StrToInt a = new StrToInt("123");
		
		System.out.println(a.getInt());
	}

}
