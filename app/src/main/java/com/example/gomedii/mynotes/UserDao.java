package com.example.gomedii.mynotes;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by GOMEDII on 3/22/2018.
 */
@Dao
public interface UserDao {

    @Insert
    void addUser(User... user);

    @Query("select * from users")
    public List<User>getUsers();
    @Update
    void update(User user);
    @Query("SELECT * FROM users WHERE id == :id")
    public User getUser(int id);
    @Delete
    void  delete(User user);

}
