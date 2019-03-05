package application;

public class Word {

	private String word;
	private String describe;
	private Integer liczbaznakow;

	public Word(String word, String describe, Integer liczbaznakow) {
		this.word = word;
		this.describe = describe;
		this.liczbaznakow = liczbaznakow;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Integer getLiczbaznakow() {

		liczbaznakow = word.length();
		
		
		return liczbaznakow;
	}

	public void setLiczbaznakow(Integer liczbaznakow) {
		this.liczbaznakow = liczbaznakow;
	}

}
