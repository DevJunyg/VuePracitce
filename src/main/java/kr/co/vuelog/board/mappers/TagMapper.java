package kr.co.vuelog.board.mappers;

import java.util.List;

import kr.co.vuelog.board.domain.PostDTO;
import kr.co.vuelog.board.domain.TagDTO;

public interface TagMapper {
	public int create(TagDTO tagDto);
	public TagDTO read(int tno);
	public int delete(int tno);
	
	public List<TagDTO> findByPno(int pno);
	public List<Integer> findByTagName(String tag); 
	public void deleteAll(Integer pno);
	public List<PostDTO> getListWithTag(String tag);
}
