package com.bit2016.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.mysite.vo.Gallery;

@Repository
public class GalleryDAO {
	@Autowired
	private SqlSession sqlSession;

	public void insert(Gallery vo) {
		sqlSession.insert("gallery.insert", vo);
	}

	public Gallery selectByNo(Long no) {
		Gallery vo = sqlSession.selectOne("gallery.selectByNo", no);
		return vo;
	}

	public List<Gallery> selectAll() {
		List<Gallery> list = sqlSession.selectList("gallery.selectAll");
		return list;
	}

	public void delete(Gallery vo) {
		sqlSession.delete("gallery.deleteByNo", vo);
	}
}
