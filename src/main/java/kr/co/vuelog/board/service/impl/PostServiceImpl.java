package kr.co.vuelog.board.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.vuelog.board.domain.Criteria;
import kr.co.vuelog.board.domain.PostAttachDTO;
import kr.co.vuelog.board.domain.PostDTO;
import kr.co.vuelog.board.domain.PostPageDTO;
import kr.co.vuelog.board.domain.TagDTO;
import kr.co.vuelog.board.mappers.PostAttachMapper;
import kr.co.vuelog.board.mappers.PostMapper;
import kr.co.vuelog.board.mappers.TagMapper;
import kr.co.vuelog.board.service.IPostService;

@Service
public class PostServiceImpl implements IPostService{

	@Autowired
	private PostMapper mapper;
	
	@Autowired
	private PostAttachMapper attachMapper;
	
	@Autowired
	private TagMapper tagMapper;
	
	private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
	
	@Override
	public List<PostDTO> getListWithPaging(Criteria cri) {
		log.info("getListWithPaging.............");
		return mapper.getList(cri);
	}
	
	@Override
	public List<PostDTO> getTagWithPaging(Criteria cri) {
		return null;
	}

	@Transactional
	@Override
	public int register(PostDTO pDto){
		mapper.create(pDto);
		int result = 0;

		if (pDto.getAttachList() != null) {
			pDto.getAttachList().forEach(attach -> {
				attach.setPno(pDto.getPno());
				attachMapper.insert(attach);
			});
		}
		
		if(pDto.getTagList() != null) {
			pDto.getTagList().forEach(tag -> {
				tag.setPno(pDto.getPno());
				tagMapper.create(tag);
			});
		}
		
		result = pDto.getPno();
		return result;
	}

	
	@Override
	public boolean modify(PostDTO pDto) {
		attachMapper.deleteAll(pDto.getPno());
		
		boolean modifyResult = mapper.update(pDto) == 1;
		log.info("modify result : " + modifyResult);
		
		if(modifyResult && pDto.getTagList() != null) {
			pDto.getTagList().forEach(tag -> {
				tag.setPno(pDto.getPno());
				tagMapper.create(tag);
			});
		}
//		if (modifyResult && pDto.getAttachList() != null) {
//			pDto.getAttachList().forEach(attach -> {
//				attach.setPno(pDto.getPno());
//				attachMapper.insert(attach);
//			});
//		}
		return modifyResult;
	}

	@Transactional
	@Override
	public boolean remove(Integer pno) {
		// attachMapper.deleteAll(pno);
		tagMapper.deleteAll(pno);
		return mapper.delete(pno) == 1;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public PostDTO read(Integer pno) {
		mapper.updateViewCnt(pno);
		return mapper.read(pno);
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return mapper.getTotalCnt(cri);
	}

	@Override
	public List<PostAttachDTO> getImgList(Integer pno) {
		return attachMapper.findByPno(pno);
	}

	@Override
	public List<TagDTO> getTagList(Integer pno) {
		return tagMapper.findByPno(pno);
	}

	@Transactional
	@Override
	public PostPageDTO getListPage(Criteria cri) {
		return new PostPageDTO(mapper.getTotalCnt(cri), mapper.getList(cri), cri);
	}

}
