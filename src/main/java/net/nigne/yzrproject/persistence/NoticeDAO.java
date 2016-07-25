package net.nigne.yzrproject.persistence;

import java.util.List;

import net.nigne.yzrproject.domain.NoticeVO;

public interface NoticeDAO {
	public List<NoticeVO> getNotice();
}
