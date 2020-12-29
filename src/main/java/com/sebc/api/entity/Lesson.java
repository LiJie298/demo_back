package com.sebc.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author lijie
 * @Date 2020/12/29
 * @Desc 课程实体
 */
@Document("lesson")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    private String lessonName;
    private String lessonTeacher;
    private String lessonImg;
    //    private String  lessonVideo;
    //    private String  lessonTime;
    private String lessonNumSuppose;
    private String lessonMainPage;
    //    private String lessonComment;
    private String lessonCollect;
    private String lessonDetail;
    private String lessonDiffculty;
}
