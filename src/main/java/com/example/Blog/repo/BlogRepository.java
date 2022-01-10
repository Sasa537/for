package com.example.Blog.repo;

import com.example.Blog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository <Post,Long>
{

}
