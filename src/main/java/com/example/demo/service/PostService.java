package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ListDto;
import com.example.demo.exception.AppException;
import com.example.demo.mapper.PostMapper;
import com.example.demo.util.Pagination;
import com.example.demo.vo.Post;
import com.example.demo.web.form.PostCreateUpdateForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final ModelMapper modelMapper;
	private final PostMapper postMapper;
	
	public ListDto<Post> getPosts(int page, String keyword) {
		int totalRows = postMapper.getTotalRows(keyword);
		Pagination paging = new Pagination(page, totalRows);
		
		int offset = paging.getOffset();
		int rows = paging.getRows();
		List<Post> posts = postMapper.getPosts(keyword, offset, rows);
		
		return new ListDto<Post>(posts, paging);
	}
	
	public Post getPostDetail(int postNo) {
		return postMapper.getPostByPostNo(postNo).orElseThrow(() -> new AppException("게시글이 존재하지 않습니다."));
	}

	public void deletePost(int postNo, int userNo) {
		int postedUserNo = postMapper.getPostUserNoByPostNo(postNo).orElseThrow(() -> new AppException("게시글이 존재하지 않습니다."));
		if (postedUserNo != userNo) {
			throw new AppException("다른 사용자가 작성한 글은 삭제할 수 없습니다.");
		}
		
		Post post = Post.builder()
				.no(postNo)
				.deleted(true)
				.build();
		
		postMapper.updatePost(post);
	}

	public void createPost(PostCreateUpdateForm form, int userNo) {
		Post post = modelMapper.map(form, Post.class);
		post.setUserNo(userNo);
		
		postMapper.insertPost(post);
	}
	
	public PostCreateUpdateForm getUpdateForm(int postNo, int userNo) {
		Post post = getPostDetail(postNo);
		if (post.getUserNo() != userNo) {
			throw new AppException("다른 사용자가 작성한 게시글은 수정할 수 없습니다.");
		}
		
		return modelMapper.map(post, PostCreateUpdateForm.class);
	}

	public void updatePost(PostCreateUpdateForm form, int userNo) {
		int postedUserNo = postMapper.getPostUserNoByPostNo(form.getNo()).orElseThrow(() -> new AppException("게시글이 존재하지 않습니다."));
		if (postedUserNo != userNo) {
			throw new AppException("다른 사용자가 작성한 글은 삭제할 수 없습니다.");
		}
		
		Post post = modelMapper.map(form, Post.class);
		
		postMapper.updatePost(post);		
	}
}
