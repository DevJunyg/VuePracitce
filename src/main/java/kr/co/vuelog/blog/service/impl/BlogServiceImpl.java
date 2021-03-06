package kr.co.vuelog.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import kr.co.vuelog.blog.domain.BlogDTO;
import kr.co.vuelog.blog.mapper.BlogMapper;
import kr.co.vuelog.blog.service.IBlogService;

@Service
@Repository
public class BlogServiceImpl implements IBlogService {

	@Autowired
	private BlogMapper mapper;
	
	@Override
	public BlogDTO read(String id) {
		return mapper.read(id);
	}

	@Override
	public int updateInfo(BlogDTO bDto) {
		return mapper.updateInfo(bDto);
	}

	@Override
	public int updateId(BlogDTO bDto) {
		return mapper.updateId(bDto);
	}

	@Override
	public int delete(String email) {
		return mapper.delete(email);
	}

}
