package kr.co.vuelog.member.mapper;

import kr.co.vuelog.member.domain.ProfileDTO;

public interface ProfileMapper {
	
	// 프로필 사진 정보 확인
	public ProfileDTO read(String email);
	// 프로필 사진 등록
	public void insert(ProfileDTO profileDTO);
	// 프로필 사진 삭제
	public void delete(String email);

}
