package kr.co.vuelog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.vuelog.board.mappers.PostMapper;
import lombok.extern.log4j.Log4j;

// post mapper 테스트 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" )
@Log4j
public class PostMapperTests {
	@Autowired
	private PostMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	
//	@Test
//	public void testCreate() {
//		IntStream.rangeClosed(1, 10).forEach(i -> {
//			PostDTO postDto = new PostDTO();
//			
//			postDto.setTitle("게시물 제목 테스트 " + i);
//			postDto.setContent("게시물 내용 테스트 " + i);
//			postDto.setBoardtype("1");
//			postDto.setEmail("test");
//			postDto.setStorage('n');
//			
//			mapper.create(postDto);
//		});
//	}
	
	
//	@Test
//	public void testStorage() {
//		PostDTO postDto = new PostDTO();
//		
//		postDto.setTitle("게시물 제목 테스트");
//		postDto.setContent("게시물 임시저장 테스트");
//		postDto.setBoardtype("1");
//		postDto.setEmail("test");
//		postDto.setStorage('y');
//		
//		
//		mapper.create(postDto);
//	}
	
	
//	@Test
//	public void testRead() {
//		int targetPno = 5;
//		
//		PostDTO postDto = mapper.read(targetPno);
//		
//		log.info(postDto);
//	}

	
//	@Test
//	public void testDelete() {
//		int targetPno = 13;
//		
//		mapper.delete(targetPno);
//		
//	}
	
	
//	@Test
//	public void testUpdate() {
//		int targetPno = 2;
//		
//		PostDTO postDto = mapper.read(targetPno);
//		
//		postDto.setTitle("제목 업데이트 테스트");
//		postDto.setContent("내용 업데이트 테스트");
//		postDto.setStorage('n');
//		int count = mapper.update(postDto);
//		log.info("UPDATE Count : " + count);
//	}
	
	
//	@Test
//	public void testList() {
//		Criteria cri = new Criteria();
//		List<PostDTO> posts = mapper.getList(cri);
//		posts.forEach(post -> log.info(post));
//	}
	
}
