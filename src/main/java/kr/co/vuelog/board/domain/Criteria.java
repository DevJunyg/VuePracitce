package kr.co.vuelog.board.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class Criteria {
	
	private int pageNum;
	private int amount;
	
	private int sort;
	private String boardtype;
	private char storage;
	
	private String type;
	private String keyword;
	
	private String email;
	
	public Criteria() {
		this.pageNum = 1;
		this.amount = 10;
		this.boardtype = "0";
		this.sort = 0;
		this.storage = 'n';
		this.email = null;
	}
	
	public Criteria(int pageNum, int amount, int sort, String boardtype, char storage, String email) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.sort = sort;
		this.boardtype = boardtype;
		this.storage = storage;
		this.email = email;
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.boardtype = "0";
		this.sort = 0;
		this.storage = 'n';
		this.email = null;
	}
	
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
															.queryParam("pageNum", this.pageNum)
															.queryParam("amount", this.getAmount())
															.queryParam("type", this.getType())
															.queryParam("keyword", this.getKeyword());
		return builder.toUriString();
	}
	
}
