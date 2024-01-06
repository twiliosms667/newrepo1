package com.blogger4.blogger4.repositary;

import com.blogger4.blogger4.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepositary extends JpaRepository <Post,Long >{

}
