package kr.co.vuelog.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.vuelog.blog.domain.BlockDTO;
import kr.co.vuelog.blog.mapper.BlockMapper;
import kr.co.vuelog.blog.service.IBlockService;
import kr.co.vuelog.member.domain.MemberDTO;

@Service
public class BlockServiceImpl implements IBlockService {
	
	@Autowired
	private BlockMapper mapper;
	
	@Override
	public List<MemberDTO> blockToList(String email) {
		return mapper.blockToList(email);
	}

	@Override
	public List<MemberDTO> blockByList(String email) {
		return mapper.blockByList(email);
	}

	@Override
	public int blockInsert(BlockDTO blockDto) {
		return mapper.blockInsert(blockDto);
	}

	@Override
	public int blockDelete(BlockDTO blockDto) {
		return mapper.blockDelete(blockDto);
	}

	@Override
	public BlockDTO searchBlock(BlockDTO blockDto) {
		return mapper.searchBlock(blockDto);
	}

}
