package com.blogger4.blogger4.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="postss")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String content;


    @OneToMany(mappedBy ="post",cascade = CascadeType.ALL,orphanRemoval = true)

    private List<Comment> comments=new ArrayList<>();
}
