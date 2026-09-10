package it.aulab.progetto_blog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.progetto_blog.dtos.PostDto;
import it.aulab.progetto_blog.models.Comment;
import it.aulab.progetto_blog.models.Post;
import it.aulab.progetto_blog.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PostDto> readAll() {
        List<PostDto> dtos = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            dtos.add(modelMapper.map(post, PostDto.class));
        }
        return dtos;
    }

    @Override
    public PostDto read(Long id) {
        Optional<Post> optPost = postRepository.findById(id);
        if (optPost.isPresent()) {
            return modelMapper.map(optPost.get(), PostDto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post id=" + id + " not found");
        }
    }

    @Override
    public PostDto create(Post post) {
        return modelMapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public PostDto update(Long id, Post post) {
        if (postRepository.existsById(id)) {
            post.setId(id);
            return modelMapper.map(postRepository.save(post), PostDto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void delete(Long id) {
        if (postRepository.existsById(id)) {
            Post post = postRepository.findById(id).get();
            if (post.getComments() != null) {
                for (Comment comment : post.getComments()) {
                    comment.setPost(null);
                }
            }
            postRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
    }
}