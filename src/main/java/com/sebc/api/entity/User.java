package com.sebc.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private ObjectId id;
    private String username;
    private String password;
    private String userMail;
    private String userPhone;
    private String userAdmin;
    private String userPower;
    private String userStop;

    public User(String idStr) {
        this.id = new ObjectId(idStr);
    }

}
