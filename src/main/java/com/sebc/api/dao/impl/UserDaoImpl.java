package com.sebc.api.dao.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.sebc.api.dao.UserDao;
import com.sebc.api.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


    @Autowired
    private MongoOperations writeMongo;

    @Override
    public User findUserByUsername(String userName) {
        Query query = new Query(Criteria.where("username").is(userName));
        List<User> userList = writeMongo.find(query, User.class);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public User insertUser(User user) {
       return writeMongo.insert(user);
    }

    @Override
    public long deleteUser(User user) {
        ObjectId id = user.getId();
        if (id == null) {
            return 0;
        }
        DeleteResult deleteResult = writeMongo.remove(Query.query(Criteria.where("_id").is(id)));
        return deleteResult.getDeletedCount();
    }

    @Override
    public long updateUser(User user) {
        ObjectId id = user.getId();
        if (id == null) {
            return 0;
        }
        Update update = new Update();
        if (user.getUsername() != null && user.getUsername().length() > 0) {
            update.addToSet("name", user.getUsername());
        }
        // 更新操作有两种
        //1、只做更新操作
        //  ExecutableUpdateOperation.TerminatingUpdate<User> id1 = writeMongo.update(User.class).matching(Query.query(Criteria.where("_id").is(id)))
        //   .apply(update);
        //2、更新，没有的话会进行插入操作
        UpdateResult result = writeMongo.upsert(Query.query(Criteria.where("_id").is(id)), update, User.class);
        return result.getModifiedCount();
    }
}
