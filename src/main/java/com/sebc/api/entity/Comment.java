package com.sebc.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author lijie
 * @Date 2020/12/29
 * @Desc 评论
 */
@Document("comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private ObjectId lesson_id;
    private String username;
    private String context;
    private boolean check;
}
